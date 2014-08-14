package com.agadar.archmagus.itemblock;

import java.util.Random;

import com.agadar.archmagus.misc.References;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class BlockManaCrystalOre extends Block 
{
	private Random rand = new Random();
	
	protected BlockManaCrystalOre() 
	{
		super(Material.rock);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypePiston);
		this.setBlockName("mana_crystal_ore");
		this.setBlockTextureName(References.MODID + ":" + getUnlocalizedName().substring(5));
		this.setCreativeTab(ModItemsBlocks.tabArchmagus);
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
		return ModItemsBlocks.mana_crystal;
    }
	
    @Override
    public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_)
    {
        if (this.getItemDropped(p_149690_5_, rand, p_149690_7_) != Item.getItemFromBlock(this))
        	return MathHelper.getRandomIntegerInRange(rand, 2, 5);
        
        return 0;
    }
}
