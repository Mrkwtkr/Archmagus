package com.agadar.archmagus.spell.shield;

import net.minecraft.potion.Potion;

import com.agadar.archmagus.potion.ModPotions;

/** Summons the Frost Shield. */
public class SpellFrostShield extends SpellShield 
{
	public SpellFrostShield(int par1) 
	{
		super(par1, "frost");
	}

	@Override
	public Potion getShieldEffect() 
	{
		return ModPotions.frostShield;
	}
}
