package com.agadar.archmagus.spells;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public abstract class Spell 
{
	/** The list of all spells. */
	public final static Spell[] spellList = new Spell[256];
	public final static Spell blazeFire = new SpellBlazeFire(0);
	public final static Spell ghastFire = new SpellGhastFire(1);
	public final static Spell conjureSword = new SpellConjureSword(2);
	/** The cooldown of all spells in game ticks. */
	public final static short coolDown = 20;
	/** A Random object used by some child classes of Spell. */
	protected final static Random random = new Random();
	/** The index of this spell in the spellList. */
	public final int effectId;
	/** Used in localisation and stats. */
    private String name;
	
    protected Spell(int par1)
    {
        this.effectId = par1;

        if (spellList[par1] != null)
        {
            throw new IllegalArgumentException("Duplicate spell id!");
        }
        else
        {
        	spellList[par1] = this;
        }
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
     * Sets the spell name.
     */
    public Spell setName(String par1Str)
    {
        this.name = par1Str;
        return this;
    }
    
    /**
     * Returns the name of key in translation table of this spell.
     */
    public String getName()
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

	public abstract void cast(short par1Level, World par2World, EntityPlayer par3EntityPlayer);
}
