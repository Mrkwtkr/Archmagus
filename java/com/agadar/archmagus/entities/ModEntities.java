package com.agadar.archmagus.entities;

import com.agadar.archmagus.Archmagus;

import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities 
{
	public static void registerEntities()
	{
		// Register our arcane wolf and its renderer.
		EntityRegistry.registerGlobalEntityID(EntityArcaneWolf.class, "Arcane Wolf", EntityRegistry.findGlobalUniqueEntityId());
		Archmagus.proxy.registerArcaneWolf();
	}
}
