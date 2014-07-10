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
				/** TODO: Implement the knockback immunity. */
			}
			else if (event.entityLiving.isPotionActive(ModPotions.waterShield))
			{
				/** TODO: Implement the healing-on-hit and fire immunity. */
			}
			else if (event.entityLiving.isPotionActive(ModPotions.aetherShield))
			{
				/** TODO: Implement the projectile-immunity and walking-speed effect. */
			}
			else if (event.entityLiving.isPotionActive(ModPotions.frostArmor))
			{
				/** TODO: Implement the slow-enemy-on-hit and weaken-enemy-on hit. */
			}
		}
	}
}
