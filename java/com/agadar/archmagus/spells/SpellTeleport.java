package com.agadar.archmagus.spells;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SpellTeleport extends Spell 
{
	/**
	 * Holds the distances this spell can teleport a player, where the distance with 
	 * index 0 is the distance the player can teleport if the spell is level 1, 
	 * and where the distance with index 1 is the distance the player can teleport if
	 * the spell is level 2, etcetera.
	 */
	private final int[] distances = { 10, 15, 20};
	
	protected SpellTeleport(int par1) 
	{
		super(par1);
		this.setName("teleport");
	}
	
    @Override
    public int getHungerCost()
    {
    	return 4;
    }
    
    @Override
    public short getCooldown()
    {
    	return 200;
    }
    
    @Override
    public int getMaxLevel()
    {
        return distances.length;
    }

	@Override
	public boolean cast(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{
		if (!par2World.isRemote)
		{
			int distance = distances[getNormalizedLevel(par1Level) - 1];
			Minecraft mc = Minecraft.getMinecraft();

			if((mc.renderViewEntity.rayTrace(distance, 1.0F) != null))
			{
				int blockHitX = mc.renderViewEntity.rayTrace(distance, 1.0F).blockX;
				int blockHitY = mc.renderViewEntity.rayTrace(distance, 1.0F).blockY;
				int blockHitZ = mc.renderViewEntity.rayTrace(distance, 1.0F).blockZ;
				par3EntityPlayer.setPositionAndUpdate(blockHitX, blockHitY + 1, blockHitZ);
			} 
			else
			{
				return false;
			}
		}
		return true;
	}
}
