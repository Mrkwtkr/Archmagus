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
	    	((ItemSpellBook) ModItems.spell_book).func_92113_a(Spells.blazefire, itemList);
	    	((ItemSpellBook) ModItems.spell_book).func_92113_a(Spells.ghastfire, itemList);
	    	((ItemSpellBook) ModItems.spell_book).func_92113_a(Spells.witherblast, itemList);
	    	((ItemSpellBook) ModItems.spell_book).func_92113_a(Spells.lightning, itemList);
	    	((ItemSpellBook) ModItems.spell_book).func_92113_a(Spells.summon_wolf, itemList);
	    	((ItemSpellBook) ModItems.spell_book).func_92113_a(Spells.raise_skeleton, itemList);
	    	((ItemSpellBook) ModItems.spell_book).func_92113_a(Spells.raise_wither_skeleton, itemList);
	    	((ItemSpellBook) ModItems.spell_book).func_92113_a(Spells.raise_zombie, itemList);
	    	((ItemSpellBook) ModItems.spell_book).func_92113_a(Spells.raise_zombie_pigman, itemList);
	    	((ItemSpellBook) ModItems.spell_book).func_92113_a(Spells.summon_witch, itemList);
	    	((ItemSpellBook) ModItems.spell_book).func_92113_a(Spells.summon_spider, itemList);
	    	((ItemSpellBook) ModItems.spell_book).func_92113_a(Spells.summon_cave_spider, itemList);
	    	((ItemSpellBook) ModItems.spell_book).func_92113_a(Spells.teleport, itemList);
	    	((ItemSpellBook) ModItems.spell_book).func_92113_a(Spells.respawn, itemList);
	    }
	};
}
