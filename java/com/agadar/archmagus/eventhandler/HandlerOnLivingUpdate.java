package com.agadar.archmagus.eventhandler;

import com.agadar.archmagus.item.ModItems;
import com.agadar.archmagus.spell.Spell;
import com.agadar.archmagus.spell.Spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/** For spawning particles around entities holding spell books. */
public class HandlerOnLivingUpdate 
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
				double particleChance = player.worldObj.rand.nextDouble();
				short id = heldItem.stackTagCompound.getShort("id");
				Spell spell = Spells.spellList[id];
				
				if (particleChance < spell.getParticleAmount())
				{
					double d0 = player.worldObj.rand.nextGaussian() * 0.02D;
		            double d1 = player.worldObj.rand.nextGaussian() * 0.02D;
		            double d2 = player.worldObj.rand.nextGaussian() * 0.02D;
		            player.worldObj.spawnParticle(spell.getParticleName(), player.posX + (double)(player.worldObj.rand.nextFloat() * player.width * 2.0F) - (double)player.width, player.posY - 1.5D + (double)(player.worldObj.rand.nextFloat() * player.height), player.posZ + (double)(player.worldObj.rand.nextFloat() * player.width * 2.0F) - (double)player.width, d0, d1, d2);
				}
			}
		}
	}
}