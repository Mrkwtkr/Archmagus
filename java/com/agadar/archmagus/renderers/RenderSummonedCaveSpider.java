package com.agadar.archmagus.renderers;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSummonedCaveSpider extends RenderSummonedSpider 
{
	private static final ResourceLocation caveSpiderTextures = new ResourceLocation("textures/entity/spider/cave_spider.png");

    public RenderSummonedCaveSpider()
    {
        this.shadowSize *= 0.7F;
    }
    
    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
    	GL11.glScalef(0.7F, 0.7F, 0.7F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return caveSpiderTextures;
    }
}
