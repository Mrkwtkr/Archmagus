package com.agadar.archmagus.potion;

import com.agadar.archmagus.Archmagus;
import com.agadar.archmagus.misc.ManaProperties;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionBase extends Potion 
{
	/** The location of our custom potion effect icons. */
	private static final ResourceLocation resourceLoc = new ResourceLocation(Archmagus.MODID + ":textures/status_icons.png");

	public PotionBase(int par1Id, boolean par2IsBadEffect, int par3LiquidColor) 
	{
		super(par1Id, par2IsBadEffect, par3LiquidColor);
	}
	
	@Override
    public void performEffect(EntityLivingBase par1EntityLivingBase, int par2)
    {
		if (par1EntityLivingBase instanceof EntityPlayer)
		{
			if (this.id == ModPotions.manaRegen.id)
	        {
				ManaProperties prop = ManaProperties.get((EntityPlayer) par1EntityLivingBase);
				
				if (prop != null)
					prop.replenishMana(1);
	        }
			else if (this.id == ModPotions.mana.id)
			{
				
			}
		}
		else
		{
			super.performEffect(par1EntityLivingBase, par2);
		}
    }
	
	@Override
    public boolean isReady(int par1, int par2)
    {
		if (this.id == ModPotions.manaRegen.id)
        {
            int k = 50 >> par2;
            return k > 0 ? par1 % k == 0 : true;
        }
		else if (this.id == ModPotions.mana.id)
		{
			return par1 >= 1;
		}
		
		return super.isReady(par1, par2);
    }
	
	@Override
    public boolean isInstant()
    {
        return this.id == ModPotions.mana.id;
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
