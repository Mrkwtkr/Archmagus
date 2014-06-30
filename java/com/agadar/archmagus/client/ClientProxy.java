package com.agadar.archmagus.client;

import com.agadar.archmagus.CommonProxy;
import com.agadar.archmagus.entities.EntityRisenZombie;
import com.agadar.archmagus.entities.EntityRisenZombiePigman;
import com.agadar.archmagus.entities.EntitySummonedWolf;
import com.agadar.archmagus.entities.EntityRisenSkeleton;
import com.agadar.archmagus.entities.EntityRisenWitherSkeleton;
import com.agadar.archmagus.entities.EntitySummonedCaveSpider;
import com.agadar.archmagus.entities.EntitySummonedSpider;
import com.agadar.archmagus.entities.EntitySummonedWitch;
import com.agadar.archmagus.models.ModelSummonedWolf;
import com.agadar.archmagus.renderers.RenderRisenZombie;
import com.agadar.archmagus.renderers.RenderRisenZombiePigman;
import com.agadar.archmagus.renderers.RenderSummonedWolf;
import com.agadar.archmagus.renderers.RenderRisenSkeleton;
import com.agadar.archmagus.renderers.RenderRisenWitherSkeleton;
import com.agadar.archmagus.renderers.RenderSummonedCaveSpider;
import com.agadar.archmagus.renderers.RenderSummonedSpider;
import com.agadar.archmagus.renderers.RenderSummonedWitch;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy 
{
	@Override
	public void registerRenderers() 
	{
		RenderingRegistry.registerEntityRenderingHandler(EntitySummonedWolf.class, new RenderSummonedWolf(new ModelSummonedWolf(), new ModelSummonedWolf(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRisenSkeleton.class, new RenderRisenSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(EntityRisenWitherSkeleton.class, new RenderRisenWitherSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(EntityRisenZombie.class, new RenderRisenZombie());
		RenderingRegistry.registerEntityRenderingHandler(EntityRisenZombiePigman.class, new RenderRisenZombiePigman());
		RenderingRegistry.registerEntityRenderingHandler(EntitySummonedWitch.class, new RenderSummonedWitch());
		RenderingRegistry.registerEntityRenderingHandler(EntitySummonedSpider.class, new RenderSummonedSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntitySummonedCaveSpider.class, new RenderSummonedCaveSpider());
	}
}
