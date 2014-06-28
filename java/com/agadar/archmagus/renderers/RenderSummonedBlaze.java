package com.agadar.archmagus.renderers;

import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSummonedBlaze extends RenderLiving
{
    private static final ResourceLocation blazeTextures = new ResourceLocation("textures/entity/blaze.png");
    private int field_77068_a;

    public RenderSummonedBlaze()
    {
        super(new ModelBlaze(), 0.5F);
        this.field_77068_a = ((ModelBlaze)this.mainModel).func_78104_a();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
    	return blazeTextures;
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
    	int i = ((ModelBlaze)this.mainModel).func_78104_a();

        if (i != this.field_77068_a)
        {
            this.field_77068_a = i;
            this.mainModel = new ModelBlaze();
        }

        super.doRender(par1Entity, par2, par4, par6, par8, par9);
    }
}
