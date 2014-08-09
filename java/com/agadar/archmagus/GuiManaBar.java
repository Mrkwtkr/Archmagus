package com.agadar.archmagus;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class GuiManaBar extends Gui 
{
	/** The path to the gui's icons. */
	private static final ResourceLocation modIcons = new ResourceLocation("archmagus", "textures/gui/archmagus_icons.png");	
	/** A reference to the Minecraft instance. */
	private final Minecraft mc;
	
	public GuiManaBar(Minecraft mc)
	{
		super();
		this.mc = mc;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderHungerBar(RenderGameOverlayEvent.Post event)
	{		
		if (event.isCancelable() || event.type != ElementType.FOOD)
			return;

		mc.mcProfiler.startSection("mana");
		ManaProperties props = ManaProperties.get(this.mc.thePlayer);
		
		if (props == null || props.getMaxMana() == 0)
			return;
		
		mc.getTextureManager().bindTexture(modIcons);
		GL11.glEnable(GL11.GL_BLEND);
		int width = event.resolution.getScaledWidth();
        int height = event.resolution.getScaledHeight();
		int left = width / 2 + 91;
        int top = height - GuiIngameForge.right_height;
        int currentMana = props.getCurrentMana();
        
        for (int i = 0; i < 10; ++i)
        {
            int idx = i * 2 + 1;
            int x = left - i * 8 - 9;
            int y = top;

            this.drawTexturedModalRect(x, y, 0, 0, 9, 9);
            
            if (idx < currentMana)
            	this.drawTexturedModalRect(x, y, 36, 0, 9, 9);
            else if (idx == currentMana)
            	this.drawTexturedModalRect(x, y, 45, 0, 9, 9);
        }
        
        GL11.glDisable(GL11.GL_BLEND);
        mc.mcProfiler.endSection();
	}
}
