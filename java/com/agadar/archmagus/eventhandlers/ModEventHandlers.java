package com.agadar.archmagus.eventhandlers;

import net.minecraftforge.common.MinecraftForge;

public class ModEventHandlers 
{
	public static void subscribeEvents() 
	{
		/** For making mobs drop spell books. */
		MinecraftForge.EVENT_BUS.register(new HandlerOnLivingDrops());
		 
		/** For making spell books combineable in anvils. */
		MinecraftForge.EVENT_BUS.register(new HandlerOnAnvilUpdate());
		
		/** For altering the player's model in case he is polymorphed. */
		MinecraftForge.EVENT_BUS.register(new HandlerOnPlayerRender());
		
		/** For hiding the player's hand in case he is polymorphed. */
		MinecraftForge.EVENT_BUS.register(new HandlerOnRenderHand());
		
		/** For spawning particles around entities holding spell books. */
		//MinecraftForge.EVENT_BUS.register(new HandlerOnLivingUpdate());
		// Currently disabled due to the particles spawning very low to the ground when viewed by another player for some reason.
	}
}
