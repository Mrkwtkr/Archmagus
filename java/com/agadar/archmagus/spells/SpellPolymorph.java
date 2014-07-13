package com.agadar.archmagus.spells;

import com.agadar.archmagus.potions.ModPotions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/** Polymorphs the caster into an animal. */
public class SpellPolymorph extends Spell 
{
	protected SpellPolymorph(int par1) 
	{
		super(par1);
		this.setName("polymorph");
	}

	@Override
    public int getHungerCost()
    {
    	return 6;
    }
    
    @Override
    public short getCooldown()
    {
    	return 200;
    }
	
	@Override
	public boolean castSpell(short par1Level, World par2World, EntityPlayer par3EntityPlayer) 
	{
		if (!par2World.isRemote)
		{
			par2World.playSoundAtEntity(par3EntityPlayer, this.getSoundName(), 1.0F, 1.0F);
			par3EntityPlayer.addPotionEffect(new PotionEffect(ModPotions.polymorphed.getId(), 200, 0));			
		}
		
		return true;
	}
}
