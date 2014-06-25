package com.agadar.archmagus.client;

import com.agadar.archmagus.CommonProxy;
import com.agadar.archmagus.entities.EntityArcaneWolf;
import com.agadar.archmagus.models.ModelArcaneWolf;
import com.agadar.archmagus.renderers.RenderArcaneWolf;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy 
{
	@Override
	public void registerArcaneWolf() 
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityArcaneWolf.class, new RenderArcaneWolf(new ModelArcaneWolf(), new ModelArcaneWolf(), 0.5F));
	}
}
