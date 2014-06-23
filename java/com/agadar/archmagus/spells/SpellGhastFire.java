package com.agadar.archmagus.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class SpellGhastFire extends Spell 
{
	public SpellGhastFire(int par1) 
	{
		super(par1);
		this.setName("ghastFire");
	}
	
	@Override
    public int getHungerCost()
    {
    	return 2;
    }

	@Override
	public void cast(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{
		par2World.playAuxSFXAtEntity((EntityPlayer)null, 1008, (int)par3EntityPlayer.posX, (int)par3EntityPlayer.posY, (int)par3EntityPlayer.posZ, 0);
		Vec3 v3 = par3EntityPlayer.getLook(1);
		
		for (int i = 0; i < par1Level; i++)
		{
			EntityLargeFireball largefireball = new EntityLargeFireball(par2World, par3EntityPlayer.posX, par3EntityPlayer.posY + par3EntityPlayer.eyeHeight, par3EntityPlayer.posZ, v3.xCoord + random.nextGaussian() / 6, v3.yCoord, v3.zCoord + random.nextGaussian() / 6);
			largefireball.shootingEntity = par3EntityPlayer;
			par2World.spawnEntityInWorld(largefireball);
		}
	}
}
