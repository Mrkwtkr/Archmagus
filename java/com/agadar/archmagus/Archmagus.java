package com.agadar.archmagus;

import com.agadar.archmagus.entities.ModEntities;
import com.agadar.archmagus.eventhandlers.ModEventHandlers;
import com.agadar.archmagus.help.References;
import com.agadar.archmagus.items.ModItems;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.MODID, version = References.VERSION, name = References.NAME, useMetadata = true)
public class Archmagus 
{
	@Instance(value = References.NAME)
	public static Archmagus instance;

	@SidedProxy(clientSide = References.CLIENTSIDE, serverSide = References.SERVERSIDE)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{		
		ModItems.loadItems();	
		ModEntities.registerEntities();	
		ModEventHandlers.subscribeEvents();
	}
}
