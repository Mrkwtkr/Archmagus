package com.agadar.archmagus.spells;

public class SpellData 
{
	/** Spell object associated with this SpellData. */
    public final Spell spellObj;
    /** Spell level associated with this SpellData. */
    public final int spellLevel;
    
    public SpellData(Spell par1Spell, int par2)
    {
        this.spellObj = par1Spell;
        this.spellLevel = par2;
    }

    public SpellData(int par1, int par2)
    {
        this(Spell.getSpellAt(par1), par2);
    }
}
