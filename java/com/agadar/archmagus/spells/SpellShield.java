package com.agadar.archmagus.spells;

import com.agadar.archmagus.potions.ModPotions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SpellShield extends Spell
{
	/** The type of shield that is applied when this spell is cast. */
	private final Potion shieldType;
	/** The duration/cooldown of all shields in ticks. */
	private final static short duration = 1200;
	
	protected SpellShield(int par1, Potion shieldType) 
	{
		super(par1);
		this.shieldType = shieldType;
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
		par1EntityPlayer.removePotionEffect(ModPotions.etherealShield.getId());
		par1EntityPlayer.removePotionEffect(ModPotions.frostArmor.getId());
		par1EntityPlayer.removePotionEffect(ModPotions.divineShield.getId());
		par1EntityPlayer.removePotionEffect(ModPotions.demonArmor.getId());
	}
}
