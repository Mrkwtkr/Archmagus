package com.agadar.archmagus.spell.targeted;

import com.agadar.archmagus.spell.Spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/** Fires a large fireball. */
public class SpellGhastFire extends Spell implements ISpellTargeted
{
	public SpellGhastFire(int par1) 
	{
		super(par1);
		this.setName("ghastfire");
	}
	
	@Override
    public int getHungerCost()
    {
    	return 3;
    }
	
	@Override
	public short getCooldown()
	{
		return 40;
	}
	
	@Override
	public String getParticleName()
	{
		return "flame";
	}
	
	@Override
	public double getParticleAmount()
	{
		return 0.3;
	}

	@Override
	public boolean castSpell(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{
		if (!par2World.isRemote)
		{
			par2World.playSoundAtEntity(par3EntityPlayer, this.getSoundName(), 1.0F, 1.0F);
			
			Vec3 v3 = par3EntityPlayer.getLook(1);
			int accuracy = 10;
			EntityLargeFireball largefireball = new EntityLargeFireball(par2World, par3EntityPlayer.posX, par3EntityPlayer.posY + par3EntityPlayer.eyeHeight, par3EntityPlayer.posZ, v3.xCoord + random.nextGaussian() / accuracy, v3.yCoord, v3.zCoord + random.nextGaussian() / accuracy);
			largefireball.shootingEntity = par3EntityPlayer;
			par2World.spawnEntityInWorld(largefireball);
		}
		
		return true;
	}
}
