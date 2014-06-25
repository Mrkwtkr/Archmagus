package com.agadar.archmagus.items;

import java.util.List;

import com.agadar.archmagus.help.RegisterHelper;
import com.agadar.archmagus.spells.Spell;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems 
{
	// The Spell Tome
	public final static Item spell_tome = new ItemSpellTome();
	
	// Conjured Tools/Weapons Materials
	public final static ToolMaterial CONJ_TOOL_1 = EnumHelper.addToolMaterial("CONJURED_1", 0, 59, 2.0F, 0.0F, 0);
	public final static ToolMaterial CONJ_TOOL_2 = EnumHelper.addToolMaterial("CONJURED_2", 1, 59, 4.0F, 1.0F, 0);
	public final static ToolMaterial CONJ_TOOL_3 = EnumHelper.addToolMaterial("CONJURED_3", 2, 59, 6.0F, 2.0F, 0);
	public final static ToolMaterial CONJ_TOOL_4 = EnumHelper.addToolMaterial("CONJURED_4", 3, 59, 8.0F, 3.0F, 0);
	
	// Conjured Armor Materials
	public final static ArmorMaterial CONJ_ARMOR_1 = EnumHelper.addArmorMaterial("CONJ_ARMOR_1", 5, new int[]{1, 3, 2, 1}, 0);
	public final static ArmorMaterial CONJ_ARMOR_2 = EnumHelper.addArmorMaterial("CONJ_ARMOR_2", 5, new int[]{2, 6, 5, 2}, 0);
	public final static ArmorMaterial CONJ_ARMOR_3 = EnumHelper.addArmorMaterial("CONJ_ARMOR_3", 5, new int[]{3, 8, 6, 3}, 0);
			
	// Conjured Tools/Weapons
	public final static Item conjured_axe_1 = new ItemConjuredAxe(CONJ_TOOL_1, "conjured_axe_1");
	public final static Item conjured_axe_2 = new ItemConjuredAxe(CONJ_TOOL_2, "conjured_axe_2");
	public final static Item conjured_axe_3 = new ItemConjuredAxe(CONJ_TOOL_3, "conjured_axe_3");
	public final static Item conjured_axe_4 = new ItemConjuredAxe(CONJ_TOOL_4, "conjured_axe_4");
	public final static Item conjured_bow = new ItemConjuredBow("conjured_bow");
	public final static Item conjured_hoe = new ItemConjuredHoe(CONJ_TOOL_1, "conjured_hoe");
	public final static Item conjured_pickaxe_1 = new ItemConjuredPickaxe(CONJ_TOOL_1, "conjured_pickaxe_1");
	public final static Item conjured_pickaxe_2 = new ItemConjuredPickaxe(CONJ_TOOL_2, "conjured_pickaxe_2");
	public final static Item conjured_pickaxe_3 = new ItemConjuredPickaxe(CONJ_TOOL_3, "conjured_pickaxe_3");
	public final static Item conjured_pickaxe_4 = new ItemConjuredPickaxe(CONJ_TOOL_4, "conjured_pickaxe_4");
	public final static Item conjured_shovel_1 = new ItemConjuredShovel(CONJ_TOOL_1, "conjured_shovel_1");
	public final static Item conjured_shovel_2 = new ItemConjuredShovel(CONJ_TOOL_2, "conjured_shovel_2");
	public final static Item conjured_shovel_3 = new ItemConjuredShovel(CONJ_TOOL_3, "conjured_shovel_3");
	public final static Item conjured_shovel_4 = new ItemConjuredShovel(CONJ_TOOL_4, "conjured_shovel_4");
	public final static Item conjured_sword_1 = new ItemConjuredSword(CONJ_TOOL_1, "conjured_sword_1");
	public final static Item conjured_sword_2 = new ItemConjuredSword(CONJ_TOOL_2, "conjured_sword_2");
	public final static Item conjured_sword_3 = new ItemConjuredSword(CONJ_TOOL_3, "conjured_sword_3");
	public final static Item conjured_sword_4 = new ItemConjuredSword(CONJ_TOOL_4, "conjured_sword_4");
	
	// Conjured Armors
	public static Item conjured_helmet_1 = new ItemConjuredArmor(0, "conjured_helmet_1", CONJ_ARMOR_1);
	public static Item conjured_helmet_2 = new ItemConjuredArmor(0, "conjured_helmet_2", CONJ_ARMOR_2);
	public static Item conjured_helmet_3 = new ItemConjuredArmor(0, "conjured_helmet_3", CONJ_ARMOR_3);
	public static Item conjured_chestplate_1 = new ItemConjuredArmor(1, "conjured_chestplate_1", CONJ_ARMOR_1);
	public static Item conjured_chestplate_2 = new ItemConjuredArmor(1, "conjured_chestplate_2", CONJ_ARMOR_2);
	public static Item conjured_chestplate_3 = new ItemConjuredArmor(1, "conjured_chestplate_3", CONJ_ARMOR_3);
	public static Item conjured_leggings_1 = new ItemConjuredArmor(2, "conjured_leggings_1", CONJ_ARMOR_1);
	public static Item conjured_leggings_2 = new ItemConjuredArmor(2, "conjured_leggings_2", CONJ_ARMOR_2);
	public static Item conjured_leggings_3 = new ItemConjuredArmor(2, "conjured_leggings_3", CONJ_ARMOR_3);
	public static Item conjured_boots_1 = new ItemConjuredArmor(3, "conjured_boots_1", CONJ_ARMOR_1);
	public static Item conjured_boots_2 = new ItemConjuredArmor(3, "conjured_boots_2", CONJ_ARMOR_2);
	public static Item conjured_boots_3 = new ItemConjuredArmor(3, "conjured_boots_3", CONJ_ARMOR_3);
	
	public static void loadItems()
	{
		RegisterHelper.registerItem(spell_tome);
		RegisterHelper.registerItem(conjured_axe_1);
		RegisterHelper.registerItem(conjured_axe_2);
		RegisterHelper.registerItem(conjured_axe_3);
		RegisterHelper.registerItem(conjured_axe_4);
		RegisterHelper.registerItem(conjured_bow);
		RegisterHelper.registerItem(conjured_hoe);
		RegisterHelper.registerItem(conjured_pickaxe_1);
		RegisterHelper.registerItem(conjured_pickaxe_2);
		RegisterHelper.registerItem(conjured_pickaxe_3);
		RegisterHelper.registerItem(conjured_pickaxe_4);
		RegisterHelper.registerItem(conjured_shovel_1);
		RegisterHelper.registerItem(conjured_shovel_2);
		RegisterHelper.registerItem(conjured_shovel_3);
		RegisterHelper.registerItem(conjured_shovel_4);
		RegisterHelper.registerItem(conjured_sword_1);
		RegisterHelper.registerItem(conjured_sword_2);
		RegisterHelper.registerItem(conjured_sword_3);
		RegisterHelper.registerItem(conjured_sword_4);
		RegisterHelper.registerItem(conjured_helmet_1);
		RegisterHelper.registerItem(conjured_helmet_2);
		RegisterHelper.registerItem(conjured_helmet_3);
		RegisterHelper.registerItem(conjured_chestplate_1);
		RegisterHelper.registerItem(conjured_chestplate_2);
		RegisterHelper.registerItem(conjured_chestplate_3);
		RegisterHelper.registerItem(conjured_leggings_1);
		RegisterHelper.registerItem(conjured_leggings_2);
		RegisterHelper.registerItem(conjured_leggings_3);
		RegisterHelper.registerItem(conjured_boots_1);
		RegisterHelper.registerItem(conjured_boots_2);
		RegisterHelper.registerItem(conjured_boots_3);
	}
	
	// The Creative Tab
 	public final static CreativeTabs tabSpellTomes = new CreativeTabs("tabSpellTomes") 
	{
	    @Override
	    @SideOnly(Side.CLIENT)
	    public Item getTabIconItem() { return ModItems.spell_tome; }
	
	    @SuppressWarnings("rawtypes")
		@SideOnly(Side.CLIENT)
	    public void displayAllReleventItems(List itemList) 
	    {       
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.blazefire, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.ghastfire, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.witherblast, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.lightning, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.conjure_axe, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.conjure_bow, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.conjure_hoe, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.conjure_pickaxe, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.conjure_shovel, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.conjure_sword, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.conjure_armor, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.summon_arcane_wolf, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.raise_skeleton, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.raise_wither_skeleton, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.teleport, itemList);
	    	((ItemSpellTome) ModItems.spell_tome).func_92113_a(Spell.respawn, itemList);
	    }
	};
}
