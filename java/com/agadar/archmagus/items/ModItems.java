package com.agadar.archmagus.items;

import java.util.List;

import com.agadar.archmagus.help.RegisterHelper;
import com.agadar.archmagus.spells.Spell;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ModItems 
{
	// The Spell Tome
	public final static Item spellTome = new ItemSpellTome();
	
	// The Creative Tab
	public final static CreativeTabs tabArchmagus = new CreativeTabs("tabArchmagus") 
	{
	    @Override
	    @SideOnly(Side.CLIENT)
	    public Item getTabIconItem() { return Items.enchanted_book; }
	
	    @SuppressWarnings("rawtypes")
		@SideOnly(Side.CLIENT)
	    public void displayAllReleventItems(List itemList) 
	    {       
	    	((ItemSpellTome) ModItems.spellTome).func_92113_a(Spell.blazeFire, itemList);
	    }
	};
	
	public static void loadItems()
	{
		RegisterHelper.registerItem(spellTome);
	}
}
