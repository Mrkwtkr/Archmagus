package com.agadar.archmagus.eventhandler;

import com.agadar.archmagus.potion.ModPotions;
import com.agadar.archmagus.render.RenderPolyBat;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.entity.Render;
import net.minecraftforge.client.event.RenderLivingEvent;

/** For altering the player's model in case he is polymorphed. */
public class HandlerOnRenderLiving 
{
	private final Render renderPolyBat = new RenderPolyBat();
	
	@SubscribeEvent
	public void onRenderLiving(RenderLivingEvent.Pre event)
	{      
		if (event.entity.isPotionActive(ModPotions.polymorphed))
		{
			event.setCanceled(true);
			renderPolyBat.doRender(event.entity, event.x, event.y, event.z, 0F, 0F);
		}
	}
}
