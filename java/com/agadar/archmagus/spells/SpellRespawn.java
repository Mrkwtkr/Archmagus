package com.agadar.archmagus.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class SpellRespawn extends Spell 
{
	protected SpellRespawn(int par1) 
	{
		super(par1);
		this.setName("respawn");
	}
	
	@Override
    public int getHungerCost()
    {
    	return 12;
    }
    
    @Override
    public short getCooldown()
    {
    	return 6000;
    }

	@Override
	public void cast(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{
		if (!par2World.isRemote)
		{
			ChunkCoordinates coordBed = par3EntityPlayer.getBedLocation(0);

			if (coordBed != null)
			{
				par3EntityPlayer.setPositionAndUpdate(coordBed.posX, coordBed.posY + 1, coordBed.posZ);
			}
			else
			{
				ChunkCoordinates coordSpawn = par2World.getSpawnPoint();
				par3EntityPlayer.setPositionAndUpdate(coordSpawn.posX, coordSpawn.posY, coordSpawn.posZ);
			}
		}
	}
}
