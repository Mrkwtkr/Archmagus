package com.agadar.archmagus.potion;

import com.agadar.archmagus.misc.References;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionBase extends Potion 
{
	/** The location of our custom potion effect icons. */
	private static final ResourceLocation resourceLoc = new ResourceLocation(References.MODID + ":textures/custom_icons.png");

	public PotionBase(int par1Id, boolean par2IsBadEffect, int par3LiquidColor) 
	{
		super(par1Id, par2IsBadEffect, par3LiquidColor);
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
