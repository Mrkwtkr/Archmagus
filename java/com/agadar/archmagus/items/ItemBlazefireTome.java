package com.agadar.archmagus.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemBlazefireTome extends Item 
{
	public ItemBlazefireTome()
	{
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(ModItems.tabArchmagus);
		this.setUnlocalizedName("blazefire_tome");
		this.setTextureName("book_enchanted");
		this.setMaxDamage(30);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }
	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
	@Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if (!par2World.isRemote) 
		{
			if (!par1ItemStack.isItemDamaged())
			{
				par1ItemStack.damageItem(par1ItemStack.getMaxDamage(), par3EntityPlayer);
				
				Vec3 v3 = par3EntityPlayer.getLook(1);
				EntitySmallFireball smallfireball = new EntitySmallFireball(par2World, par3EntityPlayer.posX, par3EntityPlayer.posY + par3EntityPlayer.eyeHeight, par3EntityPlayer.posZ, v3.xCoord, v3.yCoord, v3.zCoord);
				smallfireball.shootingEntity = par3EntityPlayer;
				par2World.spawnEntityInWorld(smallfireball);
			}
		}

        return par1ItemStack;
    }
    
	@Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) 
	{
		if (!par2World.isRemote)
		{
			if (par1ItemStack.getItemDamage() > 0)
			{
				par1ItemStack.damageItem(-1, (EntityLivingBase) par3Entity); 
			}
		}
    }
}
