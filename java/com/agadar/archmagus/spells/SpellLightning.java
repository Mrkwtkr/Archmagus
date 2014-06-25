package com.agadar.archmagus.spells;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SpellLightning extends Spell 
{
	protected SpellLightning(int par1) 
	{ 
		super(par1);
		this.setName("lightning");
	}
	
	@Override
    public int getMaxLevel()
    {
        return 3;
    }
	
	@Override
    public int getHungerCost()
    {
    	return 4;
    }
	
	@Override
	public short getCooldown()
	{
		return 60;
	}

	@Override
	public void cast(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{
		int distance = 100;
		Minecraft mc = Minecraft.getMinecraft();
		
		if((mc.renderViewEntity.rayTrace(distance, 1.0F) != null))
		{
			int blockHitX = mc.renderViewEntity.rayTrace(distance, 1.0F).blockX;
			int blockHitY = mc.renderViewEntity.rayTrace(distance, 1.0F).blockY;
			int blockHitZ = mc.renderViewEntity.rayTrace(distance, 1.0F).blockZ;
			
			for (int i = 0; i < getNormalizedLevel(par1Level); i++)
			{
				par2World.spawnEntityInWorld(new EntityLightningBolt(par2World, blockHitX + random.nextGaussian() * 4, blockHitY, blockHitZ + random.nextGaussian() * 4));
			}
		}		
	}
}
