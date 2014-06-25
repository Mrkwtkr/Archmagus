package com.agadar.archmagus.client;

import com.agadar.archmagus.CommonProxy;
import com.agadar.archmagus.entities.EntityArcaneWolf;
import com.agadar.archmagus.entities.EntityRisenSkeleton;
import com.agadar.archmagus.models.ModelArcaneWolf;
import com.agadar.archmagus.renderers.RenderArcaneWolf;
import com.agadar.archmagus.renderers.RenderRisenSkeleton;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy 
{
	@Override
	public void registerRenderers() 
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityArcaneWolf.class, new RenderArcaneWolf(new ModelArcaneWolf(), new ModelArcaneWolf(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRisenSkeleton.class, new RenderRisenSkeleton());
	}
}
