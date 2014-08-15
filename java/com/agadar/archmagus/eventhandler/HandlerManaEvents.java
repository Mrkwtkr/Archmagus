package com.agadar.archmagus.eventhandler;

import com.agadar.archmagus.Archmagus;
import com.agadar.archmagus.misc.ManaProperties;
import com.agadar.archmagus.misc.MaxManaMessage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/** Handles mana-related events. */
public class HandlerManaEvents 
{
	/** Assigns mana pools to players on construction. */
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer && ManaProperties.get((EntityPlayer) event.entity) == null)
			ManaProperties.register((EntityPlayer) event.entity);
	}
	
	/** Synchronizes the player's maximum mana pool size with what is known on the server. */
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
			Archmagus.networkWrapper.sendTo(new MaxManaMessage(ManaProperties.get((EntityPlayer) event.entity).getMaxMana()), (EntityPlayerMP) event.entity);
	}
}
