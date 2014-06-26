package com.agadar.archmagus.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class SpellGhastFire extends Spell 
{
	protected SpellGhastFire(int par1) 
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
	public int getParticleAmount()
	{
		return 15;
	}

	@Override
	public boolean castSpell(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{
		par2World.playAuxSFXAtEntity((EntityPlayer)null, 1008, (int)par3EntityPlayer.posX, (int)par3EntityPlayer.posY, (int)par3EntityPlayer.posZ, 0);
		
		if (!par2World.isRemote)
		{
			Vec3 v3 = par3EntityPlayer.getLook(1);
			int accuracy = 10;
			EntityLargeFireball largefireball = new EntityLargeFireball(par2World, par3EntityPlayer.posX, par3EntityPlayer.posY + par3EntityPlayer.eyeHeight, par3EntityPlayer.posZ, v3.xCoord + random.nextGaussian() / accuracy, v3.yCoord, v3.zCoord + random.nextGaussian() / accuracy);
			largefireball.shootingEntity = par3EntityPlayer;
			par2World.spawnEntityInWorld(largefireball);
		}
		
		return true;
	}
}
