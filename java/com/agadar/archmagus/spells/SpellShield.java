package com.agadar.archmagus.spells;

import com.agadar.archmagus.potions.ModPotions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/** Summons a defensive magical shield. We currently have the following shields:
 *  Fire Shield, which causes damage and sets fire to enemies whenever they hit the caster;
 *  Earthen Shield, which reduces the caster's damage taken by 25%, and makes him immune to knockback;
 *  Water Shield, which heals the caster whenever he is hit, and makes him immune to fire;
 *  Aether Shield, which makes the caster immune to projectiles, and increases his running speed;
 *  Frost Armor, which causes slowness and weakness to enemies whenever they hit the caster. */
public class SpellShield extends Spell
{
	/** The type of shield that is applied when this spell is cast. */
	private final Potion shieldType;
	/** The duration/cooldown of all shields in ticks. */
	private final static short duration = 1200;
	
	protected SpellShield(int par1, Potion par2ShieldType, String par2Name) 
	{
		super(par1);
		this.shieldType = par2ShieldType;
		this.setName("shield." + par2Name);
	}
	
	@Override
	public int getHungerCost()
    {
    	return 4;
    }
	
	@Override
	public short getCooldown()
	{
		return duration;
	}

	@Override
	public boolean castSpell(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{
		if (!par2World.isRemote)
		{
			clearShields(par3EntityPlayer);
			par3EntityPlayer.addPotionEffect(new PotionEffect(shieldType.getId(), duration, 0));			
		}
		
		return true;
	}
	
	/** Removes all shield effects from the given EntityPlayer.
	 *  Should be called every time before a new shield is applied so that
	 *  a player cannot have more than one type of shield on him at a time. */
	private static void clearShields(EntityPlayer par1EntityPlayer)
	{
		par1EntityPlayer.removePotionEffect(ModPotions.fireShield.getId());
		par1EntityPlayer.removePotionEffect(ModPotions.earthenShield.getId());
		par1EntityPlayer.removePotionEffect(ModPotions.waterShield.getId());
		par1EntityPlayer.removePotionEffect(ModPotions.aetherShield.getId());
		par1EntityPlayer.removePotionEffect(ModPotions.frostArmor.getId());
	}
}
