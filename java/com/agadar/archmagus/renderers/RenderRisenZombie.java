package com.agadar.archmagus.renderers;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
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
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
    	return zombieTextures;
    }
}
