package com.agadar.archmagus.spell.shield;

import net.minecraft.potion.Potion;

import com.agadar.archmagus.potion.ModPotions;

/** Summons the Water Shield. */
public class SpellWaterShield extends SpellShield 
{
	public SpellWaterShield(int par1) 
	{
		super(par1, "water");
	}

	@Override
	public Potion getShieldEffect() 
	{
		return ModPotions.waterShield;
	}
}
