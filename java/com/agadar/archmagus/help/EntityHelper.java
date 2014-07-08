package com.agadar.archmagus.help;

import net.minecraft.entity.Entity;

/** Provides miscellaneous methods that manipulate Entity variables. */
public class EntityHelper 
{
	/** Resets the given Entity's size to its original size. */
	public static void resetEntitySize(Entity par1Entity)
	{
		
	}
	
	/** Sets the given Entity's size. */
	public static void setEntitySize(float par1Width, float par2Height, Entity par3Entity)
	{	
		float f2;

        if (par1Width != par3Entity.width || par2Height != par3Entity.height)
        {
            f2 = par3Entity.width;
            par3Entity.width = par1Width;
            par3Entity.height = par2Height;
            par3Entity.boundingBox.maxX = par3Entity.boundingBox.minX + (double)par3Entity.width;
            par3Entity.boundingBox.maxZ = par3Entity.boundingBox.minZ + (double)par3Entity.width;
            par3Entity.boundingBox.maxY = par3Entity.boundingBox.minY + (double)par3Entity.height;

            if (par3Entity.width > f2 && !par3Entity.worldObj.isRemote)
            {
                par3Entity.moveEntity((double)(f2 - par3Entity.width), 0.0D, (double)(f2 - par3Entity.width));
            }
        }

        f2 = par1Width % 2.0F;

        if ((double)f2 < 0.375D)
        {
            par3Entity.myEntitySize = Entity.EnumEntitySize.SIZE_1;
        }
        else if ((double)f2 < 0.75D)
        {
            par3Entity.myEntitySize = Entity.EnumEntitySize.SIZE_2;
        }
        else if ((double)f2 < 1.0D)
        {
            par3Entity.myEntitySize = Entity.EnumEntitySize.SIZE_3;
        }
        else if ((double)f2 < 1.375D)
        {
            par3Entity.myEntitySize = Entity.EnumEntitySize.SIZE_4;
        }
        else if ((double)f2 < 1.75D)
        {
            par3Entity.myEntitySize = Entity.EnumEntitySize.SIZE_5;
        }
        else
        {
            par3Entity.myEntitySize = Entity.EnumEntitySize.SIZE_6;
        }
	}
}
