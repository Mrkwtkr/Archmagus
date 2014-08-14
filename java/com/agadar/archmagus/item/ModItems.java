package com.agadar.archmagus.item;

import com.agadar.archmagus.misc.References;
import com.agadar.archmagus.misc.RegisterHelper;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/** Responsible for instantiating and registering this mod's items. */
public class ModItems 
{
	/** The creative tab where all possible spell books are located. */
 	public final static CreativeTabs tabSpellBooks = new CreativeTabs("tabSpellBooks") 
	{
	    @Override
	    @SideOnly(Side.CLIENT)
	    public Item getTabIconItem() 
	    { 
	    	return ModItems.spell_book; 
	    }
	};
	
	/** The spell book. */
	public final static Item spell_book = new ItemSpellBook();
	/** The mana apple. */
	public final static Item apple_mana = new ItemAppleMana();
	/** The mana crystal. */
	public final static Item mana_crystal = new Item().setUnlocalizedName("mana_crystal").setCreativeTab(tabSpellBooks).setTextureName(References.MODID + ":" + "mana_crystal");

	/** Instantiates and registers this mod's items. */
	public static void registerModItems()
	{
		/** Register the items. */
		RegisterHelper.registerItem(spell_book);
		RegisterHelper.registerItem(apple_mana);
		RegisterHelper.registerItem(mana_crystal);
		
		/** Register the crafting recipes. */
		GameRegistry.addRecipe(new ItemStack(apple_mana), "xxx", "xyx", "xxx", 'x', mana_crystal, 'y', Items.apple);
	}
}
