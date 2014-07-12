package com.agadar.archmagus.spells;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/** The archetype of all Area-Of-Effect Spells. */
public abstract class SpellAoE extends Spell 
{
	protected SpellAoE(int par1) 
	{
		super(par1);
	}
	
	@Override
    public short getMaxLevel()
    {
        return 3;
    }
	
	@Override
    public short getCooldown()
    {
    	return 200;
    }
	
	@Override
	public int getHungerCost()
    {
    	return 6;
    }

	@SuppressWarnings("unchecked")
	@Override
	public boolean castSpell(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{
		if (!par2World.isRemote)
		{
			double areaSize = par1Level * 5;
			List<EntityLivingBase> entities = par2World.getEntitiesWithinAABB(EntityLivingBase.class, par3EntityPlayer.boundingBox.expand(areaSize, areaSize, areaSize));
			
			for (EntityLivingBase entity : entities)
			{
				if (!(entity instanceof EntityTameable && ((EntityTameable)entity).getOwner() == par3EntityPlayer))
				{
					affectEntity(par2World, entity);
				}
			}
		}
		
		return true;
	}
	
	/** Called by castSpell(...) for each EntityLiving in the area of effect. */
	protected abstract void affectEntity(World par1World, EntityLivingBase par2EntityLivingBase);
}
