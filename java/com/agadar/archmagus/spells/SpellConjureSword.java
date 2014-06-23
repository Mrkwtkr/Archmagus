package com.agadar.archmagus.spells;

import com.agadar.archmagus.items.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpellConjureSword extends Spell
{
	public SpellConjureSword(int par1) 
	{
		super(par1);
		this.setName("conjure_sword");
	}
	
	@Override
    public int getHungerCost()
    {
    	return 4;
    }
	
	@Override
    public int getMaxLevel()
    {
        return 4;
    }

	@Override
	public void cast(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{
		Item conjured = ModItems.conjured_sword_1;
		
		switch (par1Level)
		{
			case 2:
				conjured = ModItems.conjured_sword_2;
				break;
			case 3:
				conjured = ModItems.conjured_sword_3;
				break;
			case 4:
				conjured = ModItems.conjured_sword_4;
				break;
		}		
		
		if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(conjured))) 
		{
			par3EntityPlayer.dropItem(conjured, 1);
		}
		par3EntityPlayer.inventoryContainer.detectAndSendChanges();
	}
}
