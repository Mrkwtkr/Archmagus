package com.agadar.archmagus.eventhandler;

import com.agadar.archmagus.misc.ManaProperties;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;

/** For naturally regenerating mana. */
public class HandlerOnPlayerPostTick 
{
	@SubscribeEvent
	public void onPlayerPostTick(PlayerTickEvent event)
	{
		if (event.phase != TickEvent.Phase.END || event.side != Side.SERVER || event.player.getFoodStats().getFoodLevel() < 18)
			return;

		ManaProperties prop = ManaProperties.get(event.player);

		if (prop.getCurrentMana() >= prop.getMaxMana())
			return;

		prop.manaTimer++;

		if (prop.manaTimer >= 60)
		{
			prop.replenishMana(1);
			prop.manaTimer = 0;
		}
	}
}
