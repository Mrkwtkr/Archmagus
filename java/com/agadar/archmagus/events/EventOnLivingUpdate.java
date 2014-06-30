package com.agadar.archmagus.events;

import com.agadar.archmagus.items.ModItems;
import com.agadar.archmagus.spells.Spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/** Handles spawning particles around entities holding spell books. */
public class EventOnLivingUpdate 
{
	@SubscribeEvent
	public void OnLivingUpdate(LivingUpdateEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			ItemStack heldItem = player.getHeldItem();
			
			if (heldItem != null && heldItem.getItem() == ModItems.spell_book && heldItem.stackTagCompound != null) 
			{
				short id = heldItem.stackTagCompound.getShort("id");
				double d0 = player.worldObj.rand.nextGaussian() * 0.02D;
	            double d1 = player.worldObj.rand.nextGaussian() * 0.02D;
	            double d2 = player.worldObj.rand.nextGaussian() * 0.02D;
	            player.worldObj.spawnParticle(Spells.getSpellAt(id).getParticleName(), player.posX + (double)(player.worldObj.rand.nextFloat() * player.width * 2.0F) - (double)player.width, player.posY - 1.5D + (double)(player.worldObj.rand.nextFloat() * player.height), player.posZ + (double)(player.worldObj.rand.nextFloat() * player.width * 2.0F) - (double)player.width, d0, d1, d2);
			}
		}
	}
}