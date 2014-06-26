package com.agadar.archmagus.entities;

import com.agadar.archmagus.Archmagus;

import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities 
{
	public static void registerEntities()
	{
		// Register the entities.
		EntityRegistry.registerGlobalEntityID(EntitySpiritWolf.class, "Spirit Wolf", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(EntityRisenSkeleton.class, "Risen Skeleton", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(EntityRisenWitherSkeleton.class, "Risen Wither Skeleton", EntityRegistry.findGlobalUniqueEntityId());
		
		// Register the renderers.
		Archmagus.proxy.registerRenderers();
	}
}
