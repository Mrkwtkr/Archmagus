package com.agadar.archmagus.items;

import com.agadar.archmagus.help.RegisterHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ModItems 
{
	// The Creative Tab
	public final static CreativeTabs tabArchmagus = new CreativeTabs("tabArchmagus") 
	{
	    @Override
	    @SideOnly(Side.CLIENT)
	    public Item getTabIconItem() { return Items.enchanted_book; }
	};
	
	// Spell Tomes
		public final static Item blazefire_tome = new ItemBlazefireTome();
	
	public static void loadItems()
	{
		RegisterHelper.registerItem(blazefire_tome);
	}
}
