package com.agadar.archmagus.items;

import java.util.List;

import com.agadar.archmagus.help.RegisterHelper;
import com.agadar.archmagus.spells.Spell;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems 
{
	// The Spell Tome
	public final static Item spell_tome = new ItemSpellTome();
	
	// Conjured Tools Materials
	public final static ToolMaterial CONJURED_1 = EnumHelper.addToolMaterial("CONJURED_1", 0, 59, 2.0F, 0.0F, 0);
	public final static ToolMaterial CONJURED_2 = EnumHelper.addToolMaterial("CONJURED_2", 1, 59, 4.0F, 1.0F, 0);
	public final static ToolMaterial CONJURED_3 = EnumHelper.addToolMaterial("CONJURED_3", 2, 59, 6.0F, 2.0F, 0);
	public final static ToolMaterial CONJURED_4 = EnumHelper.addToolMaterial("CONJURED_4", 3, 59, 8.0F, 3.0F, 0);
	
	// Conjured Tools
	public final static Item conjured_sword_1 = new ItemConjuredSword(CONJURED_1, "conjured_sword_1");
	public final static Item conjured_sword_2 = new ItemConjuredSword(CONJURED_2, "conjured_sword_2");
	public final static Item conjured_sword_3 = new ItemConjuredSword(CONJURED_3, "conjured_sword_3");
	public final static Item conjured_sword_4 = new ItemConjuredSword(CONJURED_4, "conjured_sword_4");
	
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
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.blazeFire, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.ghastFire, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.conjureSword, itemList);
	    }
	};
	
	public static void loadItems()
	{
		RegisterHelper.registerItem(spell_tome);
		RegisterHelper.registerItem(conjured_sword_1);
		RegisterHelper.registerItem(conjured_sword_2);
		RegisterHelper.registerItem(conjured_sword_3);
		RegisterHelper.registerItem(conjured_sword_4);
	}
}
