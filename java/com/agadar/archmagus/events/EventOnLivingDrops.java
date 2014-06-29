package com.agadar.archmagus.events;

import com.agadar.archmagus.items.ItemSpellBook;
import com.agadar.archmagus.items.ModItems;
import com.agadar.archmagus.spells.SpellData;
import com.agadar.archmagus.spells.Spells;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class EventOnLivingDrops 
{
	@SubscribeEvent
	public void OnLivingDrops (LivingDropsEvent event)
	{
		int randResult = event.entity.worldObj.rand.nextInt(1);

		if (randResult == 0)
		{
			Class<? extends Entity> entityClass = event.entity.getClass();
			ItemStack spellBookDrop = new ItemStack(ModItems.spell_book);

			if (entityClass.equals(EntitySkeleton.class))
			{
				SpellData spellData = new SpellData(Spells.raise_skeleton, 1);
				((ItemSpellBook) ModItems.spell_book).addSpell(spellBookDrop, spellData);
				event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, spellBookDrop));
			}
		}
	}
}
