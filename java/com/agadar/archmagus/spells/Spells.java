package com.agadar.archmagus.spells;

import java.util.List;

import com.agadar.archmagus.entities.EntityRisenSkeleton;
import com.agadar.archmagus.entities.EntityRisenWitherSkeleton;
import com.agadar.archmagus.entities.EntityRisenZombie;
import com.agadar.archmagus.entities.EntityRisenZombiePigman;
import com.agadar.archmagus.entities.EntitySummonedWolf;
import com.agadar.archmagus.entities.EntitySummonedCaveSpider;
import com.agadar.archmagus.entities.EntitySummonedSpider;
import com.agadar.archmagus.entities.EntitySummonedWitch;
import com.agadar.archmagus.items.ItemSpellBook;
import com.agadar.archmagus.items.ModItems;
import com.agadar.archmagus.potions.ModPotions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** Holds and manages all Spells. */
public class Spells 
{
	/** The list of all spells. */
	private final static Spell[] spellList = new Spell[256];
	/** All individual spells. */
	public final static Spell blazefire = new SpellBlazeFire(0);
	public final static Spell ghastfire = new SpellGhastFire(1);
	public final static Spell witherblast = new SpellWitherBlast(2);
	public final static Spell summon_wolf = new SpellSummon(3, "wolf", EntitySummonedWolf.class);
	public final static Spell raise_skeleton = new SpellSummon(4, "skeleton", EntityRisenSkeleton.class);
	public final static Spell raise_wither_skeleton = new SpellSummon(5, "wither_skeleton", EntityRisenWitherSkeleton.class);
	public final static Spell raise_zombie = new SpellSummon(6, "zombie", EntityRisenZombie.class);
	public final static Spell raise_zombie_pigman = new SpellSummon(7, "zombie_pigman", EntityRisenZombiePigman.class);
	public final static Spell summon_witch = new SpellSummon(8, "witch", EntitySummonedWitch.class);
	public final static Spell summon_spider = new SpellSummon(9, "spider", EntitySummonedSpider.class);
	public final static Spell summon_cave_spider = new SpellSummon(10, "cave_spider", EntitySummonedCaveSpider.class);
	public final static Spell teleport = new SpellTeleport(11);
	public final static Spell respawn = new SpellRespawn(12);
	//public final static Spell polymorph = new SpellPolymorph(14);
	public final static Spell fireShield = new SpellShield(13, ModPotions.fireShield, "fire");
	public final static Spell earthShield = new SpellShield(14, ModPotions.earthShield, "earth");
	public final static Spell waterShield = new SpellShield(15, ModPotions.waterShield, "water");
	public final static Spell stormShield = new SpellShield(16, ModPotions.stormShield, "storm");
	public final static Spell frostShield = new SpellShield(17, ModPotions.frostShield, "frost");
	public final static Spell blazestorm = new SpellBlazeStorm(18);
	public final static Spell lightningstorm = new SpellLightningStorm(19);
	
	/**
	 * Registers a new spell at the given id.
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
	 * Returns the spell at the given id. May return null.
	 */
	public static Spell getSpellAt(int id)
	{
		if (id >= 0 && id < spellList.length)
		{
			return spellList[id];
		}
		
		return null;
	}
	
    /** Adds all possible spell books to the given List.
     *  Used for placing all possible spell books in the creative menu. */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    public static void getAllSpellBooks(List par1List)
    {
    	for (int i1 = 0; i1 < spellList.length; i1++)
    	{
    		Spell spell = spellList[i1];
    		
    		if (spell != null)
    		{
    			for (short i2 = spell.getMinLevel(); i2 <= spell.getMaxLevel(); i2++)
            	{
                	par1List.add(((ItemSpellBook) ModItems.spell_book).getSpellItemStack(new SpellData(spell, i2)));
            	}
    		}
    	}
    }
}
