package com.agadar.archmagus.items;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

import com.agadar.archmagus.help.References;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemConjuredAxe extends ItemAxe 
{
    public ItemConjuredAxe(Item.ToolMaterial p_i45327_1_, String name)
    {
        super(p_i45327_1_);
        this.setUnlocalizedName(name);
		this.setTextureName(References.MODID + ":conjured_axe");
    }

    @Override
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }
	
	@Override
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }

    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }
}
