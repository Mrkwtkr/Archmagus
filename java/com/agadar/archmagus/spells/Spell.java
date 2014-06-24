package com.agadar.archmagus.spells;

import java.util.Random;

import com.agadar.archmagus.items.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public abstract class Spell 
{
	/** The list of all spells. */
	public final static Spell[] spellList = new Spell[256];
	public final static Spell blazeFire = new SpellBlazeFire(0);
	public final static Spell ghastFire = new SpellGhastFire(1);
	public final static Spell conjureAxe = new SpellConjureItem(2,
			"conjure_axe", new Item[] { ModItems.conjured_axe_1,
					ModItems.conjured_axe_2, ModItems.conjured_axe_3,
					ModItems.conjured_axe_4 });
	public final static Spell conjureBow = new SpellConjureItem(3,
			"conjure_bow", new Item[] { ModItems.conjured_bow });
	public final static Spell conjureHoe = new SpellConjureItem(4,
			"conjure_hoe", new Item[] { ModItems.conjured_hoe });
	public final static Spell conjurePickaxe = new SpellConjureItem(5,
			"conjure_pickaxe", new Item[] { ModItems.conjured_pickaxe_1,
					ModItems.conjured_pickaxe_2, ModItems.conjured_pickaxe_3,
					ModItems.conjured_pickaxe_4 });
	public final static Spell conjureShovel = new SpellConjureItem(6,
			"conjure_shovel", new Item[] { ModItems.conjured_shovel_1,
					ModItems.conjured_shovel_2, ModItems.conjured_shovel_3,
					ModItems.conjured_shovel_4 });
	public final static Spell conjureSword = new SpellConjureItem(7,
			"conjure_sword", new Item[] { ModItems.conjured_sword_1,
					ModItems.conjured_sword_2, ModItems.conjured_sword_3,
					ModItems.conjured_sword_4 });
	public final static Spell conjureHelmet = new SpellConjureItem(8,
			"conjure_helmet", new Item[] { ModItems.conjured_helmet_1,
					ModItems.conjured_helmet_2, ModItems.conjured_helmet_3 });
	public final static Spell conjureChestplate = new SpellConjureItem(9,
			"conjure_chestplate", new Item[] { ModItems.conjured_chestplate_1,
					ModItems.conjured_chestplate_2, ModItems.conjured_chestplate_3 });
	public final static Spell conjureLeggings = new SpellConjureItem(10,
			"conjure_leggings", new Item[] { ModItems.conjured_leggings_1,
					ModItems.conjured_leggings_2, ModItems.conjured_leggings_3 });
	public final static Spell conjureBoots = new SpellConjureItem(11,
			"conjure_boots", new Item[] { ModItems.conjured_boots_1,
					ModItems.conjured_boots_2, ModItems.conjured_boots_3 });
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
