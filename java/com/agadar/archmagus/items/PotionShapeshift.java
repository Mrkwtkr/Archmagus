package com.agadar.archmagus.items;

import com.agadar.archmagus.help.References;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionShapeshift extends Potion 
{
	/** The location of our custom potion effect icons. */
	private static final ResourceLocation resourceLoc = new ResourceLocation(References.MODID + ":textures/custom_icons.png");

	public PotionShapeshift(int par1Id) 
	{
		super(par1Id, false, 1);
	}

	@Override
	public Potion setIconIndex(int par1, int par2) 
	{
		super.setIconIndex(par1, par2);
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasStatusIcon() 
	{
	    Minecraft.getMinecraft().renderEngine.bindTexture(resourceLoc);
	    return true;
	}
}
