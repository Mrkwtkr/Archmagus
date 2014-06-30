package com.agadar.archmagus.events;

import com.agadar.archmagus.items.ItemSpellBook;
import com.agadar.archmagus.items.ModItems;
import com.agadar.archmagus.spells.Spell;
import com.agadar.archmagus.spells.SpellData;
import com.agadar.archmagus.spells.Spells;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class EventOnLivingDrops 
{
	@SubscribeEvent
	public void OnLivingDrops (LivingDropsEvent event)
	{		
		Class<? extends Entity> entityClass = event.entity.getClass();

		if (entityClass.equals(EntityBlaze.class))
		{
			randomDrop(event, Spells.blazefire, 1, 10);
		}
		else if (entityClass.equals(EntityGhast.class))
		{
			randomDrop(event, Spells.ghastfire, 1, 20);
		}
		else if (entityClass.equals(EntityWither.class))
		{
			randomDrop(event, Spells.witherblast, 1, 100);
		}
		else if (entityClass.equals(EntityCreeper.class))
		{
			randomDrop(event, Spells.lightning, 1, 10);
		} 
		else if (entityClass.equals(EntityZombie.class))
		{
			randomDrop(event, Spells.raise_zombie, 1, 10);
		}
		else if (entityClass.equals(EntityPigZombie.class))
		{
			randomDrop(event, Spells.raise_zombie_pigman, 1, 10);
		}
		else if (entityClass.equals(EntitySkeleton.class))
		{
			int skeletonType = ((EntitySkeleton) event.entity).getSkeletonType();
			
			if (skeletonType == 0)
			{
				randomDrop(event, Spells.raise_skeleton, 1, 10);
			}
			else if (skeletonType == 1)
			{
				randomDrop(event, Spells.raise_wither_skeleton, 1, 10);
			}
		}
		else if (entityClass.equals(EntityWolf.class))
		{
			randomDrop(event, Spells.summon_wolf, 1, 10);
		}
		else if (entityClass.equals(EntityWitch.class))
		{
			randomDrop(event, Spells.summon_witch, 1, 25);
		}
		else if (entityClass.equals(EntitySpider.class))
		{
			randomDrop(event, Spells.summon_spider, 1, 10);
		}
		else if (entityClass.equals(EntityCaveSpider.class))
		{
			randomDrop(event, Spells.summon_cave_spider, 1, 10);
		}
		else if (entityClass.equals(EntityEnderman.class))
		{
			randomDrop(event, Spells.teleport, 1, 10);
			randomDrop(event, Spells.respawn, 1, 10);
		}
	}
	
	/**
	 * @param event A reference to the LivingDropsEvent.
	 * @param spell The spell which the randomly dropped spell book will have.
	 * @param level The level of the spell which the randomly dropped spell book will have.
	 * @param percentage The chance that the spell book will be dropped.
	 */
	private void randomDrop (LivingDropsEvent event, Spell spell, int level, double percentage)
	{
		double  randResult = event.entity.worldObj.rand.nextDouble() * 100;
		
		if (randResult < percentage)
		{
			SpellData spellData = new SpellData(spell, level);
			ItemStack spellBookDrop = ((ItemSpellBook) ModItems.spell_book).getSpellItemStack(spellData);
			event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, spellBookDrop));
		}
	}
}
