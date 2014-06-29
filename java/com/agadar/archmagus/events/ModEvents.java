package com.agadar.archmagus.events;

import net.minecraftforge.common.MinecraftForge;

public class ModEvents 
{
	public static void subscribeEvents() 
	{
		MinecraftForge.EVENT_BUS.register(new EventOnLivingDrops());
	}
}
