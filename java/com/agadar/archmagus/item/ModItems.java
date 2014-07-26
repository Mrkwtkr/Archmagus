package com.agadar.archmagus.item;

import java.util.List;

import com.agadar.archmagus.help.RegisterHelper;
import com.agadar.archmagus.spell.Spells;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/** Responsible for instantiating and registering this mod's items. */
public class ModItems 
{
	/** The spell book. */
	public final static Item spell_book = new ItemSpellBook();

	/** Instantiates and registers this mod's items. */
	public static void loadModItems()
	{
		RegisterHelper.registerItem(spell_book);
	}
	
	/** The creative tab where all possible spell books are located. */
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
