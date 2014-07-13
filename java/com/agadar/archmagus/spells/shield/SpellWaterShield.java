package com.agadar.archmagus.spells.shield;

import net.minecraft.potion.Potion;

import com.agadar.archmagus.potions.ModPotions;

/** Summons the Water Shield. */
public class SpellWaterShield extends SpellShield 
{
	public SpellWaterShield(int par1) 
	{
		super(par1, "water");
	}
	
	@Override
	public String getSoundName()
	{
		return "ambient.weather.rain";
	}

	@Override
	public Potion getShieldEffect() 
	{
		return ModPotions.waterShield;
	}
}
