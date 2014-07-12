package com.agadar.archmagus.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/** Fires a number of small fireballs. */
public class SpellBlazeFire extends Spell 
{
	protected SpellBlazeFire(int par1)
    {
        super(par1);
        this.setName("blazefire");
    }
	
	@Override
    public short getMaxLevel()
    {
        return 3;
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
			par2World.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)par3EntityPlayer.posX, (int)par3EntityPlayer.posY, (int)par3EntityPlayer.posZ, 0);
			
			Vec3 v3 = par3EntityPlayer.getLook(1);
			int[] accuracies = { 10, 8, 6 };

			for (int i = 0; i < getNormalizedLevel(par1Level); i++)
			{
				EntitySmallFireball smallfireball = new EntitySmallFireball(par2World, par3EntityPlayer.posX, par3EntityPlayer.posY + par3EntityPlayer.eyeHeight, par3EntityPlayer.posZ, v3.xCoord + random.nextGaussian() / accuracies[par1Level - 1], v3.yCoord, v3.zCoord + random.nextGaussian() / accuracies[par1Level - 1]);
				smallfireball.shootingEntity = par3EntityPlayer;
				par2World.spawnEntityInWorld(smallfireball);
			}
		}
		return true;
	}
}
