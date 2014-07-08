package com.agadar.archmagus.potions;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.agadar.archmagus.items.PotionShapeshift;

import net.minecraft.potion.Potion;

public class ModPotions 
{
	/** The potion that is applied when a player is polymorphed. */
	public final static Potion shapeshiftPotion;
	
	static
	{
		openUpPotionTypes();		
		int id = 32;
		shapeshiftPotion = new PotionShapeshift(id++).setIconIndex(0, 0).setPotionName("potion.shapeshift");
	}
	
	/** Calling this method allows us to register new Potions
	 *  and mod existing Potions. */
	private static void openUpPotionTypes()
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
}
