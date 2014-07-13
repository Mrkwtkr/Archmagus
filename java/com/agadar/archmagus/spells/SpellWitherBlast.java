package com.agadar.archmagus.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/** Fires a Wither Skull. */
public class SpellWitherBlast extends Spell 
{
	protected SpellWitherBlast(int par1) 
	{
		super(par1);
		this.setName("witherblast");
	}
	
	@Override
    public int getHungerCost()
    {
    	return 4;
    }
	
	@Override
	public short getCooldown()
	{
		return 40;
	}
	
	@Override
	public double getParticleAmount()
	{
		return 0.5;
	}
	
	@Override
	public String getParticleName()
	{
		return "smoke";
	}
	
	@Override
	public String getSoundName()
	{
		return "mob.wither.shoot";
	}

	@Override
	public boolean castSpell(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{     	
		if (!par2World.isRemote)
		{
			par2World.playSoundAtEntity(par3EntityPlayer, this.getSoundName(), 1.0F, 1.0F);
			
			Vec3 v3 = par3EntityPlayer.getLook(1);
			int accuracy = 10;
			EntityWitherSkull entitywitherskull = new EntityWitherSkull(par2World, par3EntityPlayer.posX, par3EntityPlayer.posY + par3EntityPlayer.eyeHeight, par3EntityPlayer.posZ, v3.xCoord + random.nextGaussian() / accuracy, v3.yCoord, v3.zCoord + random.nextGaussian() / accuracy);
			entitywitherskull.shootingEntity = par3EntityPlayer;
			par2World.spawnEntityInWorld(entitywitherskull);

		}
		
		return true;
	}
}
