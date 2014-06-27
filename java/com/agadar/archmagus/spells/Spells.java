package com.agadar.archmagus.spells;

import net.minecraft.item.Item;

import com.agadar.archmagus.entities.EntityRisenSkeleton;
import com.agadar.archmagus.entities.EntityRisenWitherSkeleton;
import com.agadar.archmagus.entities.EntityRisenZombie;
import com.agadar.archmagus.entities.EntitySpiritWolf;
import com.agadar.archmagus.items.ModItems;

public class Spells 
{
	/** The list of all spells. */
	private final static Spell[] spellList = new Spell[256];
	/** All individual spells. */
	public final static Spell blazefire = new SpellBlazeFire(0);
	public final static Spell ghastfire = new SpellGhastFire(1);
	public final static Spell witherblast = new SpellWitherBlast(2);
	public final static Spell lightning = new SpellLightning(3);
	public final static Spell flamestrike = new SpellFlamestrike(4);
	public final static Spell thunderstrike = new SpellThunderStrike(5);
	public final static Spell conjure_axe = new SpellConjureItem(6, "conjure_axe", new Item[] { ModItems.conjured_axe_1, ModItems.conjured_axe_2, ModItems.conjured_axe_3, ModItems.conjured_axe_4 });
	public final static Spell conjure_bow = new SpellConjureItem(7, "conjure_bow", new Item[] { ModItems.conjured_bow });
	public final static Spell conjure_hoe = new SpellConjureItem(8, "conjure_hoe", new Item[] { ModItems.conjured_hoe });
	public final static Spell conjure_pickaxe = new SpellConjureItem(9, "conjure_pickaxe", new Item[] { ModItems.conjured_pickaxe_1, ModItems.conjured_pickaxe_2, ModItems.conjured_pickaxe_3, ModItems.conjured_pickaxe_4 });
	public final static Spell conjure_shovel = new SpellConjureItem(10, "conjure_shovel", new Item[] { ModItems.conjured_shovel_1, ModItems.conjured_shovel_2, ModItems.conjured_shovel_3, ModItems.conjured_shovel_4 });
	public final static Spell conjure_sword = new SpellConjureItem(11, "conjure_sword", new Item[] { ModItems.conjured_sword_1, ModItems.conjured_sword_2, ModItems.conjured_sword_3, ModItems.conjured_sword_4 });
	public final static Spell conjure_armor = new SpellConjureArmor(12, "conjure_armor", new Item[][] { 
			new Item[] { ModItems.conjured_helmet_1, ModItems.conjured_chestplate_1, ModItems.conjured_leggings_1, ModItems.conjured_boots_1 }, 
			new Item[] { ModItems.conjured_helmet_2, ModItems.conjured_chestplate_2, ModItems.conjured_leggings_2, ModItems.conjured_boots_2 },
			new Item[] { ModItems.conjured_helmet_3, ModItems.conjured_chestplate_3, ModItems.conjured_leggings_3, ModItems.conjured_boots_3 }});
	public final static Spell summon_spirit_wolf = new SpellSummon(13, "summon_spirit_wolf", EntitySpiritWolf.class);
	public final static Spell raise_skeleton = new SpellSummon(14, "raise_skeleton", EntityRisenSkeleton.class);
	public final static Spell raise_wither_skeleton = new SpellSummon(15, "raise_wither_skeleton", EntityRisenWitherSkeleton.class);
	public final static Spell raise_zombie = new SpellSummon(16, "raise_zombie", EntityRisenZombie.class);
	public final static Spell teleport = new SpellTeleport(17);
	public final static Spell respawn = new SpellRespawn(18);
	
	/**
	 * Registers a new spell with the given ID.
	 */
	protected static void registerSpell(Spell par1Spell, int par2effectId)
	{
		if (spellList[par2effectId] != null)
        {
            throw new IllegalArgumentException("Duplicate spell id!");
        }
        else
        {
        	spellList[par2effectId] = par1Spell;
        }
	}
	
	/**
	 * Returns the spell with the given index.
	 */
	public static Spell getSpellAt(int index)
	{
		return spellList[Math.max(0, Math.min(index, spellList.length - 1))];
	}
}
