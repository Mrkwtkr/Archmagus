package com.agadar.archmagus.events;

import net.minecraftforge.common.MinecraftForge;

public class ModEvents 
{
	public static void subscribeEvents() 
	{
		/** For making mobs drop spell books. */
		MinecraftForge.EVENT_BUS.register(new EventOnLivingDrops());
		 
		/** For making spell books combineable in anvils. */
		MinecraftForge.EVENT_BUS.register(new EventOnAnvilUpdate());
		
		/** For spawning particles around entities holding spell books. */
		//MinecraftForge.EVENT_BUS.register(new EventOnLivingUpdate());
		// Currently disabled due to the particles spawning very low to the ground when viewed by another player for some reason.
	}
}
