package com.agadar.archmagus.spell.shield;

import com.agadar.archmagus.potion.ModPotions;
import com.agadar.archmagus.spell.Spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/** Summons a defensive magical shield. We currently have the following shields:
 *  Fire Shield, which causes damage and sets fire to attackers when he is hit;
 *  Earth Shield, which gives the caster the resistance and knockback immunity effects when he is hit;
 *  Water Shield, which gives the caster the regeneration and fire resistance effects when he is hit;
 *  Storm Shield, which gives the caster the speed and projectile immunity effects when he is hit;
 *  Frost Shield, which causes the slowness and weakness effects to attackers when he is hit; */
public abstract class SpellShield extends Spell
{
	protected SpellShield(int par1, String par2Name) 
	{
		super(par1);
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
		return 1200;
	}
	
	@Override
	public short getMaxLevel()
    {
        return 3;
    }
	
	/** Returns the shield (potion) effect applied to the caster when this shield spell is cast. */
	public abstract Potion getShieldEffect();

	@Override
	public boolean castSpell(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{
		if (!par2World.isRemote)
		{
			par2World.playSoundAtEntity(par3EntityPlayer, this.getSoundName(), 1.0F, 1.0F);
			clearShields(par3EntityPlayer);
			par3EntityPlayer.addPotionEffect(new PotionEffect(this.getShieldEffect().getId(), 12000, par1Level - 1));			
		}
		
		return true;
	}
	
	/** Removes all shield effects from the given EntityPlayer.
	 *  Should be called every time before a new shield is applied so that
	 *  a player cannot have more than one type of shield on him at a time. */
	private static void clearShields(EntityPlayer par1EntityPlayer)
	{
		par1EntityPlayer.removePotionEffect(ModPotions.fireShield.getId());
		par1EntityPlayer.removePotionEffect(ModPotions.earthShield.getId());
		par1EntityPlayer.removePotionEffect(ModPotions.waterShield.getId());
		par1EntityPlayer.removePotionEffect(ModPotions.stormShield.getId());
		par1EntityPlayer.removePotionEffect(ModPotions.frostShield.getId());
	}
}
