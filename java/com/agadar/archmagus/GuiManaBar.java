package com.agadar.archmagus;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class GuiManaBar extends Gui 
{
	/** The path to the gui's texture. */
	private static final ResourceLocation texturepath = new ResourceLocation("archmagus", "textures/gui/mana_bar.png");
	/** A reference to the Minecraft instance. */
	private final Minecraft mc;
	
	public GuiManaBar(Minecraft mc)
	{
		super();
		this.mc = mc;
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event)
	{
		if (event.isCancelable() || event.type != ElementType.EXPERIENCE)
			return;

		ManaProperties props = ManaProperties.get(this.mc.thePlayer);

		if (props == null || props.getMaxMana() == 0)
			return;

		// Starting position for the mana bar - 2 pixels from the top left corner.
		int xPos = 2;
		int yPos = 2;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_ALPHA_TEST);

		// Somewhere in Minecraft vanilla code it says to do this because of a lighting bug
		GL11.glDisable(GL11.GL_LIGHTING);

		// Bind your texture to the render engine
		this.mc.getTextureManager().bindTexture(texturepath);

		/*
		The parameters for drawTexturedModalRect are as follows:

		drawTexturedModalRect(int x, int y, int u, int v, int width, int height);

		x and y are the on-screen position at which to render.
		u and v are the coordinates of the most upper-left pixel in your texture file from which to start drawing.
		width and height are how many pixels to render from the start point (u, v)
		*/
		// First draw the background layer. In my texture file, it starts at the upper-
		// left corner (x=0, y=0), ends at 50 pixels (so it's 51 pixels long) and is 4 pixels thick (y value)
		this.drawTexturedModalRect(xPos, yPos, 0, 0, 51, 4);
		// Then draw the foreground; it's located just below the background in my
		// texture file, so it starts at x=0, y=4, is only 2 pixels thick and 49 length
		// Why y=4 and not y=5? Y starts at 0, so 0,1,2,3 = 4 pixels for the background

		// However, we want the length to be based on current mana, so we need a new variable:
		int manabarwidth = (int)(((float) props.getCurrentMana() / props.getMaxMana()) * 49);
		//System.out.println("[GUI MANA] Current mana bar width: " + manabarwidth);
		// Now we can draw our mana bar at yPos+1 so it centers in the background:
		this.drawTexturedModalRect(xPos, yPos + 1, 0, 4, manabarwidth, 2);
		// NOTE: be sure to reset the openGL settings after you're done or your character model will be messed up
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
	}
}
