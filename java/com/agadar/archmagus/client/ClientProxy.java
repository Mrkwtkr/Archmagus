package com.agadar.archmagus.client;

import com.agadar.archmagus.CommonProxy;
import com.agadar.archmagus.entities.EntityRisenZombie;
import com.agadar.archmagus.entities.EntitySpiritWolf;
import com.agadar.archmagus.entities.EntityRisenSkeleton;
import com.agadar.archmagus.entities.EntityRisenWitherSkeleton;
import com.agadar.archmagus.models.ModelSpiritWolf;
import com.agadar.archmagus.renderers.RenderRisenZombie;
import com.agadar.archmagus.renderers.RenderSpiritWolf;
import com.agadar.archmagus.renderers.RenderRisenSkeleton;
import com.agadar.archmagus.renderers.RenderRisenWitherSkeleton;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy 
{
	@Override
	public void registerRenderers() 
	{
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiritWolf.class, new RenderSpiritWolf(new ModelSpiritWolf(), new ModelSpiritWolf(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRisenSkeleton.class, new RenderRisenSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(EntityRisenWitherSkeleton.class, new RenderRisenWitherSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(EntityRisenZombie.class, new RenderRisenZombie());
	}
}
