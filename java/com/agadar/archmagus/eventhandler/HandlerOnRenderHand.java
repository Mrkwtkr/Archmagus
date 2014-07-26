package com.agadar.archmagus.eventhandler;

import com.agadar.archmagus.potion.ModPotions;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderHandEvent;

/** For hiding the player's hand in case he is polymorphed. */
public class HandlerOnRenderHand 
{
	@SubscribeEvent
	public void OnRenderHand(RenderHandEvent event)
	{
		if (Minecraft.getMinecraft().thePlayer.isPotionActive(ModPotions.polymorphed))
		{
			event.setCanceled(true);
		}
	}
}
