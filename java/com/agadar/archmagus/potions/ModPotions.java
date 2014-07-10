package com.agadar.archmagus.potions;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.potion.Potion;

/** Responsible for instantiating this mod's Potions. */
public class ModPotions 
{
	/** The potion that is applied when a player is polymorphed. */
	public final static Potion polymorphed;
	/** The potions that are applied when a player casts a Shield spell. */
	public final static Potion fireShield;
	public final static Potion earthenShield;
	public final static Potion waterShield;
	public final static Potion aetherShield;
	public final static Potion frostArmor;
	/** The potion that is applied when a player is Feared. */
	public final static Potion feared;
	/** The potion that is applied when a player is made immune to projectiles. */
	public final static Potion projectileImmunity;
	/** The potion that is applied when a player is made immune to knockback. */
	public final static Potion knockbackImmunity;
	
	static
	{
		openUpPotionTypes();		
		int id = 32;
		polymorphed = new PotionBase(id++).setIconIndex(0, 0).setPotionName("potion.polymorphed");
		fireShield = new PotionBase(id++).setIconIndex(1, 0).setPotionName("potion.shield.fire");
		earthenShield = new PotionBase(id++).setIconIndex(2, 0).setPotionName("potion.shield.earthen");
		waterShield = new PotionBase(id++).setIconIndex(3, 0).setPotionName("potion.shield.water");
		aetherShield = new PotionBase(id++).setIconIndex(4, 0).setPotionName("potion.shield.aether");
		frostArmor = new PotionBase(id++).setIconIndex(5, 0).setPotionName("potion.shield.frost");
		feared = new PotionBase(id++).setIconIndex(6, 0).setPotionName("potion.feared");
		projectileImmunity = new PotionBase(id++).setIconIndex(7, 0).setPotionName("potion.immunity.projectile");
		knockbackImmunity = new PotionBase(id++).setIconIndex(0, 1).setPotionName("potion.immunity.knockback");
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
