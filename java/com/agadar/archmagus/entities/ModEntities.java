package com.agadar.archmagus.entities;

import com.agadar.archmagus.Archmagus;

import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities 
{
	public static void registerEntities()
	{
		// Register the entities.
		EntityRegistry.registerGlobalEntityID(EntitySpiritWolf.class, "spirit_wolf", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(EntityRisenSkeleton.class, "risen_skeleton", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(EntityRisenWitherSkeleton.class, "risen_wither_skeleton", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerGlobalEntityID(EntityRisenZombie.class, "risen_zombie", EntityRegistry.findGlobalUniqueEntityId());
		
		// Register the renderers.
		Archmagus.proxy.registerRenderers();
	}
}
