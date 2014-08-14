package com.agadar.archmagus.itemblock;

import com.agadar.archmagus.Archmagus;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/** Responsible for instantiating and registering this mod's items and blocks. */
public class ModItemsBlocks 
{
	/** This mod's creative tab. */
 	public final static CreativeTabs tabArchmagus = new CreativeTabs("tabArchmagus") 
	{
	    @Override
	    @SideOnly(Side.CLIENT)
	    public Item getTabIconItem() 
	    { 
	    	return ModItemsBlocks.spell_book; 
	    }
	};
	
	/** The spell book. */
	public final static Item spell_book = new ItemSpellBook();
	/** The mana apple. */
	public final static Item apple_mana = new ItemAppleMana();
	/** The mana crystal. */
	public final static Item mana_crystal = new Item().setUnlocalizedName("mana_crystal").setCreativeTab(tabArchmagus).setTextureName(Archmagus.MODID + ":" + "mana_crystal");

	/** The mana crystal ore. */
	public final static Block mana_crystal_ore = new BlockManaCrystalOre();
	/** The mana crystal block. */
	
	
	/** Instantiates and registers this mod's items and blocks. */
	public static void registerModItemsAndBlocks()
	{
		/** Register the items. */
		registerItem(spell_book);
		registerItem(apple_mana);
		registerItem(mana_crystal);	
		
		/** Register the blocks. */
		registerBlock(mana_crystal_ore);
		
		/** Register the crafting recipes. */
		GameRegistry.addRecipe(new ItemStack(apple_mana), "xxx", "xyx", "xxx", 'x', mana_crystal, 'y', Items.apple);
	}
	
	/**
     * Registers all blocks. The basic format is [MODID_NAME]
     * When you call this method, with your block assigned, it will take care of everything.
     * @param block
     */
	private static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, Archmagus.MODID + "_" + block.getUnlocalizedName().substring(5));
	}

    /**
     * Registers all items. The basic format is [MODID_NAME]
     * When you call this method, with your item assigned, it will take care of everything.
     * @param item
     */
	private static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, Archmagus.MODID + "_" + item.getUnlocalizedName().substring(5));
	}
}
