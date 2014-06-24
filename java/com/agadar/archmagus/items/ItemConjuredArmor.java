package com.agadar.archmagus.items;

import com.agadar.archmagus.help.References;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemConjuredArmor extends ItemArmor
{
	public ItemConjuredArmor(int armorType, String unlocalizedName, ArmorMaterial armorMaterial)
	{
		super(armorMaterial, 0, armorType);
		setUnlocalizedName(unlocalizedName);
		setTextureName(References.MODID + ":" + getUnlocalizedName().substring(5, getUnlocalizedName().length() - 2));
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		Item item = stack.getItem();
		
		if (item != ModItems.conjured_leggings_1 && item != ModItems.conjured_leggings_2 &&
				item != ModItems.conjured_leggings_3)
		{
			return References.MODID + ":textures/models/armor/conjured_layer_1.png";
		}
		return References.MODID + ":textures/models/armor/conjured_layer_2.png";
	}
	
	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
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
}
