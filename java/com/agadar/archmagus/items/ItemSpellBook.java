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
		this.setMaxDamage(0);
		this.setUnlocalizedName("spell_book");
		this.setTextureName(References.MODID + ":" + getUnlocalizedName().substring(5));
	}
	
	/** Gives the item the enchanted glow. */ 
	@Override
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }

	/** Returns the ItemStack's NBTTag. If it doesn't have one (is null), then
	 * it is assigned a new one, which is then returned. */
    public NBTTagCompound func_92110_g(ItemStack par1ItemStack)
    {
    	if (par1ItemStack.stackTagCompound == null)
    	{
    		par1ItemStack.stackTagCompound = new NBTTagCompound();
    	}
    	
    	if (!par1ItemStack.stackTagCompound.hasKey("spell"))
    	{
    		par1ItemStack.stackTagCompound.setTag("spell", new NBTTagCompound());
    	}
    	
        return (NBTTagCompound) par1ItemStack.stackTagCompound.getTag("spell");
    }
    
    /** Adds additional information to the item's tooltip. */
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        
        NBTTagCompound spelldata = this.func_92110_g(par1ItemStack);               
        short short1 = spelldata.getShort("id");
        short short2 = spelldata.getShort("lvl");
        short cooldown = (short) (spelldata.getShort("cooldown") / 20);
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
    	NBTTagCompound spelldata = this.func_92110_g(par1ItemStack);               
    	spelldata.setShort("id", (short) par2SpellData.spellObj.effectId);
    	spelldata.setShort("lvl", (short) par2SpellData.spellLevel);
    }
    
    /** Attempts to combine two spell books. Returns null if it failed.
     *  Used for combining spell books in an Anvil. */
    public ItemStack combine(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
    	if (par1ItemStack.getItem() == ModItems.spell_book && par2ItemStack.getItem() == ModItems.spell_book &&
    			par1ItemStack.stackTagCompound != null && par2ItemStack.stackTagCompound != null &&
    			par1ItemStack.stackTagCompound.hasKey("spell") && par2ItemStack.stackTagCompound.hasKey("spell"))
    	{
    		NBTTagCompound spelldata1 = par1ItemStack.stackTagCompound.getCompoundTag("spell");               
    		NBTTagCompound spelldata2 = par2ItemStack.stackTagCompound.getCompoundTag("spell");                  		
    		short id = spelldata1.getShort("id");
    		
    		if (id == spelldata2.getShort("id"))
    		{
    			short level1 = spelldata1.getShort("lvl");
    			short level2 = spelldata2.getShort("lvl");
				
    			if (level1 > level2)
    			{
        			ItemStack result = new ItemStack(ModItems.spell_book);
    				result.stackTagCompound = new NBTTagCompound();
    				NBTTagCompound spelldata3 = new NBTTagCompound();
    				spelldata3.setShort("id", id);
    				spelldata3.setShort("lvl", level1);
    				result.stackTagCompound.setTag("spell", spelldata3);  
    				
    				return result;
    			}
    			else if (level1 == level2)
    			{
        			ItemStack result = new ItemStack(ModItems.spell_book);
    				result.stackTagCompound = new NBTTagCompound();
    				NBTTagCompound spelldata3 = new NBTTagCompound();               
    				spelldata3.setShort("id", id);
    				
    				if (level1 + 1 <= Spells.getSpellAt(id).getMaxLevel())
    				{
    					spelldata3.setShort("lvl", (short) (level1 + 1));
    				}
    				else
    				{
    					spelldata3.setShort("lvl", level1);
    				}
    				
    				result.stackTagCompound.setTag("spell", spelldata3);

    				return result;
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

    /** Adds spell books of all possible levels of the given Spell to the given List.
     *  Used for placing all possible spell books in the creative menu. */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    public void func_92113_a(Spell par1Spell, List par2List)
    {
        for (int i = par1Spell.getMinLevel(); i <= par1Spell.getMaxLevel(); ++i)
        {
            par2List.add(this.getSpellItemStack(new SpellData(par1Spell, i)));
        }
    }

    /** Makes the item tooltip text a light blue color. */
    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }

    /** Casts the spell book's spell on right click. */
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {		
    	NBTTagCompound spelldata = this.func_92110_g(par1ItemStack);
    	short cooldown = spelldata.getShort("cooldown");	
    	boolean inCreative = par3EntityPlayer.capabilities.isCreativeMode;
    	
    	if (cooldown == 0 || inCreative)
    	{
    		Spell spell = Spells.getSpellAt(spelldata.getShort("id"));	

    		if (par3EntityPlayer.getFoodStats().getFoodLevel() >= spell.getHungerCost() || inCreative)
    		{
    			short level = spelldata.getShort("lvl");
    			boolean succes = spell.castSpell(level, par2World, par3EntityPlayer);

    			if (succes)
    			{
    				if (!par2World.isRemote)
        			{
    					spelldata.setShort("cooldown", spell.getCooldown());
        			}
    				
    				if (!inCreative) 
    				{
    					par3EntityPlayer.getFoodStats().addStats(-spell.getHungerCost(), 0);
    				}
    			}      		
    		}
    	}

    	return par1ItemStack;
    }
    
    /** Updates the remaining cooldown every game tick. */
    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) 
    {
    	if (!par2World.isRemote)
    	{
    		NBTTagCompound spelldata = this.func_92110_g(par1ItemStack);
    		short cooldown = spelldata.getShort("cooldown");

    		if (cooldown > 0)
    		{
    			spelldata.setShort("cooldown", (short) (cooldown - 1));
    		}
    	}
    }
}
