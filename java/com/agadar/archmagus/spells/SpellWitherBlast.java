package com.agadar.archmagus.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

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
	public boolean cast(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{
		par2World.playAuxSFXAtEntity((EntityPlayer)null, 1014, (int)par3EntityPlayer.posX, (int)par3EntityPlayer.posY, (int)par3EntityPlayer.posZ, 0);      
		
		if (!par2World.isRemote)
		{
			Vec3 v3 = par3EntityPlayer.getLook(1);
			int accuracy = 10;
			EntityWitherSkull entitywitherskull = new EntityWitherSkull(par2World, par3EntityPlayer.posX, par3EntityPlayer.posY + par3EntityPlayer.eyeHeight, par3EntityPlayer.posZ, v3.xCoord + random.nextGaussian() / accuracy, v3.yCoord, v3.zCoord + random.nextGaussian() / accuracy);
			entitywitherskull.shootingEntity = par3EntityPlayer;
			par2World.spawnEntityInWorld(entitywitherskull);

		}
		
		return true;
	}
}
