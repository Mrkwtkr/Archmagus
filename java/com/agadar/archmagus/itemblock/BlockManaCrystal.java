package com.agadar.archmagus.itemblock;

import com.agadar.archmagus.Archmagus;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockManaCrystal extends Block 
{
	protected BlockManaCrystal() 
	{
		super(Material.iron);
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypePiston);
		this.setBlockName("mana_crystal_block");
		this.setBlockTextureName(Archmagus.MODID + ":" + getUnlocalizedName().substring(5));
		this.setLightLevel(0.625F);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
}
