package com.agadar.archmagus.spells;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public abstract class Spell 
{
	/** A Random object used by some child classes of Spell. */
	protected final static Random random = new Random();
	/** The index of this spell in the spellList. */
	public final int effectId;
	/** Used in localisation and stats. */
    private String name;
	
    protected Spell(int par1)
    {
        this.effectId = par1;
        Spells.addSpell(this, par1);
    }
    
	/**
     * Returns the minimum level that the spell can be.
     */
    public int getMinLevel()
    {
        return 1;
    }

    /**
     * Returns the maximum level that the spell can be.
     */
    public int getMaxLevel()
    {
        return 1;
    }
    
    /**
     * Returns the amount of hunger the spell costs to cast.
     */
    public int getHungerCost()
    {
    	return 1;
    }
    
    /**
     * Returns the cooldown of this spell in game ticks.
     */
    public short getCooldown()
    {
    	return 20;
    }
    
    /**
     * Returns the name of the particles spawned when this spell is cast.
     */
    public String getParticleName()
    {
    	return "happyVillager";
    }
    
	/**
	 * Returns the amount of particles spawned when this spell is cast.
	 */
	public int getParticleAmount()
	{
		return 20;
	}
    
    /**
     * Sets the spell name.
     */
    protected Spell setName(String par1Str)
    {
        this.name = par1Str;
        return this;
    }
    
    /**
     * Returns the name of key in translation table of this spell.
     */
    private String getName()
    {
        return "spell." + this.name;
    }
    
    /**
     * Returns the correct translated name of the spell and the level in roman numbers.
     */
    public String getTranslatedName(int par1)
    {
        String s = StatCollector.translateToLocal(this.getName());
        return s + " " + StatCollector.translateToLocal("spell.level." + par1);
    }
    
    /**
     * Ensures that a given level lies between this spell's minimum and maximum levels.
     */
    protected int getNormalizedLevel(int par1Level)
    {
    	return Math.max(getMinLevel(), Math.min(par1Level, getMaxLevel()));
    }
    
	/**
	 * Spawns random particles.
	 */
    @SideOnly(Side.CLIENT)
    public void generateRandomParticles(World par2World, EntityPlayer par3EntityPlayer)
    {
    	for (int i = 0; i < this.getParticleAmount(); ++i)
        {
            double d0 = par2World.rand.nextGaussian() * 0.02D;
            double d1 = par2World.rand.nextGaussian() * 0.02D;
            double d2 = par2World.rand.nextGaussian() * 0.02D;
            par2World.spawnParticle(this.getParticleName(), par3EntityPlayer.posX + (double)(par2World.rand.nextFloat() * par3EntityPlayer.width * 2.0F) - (double)par3EntityPlayer.width, par3EntityPlayer.posY - 1.5D + (double)(par2World.rand.nextFloat() * par3EntityPlayer.height), par3EntityPlayer.posZ + (double)(par2World.rand.nextFloat() * par3EntityPlayer.width * 2.0F) - (double)par3EntityPlayer.width, d0, d1, d2);
        }
    }
    
    /**
     * Casts this spell based on the given level.
     * @return An indication of whether the spell was cast succesfully.
     */
	public abstract boolean castSpell(short par1Level, World par2World, EntityPlayer par3EntityPlayer);
}
