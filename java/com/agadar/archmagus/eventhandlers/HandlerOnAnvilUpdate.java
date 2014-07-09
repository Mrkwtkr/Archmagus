package com.agadar.archmagus.eventhandlers;

import com.agadar.archmagus.items.ItemSpellBook;
import com.agadar.archmagus.items.ModItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.AnvilUpdateEvent;

/** For making spell books combineable in anvils. */
public class HandlerOnAnvilUpdate 
{
	@SubscribeEvent
	public void OnAnvilUpdate(AnvilUpdateEvent event)
	{
		if (event.left.getItem().equals(ModItems.spell_book) && event.right.getItem().equals(ModItems.spell_book))
		{
			event.output = ((ItemSpellBook) ModItems.spell_book).tryCombine(event.left, event.right);
			event.cost = 10;
		}
	}
}
