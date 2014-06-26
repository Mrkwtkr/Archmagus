package com.agadar.archmagus.spells;

import java.lang.reflect.Constructor;
import java.util.List;

import com.agadar.archmagus.entities.EntitySummonable;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.entity.passive.EntityTameable;

public class SpellSummon extends Spell 
{
	/** The constructor of the entity this spell summons. 
	 *  It is assumed the only parameter is a World reference. */
	@SuppressWarnings("rawtypes")
	private final Constructor entityConstr;
	
	@SuppressWarnings({ "rawtypes" })
	protected SpellSummon(int par1, String name, Class entityClass)
	{
		super(par1);
		this.setName(name);	
		entityConstr = getConstructor(entityClass);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Constructor getConstructor(Class entityClass)
	{
		try 
		{
			return entityClass.getConstructor(World.class);
		} 
		catch (NoSuchMethodException | SecurityException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int getMaxLevel()
    {
        return 4;
    }
	
	@Override
	public int getHungerCost()
    {
    	return 8;
    }
	
	@Override
	public short getCooldown()
	{
		return 1200;
	}

	@Override
	public boolean cast(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{
		if (!par2World.isRemote)
		{
			@SuppressWarnings("unchecked")
			List<EntitySummonable> entities = par2World.getEntitiesWithinAABB(EntitySummonable.class, par3EntityPlayer.boundingBox.expand(10.0D, 10.0D, 10.0D));
			
			for (EntitySummonable entity : entities)
			{
				if (entity.getOwnerName().equalsIgnoreCase(par3EntityPlayer.getCommandSenderName()))
				{
					entity.attackEntityFrom(DamageSource.generic, entity.getMaxHealth());
				}
			}
			
			try 
			{
				int[] xSpawnOffset = { -2, 0, 2, 0 };
				int[] zSpawnOffset = { 0, 2, 0, -2 };
				
				for (int i = 0; i < getNormalizedLevel(par1Level); i++)
				{
					EntityCreature entity = (EntityCreature) entityConstr.newInstance(par2World);
					entity.setLocationAndAngles(par3EntityPlayer.posX + xSpawnOffset[i], par3EntityPlayer.posY, par3EntityPlayer.posZ + zSpawnOffset[i], entity.rotationYaw, 0.0F);					
					String comSendName = par3EntityPlayer.getCommandSenderName();
					((EntityTameable) entity).setOwner(comSendName);
					entity.setCustomNameTag(comSendName + "'s Minion");
					entity.setAlwaysRenderNameTag(true);
					par2World.spawnEntityInWorld(entity);
				}	
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
}
