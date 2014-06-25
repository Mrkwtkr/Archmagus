package com.agadar.archmagus.spells;

import java.lang.reflect.Constructor;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.entity.passive.EntityTameable;

public class SpellSummon extends Spell 
{
	/** The constructor of the entity this spell summons. 
	 *  It is assumed the only parameter is a World reference. */
	@SuppressWarnings("rawtypes")
	private final Constructor entityConstr;
	
	@SuppressWarnings({ "rawtypes" })
	public SpellSummon(int par1, String name, Class entityClass)
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
        return 3;
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
	public void cast(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{
		try 
		{
			for (int i = 0; i < getNormalizedLevel(par1Level); i++)
			{
				EntityCreature entity = (EntityCreature) entityConstr.newInstance(par2World);
				entity.setLocationAndAngles(par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, MathHelper.wrapAngleTo180_float(par2World.rand.nextFloat() * 360.0F), 0.0F);
				entity.rotationYawHead = entity.rotationYaw;
				entity.renderYawOffset = entity.rotationYaw;
				((EntityTameable) entity).setOwner(par3EntityPlayer.getCommandSenderName());
				
				par2World.spawnEntityInWorld(entity);
			}	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
