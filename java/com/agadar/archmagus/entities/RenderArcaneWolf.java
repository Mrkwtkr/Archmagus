package com.agadar.archmagus.entities;

import org.lwjgl.opengl.GL11;

import com.agadar.archmagus.help.References;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;

public class RenderArcaneWolf extends RenderWolf 
{
	private static final ResourceLocation wolfTextures = new ResourceLocation(References.MODID + ":textures/entity/arcane_wolf.png");
	
	public RenderArcaneWolf(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) 
	{
		super(par1ModelBase, par2ModelBase, par3);
	}
	
	@Override
	protected int shouldRenderPass(EntityWolf par1EntityWolf, int par2, float par3)
    {
        if (par2 == 0 && par1EntityWolf.getWolfShaking())
        {
            float f1 = par1EntityWolf.getBrightness(par3) * par1EntityWolf.getShadingWhileShaking(par3);
            this.bindTexture(wolfTextures);
            GL11.glColor3f(f1, f1, f1);
            return 1;
        }
        else
        {
            return -1;
        }
    }
	
	@Override
	protected ResourceLocation getEntityTexture(EntityWolf par1EntityWolf)
    {
        return wolfTextures;
    }
}
