package com.agadar.archmagus.events;

import com.agadar.archmagus.items.ItemSpellBook;
import com.agadar.archmagus.items.ModItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.AnvilUpdateEvent;

public class EventOnAnvilUpdate 
{
	@SubscribeEvent
	public void OnAnvilUpdate(AnvilUpdateEvent event)
	{
		if (event.left.getItem().equals(ModItems.spell_book) && event.right.getItem().equals(ModItems.spell_book))
		{
			event.output = ItemSpellBook.combine(event.left, event.right);
			event.cost = 10;
		}
	}
}
