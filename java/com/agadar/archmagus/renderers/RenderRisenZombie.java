package com.agadar.archmagus.renderers;

import com.agadar.archmagus.entities.EntityRisenZombie;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRisenZombie extends RenderBiped
{
    private static final ResourceLocation zombieTextures = new ResourceLocation("textures/entity/zombie/zombie.png");

    public RenderRisenZombie()
    {
        super(new ModelZombie(), 0.5F, 1.0F);
    }

    @Override
    protected void func_82421_b()
    {
        this.field_82423_g = new ModelZombie(1.0F, true);
        this.field_82425_h = new ModelZombie(0.5F, true);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityRisenZombie par1EntityZombie, int par2, float par3)
    {
        this.func_82427_a(par1EntityZombie);
        return super.shouldRenderPass((EntityLiving)par1EntityZombie, par2, par3);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityRisenZombie par1EntityZombie, double par2, double par4, double par6, float par8, float par9)
    {
        this.func_82427_a(par1EntityZombie);
        super.doRender((EntityLiving)par1EntityZombie, par2, par4, par6, par8, par9);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityRisenZombie par1EntityZombie)
    {
        return zombieTextures;
    }

    private void func_82427_a(EntityRisenZombie par1EntityZombie)
    {
    	this.modelBipedMain = (ModelBiped)this.mainModel;
    }

    protected void rotateCorpse(EntityRisenZombie par1EntityZombie, float par2, float par3, float par4)
    {
        super.rotateCorpse(par1EntityZombie, par2, par3, par4);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityLiving par1EntityLiving)
    {
        return this.getEntityTexture((EntityRisenZombie)par1EntityLiving);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    @Override
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRender((EntityRisenZombie)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    @Override
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.shouldRenderPass((EntityRisenZombie)par1EntityLiving, par2, par3);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    @Override
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.shouldRenderPass((EntityRisenZombie)par1EntityLivingBase, par2, par3);
    }

    @Override
    protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
    {
        this.rotateCorpse((EntityRisenZombie)par1EntityLivingBase, par2, par3, par4);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    @Override
    public void doRender(EntityLivingBase par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRender((EntityRisenZombie)par1Entity, par2, par4, par6, par8, par9);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getEntityTexture((EntityRisenZombie)par1Entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRender((EntityRisenZombie)par1Entity, par2, par4, par6, par8, par9);
    }
}
