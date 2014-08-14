package com.agadar.archmagus.itemblock;

import java.util.Random;

import com.agadar.archmagus.Archmagus;

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
		this.setBlockTextureName(Archmagus.MODID + ":" + getUnlocalizedName().substring(5));
		this.setLightLevel(0.625F);
		this.setCreativeTab(ModItemsBlocks.tabArchmagus);
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
		return ModItemsBlocks.mana_crystal;
    }
	
	@Override
	public int quantityDroppedWithBonus(int p_149679_1_, Random p_149679_2_)
    {
        if (p_149679_1_ > 0 && Item.getItemFromBlock(this) != ModItemsBlocks.mana_crystal)
        {
            int j = p_149679_2_.nextInt(p_149679_1_ + 2) - 1;

            if (j < 0)
	            j = 0;

            return (j + 1);
        }
        else
        	return 1;
    }
	
    @Override
    public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_)
    {
        if (ModItemsBlocks.mana_crystal != Item.getItemFromBlock(this))
        	return MathHelper.getRandomIntegerInRange(rand, 2, 5);
        
        return 0;
    }
}
