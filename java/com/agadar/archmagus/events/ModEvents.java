package com.agadar.archmagus.events;

import net.minecraftforge.common.MinecraftForge;

public class ModEvents 
{
	public static void subscribeEvents() 
	{
		/** For making mobs drop spell books. */
		MinecraftForge.EVENT_BUS.register(new EventOnLivingDrops());
		
		/** For making spell books combineable in the anvil. */
		MinecraftForge.EVENT_BUS.register(new EventOnAnvilUpdate());
	}
}
