package com.agadar.archmagus.client;

import net.minecraft.client.model.ModelWolf;

import com.agadar.archmagus.CommonProxy;
import com.agadar.archmagus.entities.EntityArcaneWolf;
import com.agadar.archmagus.entities.RenderArcaneWolf;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy 
{
	@Override
	public void registerArcaneWolf() 
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityArcaneWolf.class, new RenderArcaneWolf(new ModelWolf(), new ModelWolf(), 0.5F));
	}
}
