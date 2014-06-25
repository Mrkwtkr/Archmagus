package com.agadar.archmagus.renderers;

import org.lwjgl.opengl.GL11;

import com.agadar.archmagus.entities.EntityArcaneWolf;
import com.agadar.archmagus.help.References;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderArcaneWolf extends RenderLiving 
{
	private static final ResourceLocation wolfTextures = new ResourceLocation(References.MODID + ":textures/entity/arcane_wolf.png");
	
	public RenderArcaneWolf(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) 
	{
		super(par1ModelBase, par3);
		this.setRenderPassModel(par2ModelBase);
	}
	
	/**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityArcaneWolf par1EntityWolf, float par2)
    {
        return par1EntityWolf.getTailRotation();
    }
	
	protected int shouldRenderPass(EntityArcaneWolf par1EntityWolf, int par2, float par3)
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
	
	protected ResourceLocation getEntityTexture(EntityArcaneWolf par1EntityWolf)
    {
        return wolfTextures;
    }
	
	/**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.shouldRenderPass((EntityArcaneWolf)par1EntityLivingBase, par2, par3);
    }
    
    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2)
    {
        return this.handleRotationFloat((EntityArcaneWolf)par1EntityLivingBase, par2);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getEntityTexture((EntityArcaneWolf)par1Entity);
    }
}
