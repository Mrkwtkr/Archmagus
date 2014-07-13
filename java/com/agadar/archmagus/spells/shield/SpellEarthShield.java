package com.agadar.archmagus.spells.shield;

import net.minecraft.potion.Potion;

import com.agadar.archmagus.potions.ModPotions;

/** Summons the Earth Shield. */
public class SpellEarthShield extends SpellShield 
{
	public SpellEarthShield(int par1) 
	{
		super(par1, "earth");
	}

	@Override
	public Potion getShieldEffect() 
	{
		return ModPotions.earthShield;
	}
}
