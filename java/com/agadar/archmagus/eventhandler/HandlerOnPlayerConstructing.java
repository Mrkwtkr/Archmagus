package com.agadar.archmagus.eventhandler;

import com.agadar.archmagus.ManaProperties;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/** For assigning mana pools to players when they are constructed. */
public class HandlerOnPlayerConstructing 
{
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer && ManaProperties.get((EntityPlayer) event.entity) == null)
			ManaProperties.register((EntityPlayer) event.entity);
	}
}
