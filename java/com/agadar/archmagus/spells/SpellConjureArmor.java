package com.agadar.archmagus.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/** Conjures a set of armor. */
public class SpellConjureArmor extends Spell 
{
	/**
	 * Holds the armor sets this spell can conjure, where the array with index 0
	 * holds the armor set conjured if the spell is level 1, and where the array with
	 * index 1 holds the armor set conjured if the spell is level 2, etcetera.
	 */
	private final Item[][] armorSets;
	
	protected SpellConjureArmor(int par1, String name, Item[][] armorSets) 
	{
		super(par1);
		this.setName(name);
		this.armorSets = armorSets;
	}
	
	@Override
    public int getHungerCost()
    {
    	return 16;
    }
	
	@Override
    public short getMaxLevel()
    {
        return (short) armorSets.length;
    }
	
	@Override
	public short getCooldown()
	{
		return 1200;
	}

	@Override
	public boolean castSpell(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{
		if (!par2World.isRemote)
		{
			Item[] armorSet = armorSets[getNormalizedLevel(par1Level) - 1];

			for (int i = 0; i < armorSet.length; i++)
			{
				Item armorPiece = armorSet[i];

				if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(armorPiece))) 
				{
					par3EntityPlayer.dropItem(armorPiece, 1);
				}
			}
			
			par3EntityPlayer.inventoryContainer.detectAndSendChanges();
		}
		
		return true;
	}
}
