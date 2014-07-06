package com.agadar.archmagus.items;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import com.agadar.archmagus.help.RegisterHelper;
import com.agadar.archmagus.spells.Spells;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

public class ModItems 
{
	/** The spell book. */
	public final static Item spell_book = new ItemSpellBook();
	
	/** The potion that is applied when a player is polymorphed. */
	public static Potion shapeshiftPotion;

	/** Instantiates and registers all items. */
	public static void loadItems()
	{
		openUpPotion();		
		shapeshiftPotion = (new PotionShapeshift(32, false, 0)).setIconIndex(0, 0).setPotionName("potion.shapeshift");
		
		RegisterHelper.registerItem(spell_book);
	}
	
	/** Calling this method allows us to register new potions and potion effects,
	 *  mod existing potions and potion effects, etcetera. */
	private static void openUpPotion()
	{
		Potion[] potionTypes = null;

	    for (Field f : Potion.class.getDeclaredFields()) 
	    {
	        f.setAccessible(true);
	        
	        try 
	        {
	            if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) 
	            {
	                Field modfield = Field.class.getDeclaredField("modifiers");
	                modfield.setAccessible(true);
	                modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

	                potionTypes = (Potion[])f.get(null);
	                final Potion[] newPotionTypes = new Potion[256];
	                System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
	                f.set(null, newPotionTypes);
	            }
	        } 
	        catch (Exception e) 
	        {
	            System.err.println(e);
	        }
	    }
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
