package com.agadar.archmagus.items;

import java.util.List;

import com.agadar.archmagus.help.RegisterHelper;
import com.agadar.archmagus.spells.Spells;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItems 
{
	// The Spell Book
	public final static Item spell_book = new ItemSpellBook();

	public static void loadItems()
	{
		RegisterHelper.registerItem(spell_book);
	}
	
	// The Creative Tab
 	public final static CreativeTabs tabSpellBooks = new CreativeTabs("tabSpellBooks") 
	{
	    @Override
	    @SideOnly(Side.CLIENT)
	    public Item getTabIconItem() 
	    { 
	    	return ModItems.spell_book; 
	    }
	
	    @SuppressWarnings("rawtypes")
		@SideOnly(Side.CLIENT)
	    public void displayAllReleventItems(List itemList) 
	    {       
	    	Spells.getAllSpellBooks(itemList);
	    }
	};
}
