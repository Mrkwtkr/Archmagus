package com.agadar.archmagus.spells;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.world.World;

/** Lightning strikes all non-allied creatures in the area. */
public class SpellLightningStorm extends SpellAoE {

	public SpellLightningStorm(int par1) 
	{
		super(par1);
		this.setName("lightningstorm");
	}
	
	@Override
	public int getHungerCost()
    {
    	return 8;
    }

	@Override
	protected void affectEntity(World par1World, EntityLivingBase par2EntityLivingBase) 
	{
		par1World.spawnEntityInWorld(new EntityLightningBolt(par1World, par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ));
		par2EntityLivingBase.knockBack(par2EntityLivingBase, 0F, 1F, 0F);
	}
}
