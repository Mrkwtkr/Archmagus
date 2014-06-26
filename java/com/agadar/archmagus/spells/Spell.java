package com.agadar.archmagus.spells;

import java.util.Random;

import com.agadar.archmagus.entities.EntityArcaneWolf;
import com.agadar.archmagus.entities.EntityRisenSkeleton;
import com.agadar.archmagus.entities.EntityRisenWitherSkeleton;
import com.agadar.archmagus.items.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public abstract class Spell 
{
	/** The list of all spells. */
	private final static Spell[] spellList = new Spell[256];
	/** All individual spells. */
	public final static Spell blazefire = new SpellBlazeFire(0);
	public final static Spell ghastfire = new SpellGhastFire(1);
	public final static Spell witherblast = new SpellWitherBlast(2);
	public final static Spell lightning = new SpellLightning(3);
	public final static Spell conjure_axe = new SpellConjureItem(4, "conjure_axe", new Item[] { ModItems.conjured_axe_1, ModItems.conjured_axe_2, ModItems.conjured_axe_3, ModItems.conjured_axe_4 });
	public final static Spell conjure_bow = new SpellConjureItem(5, "conjure_bow", new Item[] { ModItems.conjured_bow });
	public final static Spell conjure_hoe = new SpellConjureItem(6, "conjure_hoe", new Item[] { ModItems.conjured_hoe });
	public final static Spell conjure_pickaxe = new SpellConjureItem(7, "conjure_pickaxe", new Item[] { ModItems.conjured_pickaxe_1, ModItems.conjured_pickaxe_2, ModItems.conjured_pickaxe_3, ModItems.conjured_pickaxe_4 });
	public final static Spell conjure_shovel = new SpellConjureItem(8, "conjure_shovel", new Item[] { ModItems.conjured_shovel_1, ModItems.conjured_shovel_2, ModItems.conjured_shovel_3, ModItems.conjured_shovel_4 });
	public final static Spell conjure_sword = new SpellConjureItem(9, "conjure_sword", new Item[] { ModItems.conjured_sword_1, ModItems.conjured_sword_2, ModItems.conjured_sword_3, ModItems.conjured_sword_4 });
	public final static Spell conjure_armor = new SpellConjureArmor(10, "conjure_armor", new Item[][] { 
			new Item[] { ModItems.conjured_helmet_1, ModItems.conjured_chestplate_1, ModItems.conjured_leggings_1, ModItems.conjured_boots_1 }, 
			new Item[] { ModItems.conjured_helmet_2, ModItems.conjured_chestplate_2, ModItems.conjured_leggings_2, ModItems.conjured_boots_2 },
			new Item[] { ModItems.conjured_helmet_3, ModItems.conjured_chestplate_3, ModItems.conjured_leggings_3, ModItems.conjured_boots_3 }});
	public final static Spell summon_arcane_wolf = new SpellSummon(11, "summon_arcane_wolf", EntityArcaneWolf.class);
	public final static Spell raise_skeleton = new SpellSummon(12, "raise_skeleton", EntityRisenSkeleton.class);
	public final static Spell raise_wither_skeleton = new SpellSummon(13, "raise_wither_skeleton", EntityRisenWitherSkeleton.class);
	public final static Spell teleport = new SpellTeleport(14);
	public final static Spell respawn = new SpellRespawn(15);
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
     * Returns the cooldown of this spell in game ticks.
     */
    public short getCooldown()
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
     * Casts this spell based on the given level.
     * @return An indication of whether the spell was cast succesfully.
     */
	public abstract boolean cast(short par1Level, World par2World, EntityPlayer par3EntityPlayer);
	
	/**
	 * Returns the spell with the given index.
	 */
	public static Spell getSpellAt(int index)
	{
		return spellList[Math.max(0, Math.min(index, spellList.length - 1))];
	}
}
