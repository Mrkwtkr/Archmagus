package com.agadar.archmagus.eventhandlers;

import com.agadar.archmagus.items.ModItems;
import com.agadar.archmagus.renderers.RenderPolyBat;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.entity.Render;
import net.minecraftforge.client.event.RenderPlayerEvent;

/** For altering the player's model in case he is polymorphed. */
public class HandlerOnPlayerRender 
{
	private final Render renderPolyBat = new RenderPolyBat();
	
	@SubscribeEvent
	public void OnPlayerRender(RenderPlayerEvent.Pre event)
	{
		if (event.entityPlayer.isPotionActive(ModItems.shapeshiftPotion))
		{
			event.setCanceled(true);
			renderPolyBat.doRender(event.entity, 0D, 0D, 0D, 0F, 0F);
		}
	}
}
