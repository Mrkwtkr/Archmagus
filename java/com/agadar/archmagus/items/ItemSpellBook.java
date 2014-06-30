package com.agadar.archmagus.items;

import java.util.List;

import com.agadar.archmagus.help.References;
import com.agadar.archmagus.spells.Spell;
import com.agadar.archmagus.spells.SpellData;
import com.agadar.archmagus.spells.Spells;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemSpellBook extends Item 
{
	public ItemSpellBook()
	{
		this.setMaxStackSize(1);
		this.setUnlocalizedName("spell_book");
		this.setTextureName(References.MODID + ":" + getUnlocalizedName().substring(5));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }

	@Override
    public boolean isItemTool(ItemStack par1ItemStack)
    {
        return false;
    }

	/** Returns the ItemStack's NBTTag. If it doesn't have one (is null), then
	 * it is assigned a new one, which is then returned. */
    public NBTTagCompound func_92110_g(ItemStack par1ItemStack)
    {
    	if (par1ItemStack.stackTagCompound == null)
    	{
    		par1ItemStack.stackTagCompound = new NBTTagCompound();
    	}
        return par1ItemStack.stackTagCompound;
    }
    
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        
        NBTTagCompound nbttagcomp = this.func_92110_g(par1ItemStack);
        short short1 = nbttagcomp.getShort("id");
        short short2 = nbttagcomp.getShort("lvl");
        short cooldown = (short) (nbttagcomp.getShort("cooldown") / 20);
        Spell spell = Spells.getSpellAt(short1); 
        
        if (spell != null)
        {
        	par3List.add(spell.getTranslatedName(short2));
        	
        	if (cooldown != 0)
        	{
        		par3List.add("Cooldown: " + cooldown + " seconds");
        	}
        }
    }

    /**
     * Adds the given spell to the given ItemStack.
     */
    public void addSpell(ItemStack par1ItemStack, SpellData par2SpellData)
    {        
    	NBTTagCompound nbttagcomp = this.func_92110_g(par1ItemStack);
    	nbttagcomp.setShort("id", (short)par2SpellData.spellObj.effectId);
    	nbttagcomp.setShort("lvl", (short)par2SpellData.spellLevel);
    }
    
    /** Attempts to combine two spell books. Returns null if it failed. */
    public static ItemStack combine(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
    	if (par1ItemStack.stackTagCompound != null && par2ItemStack.stackTagCompound != null)
    	{
    		short id = par1ItemStack.stackTagCompound.getShort("id");
    		
    		if (id == par2ItemStack.stackTagCompound.getShort("id"))
    		{
    			short level1 = par1ItemStack.stackTagCompound.getShort("lvl");
    			short level2 = par2ItemStack.stackTagCompound.getShort("lvl");
				
    			if (level1 > level2)
    			{
        			ItemStack itemStack = new ItemStack(ModItems.spell_book);
    				itemStack.stackTagCompound = new NBTTagCompound();
    				itemStack.stackTagCompound.setShort("id", id);
    				itemStack.stackTagCompound.setShort("lvl", level1);
    				
    				return itemStack;
    			}
    			else if (level1 == level2)
    			{
        			ItemStack itemStack = new ItemStack(ModItems.spell_book);
    				itemStack.stackTagCompound = new NBTTagCompound();
    				itemStack.stackTagCompound.setShort("id", id);
    				
    				if (level1 + 1 <= Spells.getSpellAt(id).getMaxLevel())
    				{
    					itemStack.stackTagCompound.setShort("lvl", (short) (level1 + 1));
    				}
    				else
    				{
    					itemStack.stackTagCompound.setShort("lvl", level1);
    				}

    				return itemStack;
    			}
    		}
    	}
    	
    	return null;
    }
    
    /**
     * Returns an ItemStack of a single spell book with the given spell.
     */
    public ItemStack getSpellItemStack(SpellData par1SpellData)
    {
        ItemStack itemstack = new ItemStack(this);
        this.addSpell(itemstack, par1SpellData);
        return itemstack;
    }

    /** Adds spell books of all possible levels of the given Spell to the given List . */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    public void func_92113_a(Spell par1Spell, List par2List)
    {
        for (int i = par1Spell.getMinLevel(); i <= par1Spell.getMaxLevel(); ++i)
        {
            par2List.add(this.getSpellItemStack(new SpellData(par1Spell, i)));
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }

    /** BUG: For some reason, HOLDING the right button makes it so that this
     *  code is run only on the server. The result is that the particles are
     *  only spawned once or twice. Other than that it works fine, and tapping
     *  instead of holding DOES spawn the particles on every spell cast, but this
     *  certainly needs to be fixed. */
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {		
    	NBTTagCompound nbttagcomp = this.func_92110_g(par1ItemStack);
    	short cooldown = nbttagcomp.getShort("cooldown");	
    	boolean inCreative = par3EntityPlayer.capabilities.isCreativeMode;
    	
    	if (cooldown == 0 || inCreative)
    	{
    		Spell spell = Spells.getSpellAt(nbttagcomp.getShort("id"));	

    		if (par3EntityPlayer.getFoodStats().getFoodLevel() >= spell.getHungerCost() || inCreative)
    		{
    			short level = nbttagcomp.getShort("lvl");
    			boolean succes = spell.castSpell(level, par2World, par3EntityPlayer);

    			if (succes)
    			{
    				if (!par2World.isRemote)
        			{
        				nbttagcomp.setShort("cooldown", spell.getCooldown());
        			}
    				
    				if (!inCreative) 
    				{
    					par3EntityPlayer.getFoodStats().addStats(-spell.getHungerCost(), 0);
    				}
    				
    				spell.generateRandomParticles(par2World, par3EntityPlayer);
    			}      		
    		}
    	}

    	return par1ItemStack;
    }
    
    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) 
    {
    	if (!par2World.isRemote)
    	{
    		NBTTagCompound nbttagcompound = this.func_92110_g(par1ItemStack);
    		short cooldown = nbttagcompound.getShort("cooldown");

    		if (cooldown > 0) nbttagcompound.setShort("cooldown", (short) (cooldown - 1));
    	}
    }
}
