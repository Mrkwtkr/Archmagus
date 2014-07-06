package com.agadar.archmagus.renderers;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.agadar.archmagus.models.ModelPolyBat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPolyBat extends RendererLivingEntity
{
    private static final ResourceLocation batTextures = new ResourceLocation("textures/entity/bat.png");
    /**
     * not actually sure this is size, is not used as of now, but the model would be recreated if the value changed and
     * it seems a good match for a bats size
     */
    private int renderedBatSize;

    public RenderPolyBat()
    {
        super(new ModelPolyBat(), 0.25F);
        this.renderedBatSize = ((ModelPolyBat)this.mainModel).getBatSize();
        this.renderManager = RenderManager.instance;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return batTextures;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        GL11.glScalef(0.35F, 0.35F, 0.35F);
    }

    @Override
    public void doRender(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
    {
    	int i = ((ModelPolyBat)this.mainModel).getBatSize();

        if (i != this.renderedBatSize)
        {
            this.renderedBatSize = i;
            this.mainModel = new ModelPolyBat();
        }

        super.doRender(par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    @Override
    protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
    {
    	GL11.glTranslatef(0.0F, MathHelper.cos(par2 * 0.3F) * 0.1F, 0.0F);
        super.rotateCorpse(par1EntityLivingBase, par2, par3, par4);
    }
}
