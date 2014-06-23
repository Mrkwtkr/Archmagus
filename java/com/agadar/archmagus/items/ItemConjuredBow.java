package com.agadar.archmagus.items;

import com.agadar.archmagus.help.References;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;

public class ItemConjuredBow extends ItemBow 
{
	public ItemConjuredBow(String name)
	{
		super();
		this.setUnlocalizedName(name);
		this.setTextureName(References.MODID + ":conjured_bow");
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
    
    @Override
    public int getItemEnchantability()
    {
        return 0;
    }
}
