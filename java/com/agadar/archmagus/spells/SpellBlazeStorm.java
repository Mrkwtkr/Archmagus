package com.agadar.archmagus.spells;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class SpellBlazeStorm extends SpellAoE 
{
	protected SpellBlazeStorm(int par1) 
	{
		super(par1);
		this.setName("blazestorm");
	}
	
	@Override
	public boolean castSpell(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{
		if (!par2World.isRemote)
		par2World.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)par3EntityPlayer.posX, (int)par3EntityPlayer.posY, (int)par3EntityPlayer.posZ, 0);
		
		return super.castSpell(par1Level, par2World, par3EntityPlayer);
	}

	@Override
	protected void affectEntity(World par1World, EntityLivingBase par2EntityLivingBase) 
	{
		par2EntityLivingBase.setFire(5);				
		par2EntityLivingBase.attackEntityFrom(DamageSource.onFire, 5F);		
	}
}
