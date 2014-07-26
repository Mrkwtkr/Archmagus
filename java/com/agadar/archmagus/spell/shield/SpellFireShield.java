package com.agadar.archmagus.spell.shield;

import com.agadar.archmagus.potion.ModPotions;

import net.minecraft.potion.Potion;

/** Summons the Fire Shield. */
public class SpellFireShield extends SpellShield 
{
	public SpellFireShield(int par1) 
	{
		super(par1, "fire");
	}

	@Override
	public Potion getShieldEffect() 
	{
		return ModPotions.fireShield;
	}
}
