package com.agadar.archmagus.entities;

import com.agadar.archmagus.Archmagus;

import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities 
{
	public static void registerEntities()
	{
		// Register the entities.
		EntityRegistry.registerGlobalEntityID(EntityArcaneWolf.class, "Arcane Wolf", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(EntityRisenSkeleton.class, "Risen Skeleton", EntityRegistry.findGlobalUniqueEntityId());
		
		// Register the renderers.
		Archmagus.proxy.registerRenderers();
	}
}
