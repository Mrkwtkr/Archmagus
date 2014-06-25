package com.agadar.archmagus.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpellConjureItem extends Spell
{
	/**
	 * Holds the items this spell can conjure, where the item with index 0
	 * is the item conjured if the spell is level 1, and where the item with
	 * index 1 is the item conjured if the spell is level 2, etcetera.
	 */
	private final Item[] items;
	
	protected SpellConjureItem(int par1, String name, Item[] items) 
	{
		super(par1);
		this.setName(name);
		this.items = items;
	}
	
	@Override
    public int getHungerCost()
    {
    	return 4;
    }
	
	@Override
    public int getMaxLevel()
    {
        return items.length;
    }
	
	@Override
	public short getCooldown()
	{
		return 300;
	}

	@Override
	public void cast(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{		
		if (!par2World.isRemote)
		{
			Item item = items[getNormalizedLevel(par1Level) - 1];

			if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(item))) 
			{
				par3EntityPlayer.dropItem(item, 1);
			}
			par3EntityPlayer.inventoryContainer.detectAndSendChanges();
		}
	}
}
