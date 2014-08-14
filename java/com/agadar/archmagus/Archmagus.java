package com.agadar.archmagus;

import com.agadar.archmagus.entity.ModEntities;
import com.agadar.archmagus.eventhandler.ModEventHandlers;
import com.agadar.archmagus.itemblock.ModItemsBlocks;
import com.agadar.archmagus.misc.References;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = References.MODID, version = References.VERSION, name = References.NAME, useMetadata = true)
public class Archmagus 
{
	@Instance(value = References.NAME)
	public static Archmagus instance;

	@SidedProxy(clientSide = References.CLIENTSIDE, serverSide = References.SERVERSIDE)
	public static CommonProxy proxy;
	
	/** The message channel for this mod. */
	public static SimpleNetworkWrapper networkWrapper;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{		
		/** Register the network stuff. */
		networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(References.MODID);
		networkWrapper.registerMessage(MaxManaMessage.Handler.class, MaxManaMessage.class, 0, Side.CLIENT);
		
		/** Register the mod stuff. */
		ModItemsBlocks.registerModItemsAndBlocks();	
		ModEntities.registerModEntities();	
		ModEventHandlers.registerModEventHandlers();
		
		/** Register the client-only stuff. */
		proxy.registerRenderers();
	}
}
