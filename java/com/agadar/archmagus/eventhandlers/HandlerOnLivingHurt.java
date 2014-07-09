package com.agadar.archmagus.eventhandlers;

import com.agadar.archmagus.potions.ModPotions;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/** For applying magical shield effects. */
public class HandlerOnLivingHurt 
{
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if (event.entityLiving.worldObj.isRemote)
		{
			return;
		}

		Entity attacker = event.source.getEntity();

		if (attacker != null)
		{
			if (event.entityLiving.isPotionActive(ModPotions.fireShield))
			{
				attacker.setFire(6);
				attacker.attackEntityFrom(DamageSource.onFire, 1);
			}
			else if (event.entityLiving.isPotionActive(ModPotions.earthenShield) &&
					!event.source.isDamageAbsolute())
			{
				event.ammount *= 0.75F;
				
			}
		}
	}
}
