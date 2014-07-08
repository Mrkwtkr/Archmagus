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
	//public final static Spell lightning = new SpellLightning(3);
	public final static Spell summon_wolf = new SpellSummon(4, "summon_wolf", EntitySummonedWolf.class);
	public final static Spell raise_skeleton = new SpellSummon(5, "raise_skeleton", EntityRisenSkeleton.class);
	public final static Spell raise_wither_skeleton = new SpellSummon(6, "raise_wither_skeleton", EntityRisenWitherSkeleton.class);
	public final static Spell raise_zombie = new SpellSummon(7, "raise_zombie", EntityRisenZombie.class);
	public final static Spell raise_zombie_pigman = new SpellSummon(8, "raise_zombie_pigman", EntityRisenZombiePigman.class);
	public final static Spell summon_witch = new SpellSummon(9, "summon_witch", EntitySummonedWitch.class);
	public final static Spell summon_spider = new SpellSummon(10, "summon_spider", EntitySummonedSpider.class);
	public final static Spell summon_cave_spider = new SpellSummon(11, "summon_cave_spider", EntitySummonedCaveSpider.class);
	public final static Spell teleport = new SpellTeleport(12);
	public final static Spell respawn = new SpellRespawn(13);
	public final static Spell polymorph = new SpellPolymorph(14);
	
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
	 * Returns the spell at the given id. 
	 * If id <= 0 then the spell with id = 0 is returned.
	 * If id >= the highest possible id, then the spell with the highest possible id is returned.
	 * May return null.
	 */
	public static Spell getSpellAt(int id)
	{
		return spellList[Math.max(0, Math.min(id, spellList.length - 1))];
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
