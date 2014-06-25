package com.agadar.archmagus.items;

import java.util.List;

import com.agadar.archmagus.spells.Spell;
import com.agadar.archmagus.spells.SpellData;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemSpellTome extends Item 
{
	public ItemSpellTome()
	{
		this.setMaxStackSize(1);
		this.setUnlocalizedName("spell_tome");
		this.setTextureName("book_enchanted");
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
        Spell spell = Spell.getSpellAt(short1); 
        
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
     * Adds a stored spell to a spell tome ItemStack.
     */
    public void addSpell(ItemStack par1ItemStack, SpellData par2SpellData)
    {
        NBTTagCompound nbttagcomp = this.func_92110_g(par1ItemStack);
        nbttagcomp.setShort("id", (short)par2SpellData.spellObj.effectId);
        nbttagcomp.setShort("lvl", (short)par2SpellData.spellLevel);
    }
    
    /**
     * Returns the ItemStack of a spell version of this item.
     */
    public ItemStack getSpellItemStack(SpellData par1SpellData)
    {
        ItemStack itemstack = new ItemStack(this);
        this.addSpell(itemstack, par1SpellData);
        return itemstack;
    }

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

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {		
    	NBTTagCompound nbttagcomp = this.func_92110_g(par1ItemStack);
    	short cooldown = nbttagcomp.getShort("cooldown");	

    	if (cooldown == 0)
    	{
    		Spell spell = Spell.getSpellAt(nbttagcomp.getShort("id"));	
    		boolean inCreative = par3EntityPlayer.capabilities.isCreativeMode;

    		if (par3EntityPlayer.getFoodStats().getFoodLevel() >= spell.getHungerCost() || inCreative)
    		{
    			if (!par2World.isRemote)
    			{
    				nbttagcomp.setShort("cooldown", spell.getCooldown());
    			}
    			
    			short level = nbttagcomp.getShort("lvl");
    			spell.cast(level, par2World, par3EntityPlayer);

    			if (!inCreative) par3EntityPlayer.getFoodStats().addStats(-spell.getHungerCost(), 0);        		
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
