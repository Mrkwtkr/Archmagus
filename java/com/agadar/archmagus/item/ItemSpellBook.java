package com.agadar.archmagus.item;

import java.util.List;

import com.agadar.archmagus.help.References;
import com.agadar.archmagus.spell.Spell;
import com.agadar.archmagus.spell.SpellData;
import com.agadar.archmagus.spell.Spells;
import com.agadar.archmagus.spell.aoe.SpellAoE;
import com.agadar.archmagus.spell.shield.SpellShield;
import com.agadar.archmagus.spell.summon.SpellSummon;
import com.agadar.archmagus.spell.targeted.ISpellTargeted;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

/** This Item allows for casting spells. */
public class ItemSpellBook extends Item 
{
	public ItemSpellBook()
	{
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setUnlocalizedName("spell_book");
		this.setTextureName(References.MODID + ":" + getUnlocalizedName().substring(5));
		this.setCreativeTab(ModItems.tabSpellBooks);
	}
	
	/** Gives the item the enchanted glow. */ 
	@Override
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }

	/** Returns the ItemStack's spell tag. If it doesn't have one then
	 *  it is first assigned one before it is returned. */
    public NBTTagCompound getSpellTag(ItemStack par1ItemStack)
    {
    	if (par1ItemStack.stackTagCompound == null) par1ItemStack.stackTagCompound = new NBTTagCompound();	
    	if (!par1ItemStack.stackTagCompound.hasKey("spell")) par1ItemStack.stackTagCompound.setTag("spell", new NBTTagCompound());
    	
        return (NBTTagCompound) par1ItemStack.stackTagCompound.getTag("spell");
    }
    
    /** Adds additional information to the item's tooltip. */
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        SpellData spellData = SpellData.readFromNBTTagCompound(this.getSpellTag(par1ItemStack));

        if (spellData.spellObj != null)
        {
        	if (spellData.spellObj instanceof ISpellTargeted) par3List.add("Targeted");
        	else if (spellData.spellObj instanceof SpellSummon) par3List.add("Summoning");
        	else if (spellData.spellObj instanceof SpellShield) par3List.add("Enhancement");
        	else if (spellData.spellObj instanceof SpellAoE) par3List.add("Area of effect");
        	else par3List.add("Miscellaneous");
        	
        	if (spellData.spellCooldown != 0) par3List.add(EnumChatFormatting.RED + "Cooldown: " + (spellData.spellCooldown / 20) + " seconds");
        }
    }
    
    /** Returns the given ItemStack's display name. */
    @Override
    public String getItemStackDisplayName(ItemStack par1ItemStack)
    {
    	SpellData spellData = SpellData.readFromNBTTagCompound(this.getSpellTag(par1ItemStack));

        if (spellData.spellObj != null) return (spellData.spellObj.getTranslatedName(spellData.spellLevel));
        
        return super.getItemStackDisplayName(par1ItemStack);
    }

    /**
     * Adds the given spell data to the given ItemStack.
     */
    public void addSpell(ItemStack par1ItemStack, SpellData par2SpellData)
    {           	
    	if (par1ItemStack.stackTagCompound == null) par1ItemStack.stackTagCompound = new NBTTagCompound();
    	
    	NBTTagCompound spellTag = SpellData.writeToNBTTagCompound(par2SpellData);
    	par1ItemStack.stackTagCompound.setTag("spell", spellTag);
    }
    
    /** Attempts to combine two spell books. Returns null if it failed.
     *  Used for combining spell books in an Anvil. */
    public ItemStack tryCombine(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
    	if (par1ItemStack.getItem() == ModItems.spell_book && par2ItemStack.getItem() == ModItems.spell_book &&
    			par1ItemStack.stackTagCompound != null && par2ItemStack.stackTagCompound != null &&
    			par1ItemStack.stackTagCompound.hasKey("spell") && par2ItemStack.stackTagCompound.hasKey("spell"))
    	{
    		NBTTagCompound spellTag1 = par1ItemStack.stackTagCompound.getCompoundTag("spell");               
    		NBTTagCompound spellTag2 = par2ItemStack.stackTagCompound.getCompoundTag("spell"); 
    		
    		SpellData spellData1 = SpellData.readFromNBTTagCompound(spellTag1);
    		SpellData spellData2 = SpellData.readFromNBTTagCompound(spellTag2);   		
    		SpellData spellData3 = SpellData.tryCombine(spellData1, spellData2);
    		
    		if (spellData3 != null) return this.getSpellItemStack(spellData3);
    	}
    	
    	return null;
    }
    
    /**
     * Returns an ItemStack of a single spell book with the given spell data.
     */
    public ItemStack getSpellItemStack(SpellData par1SpellData)
    {
        ItemStack itemstack = new ItemStack(this);
        this.addSpell(itemstack, par1SpellData);
        return itemstack;
    }

    /** Makes the item tooltip text a light blue color. */
    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }

    /** Casts the spell book's spell on right click. */
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {		
    	NBTTagCompound spellTag = this.getSpellTag(par1ItemStack);
    	SpellData spellData = SpellData.readFromNBTTagCompound(spellTag);	
    	boolean inCreative = par3EntityPlayer.capabilities.isCreativeMode;
    	
    	if (spellData.spellCooldown > 0 && !inCreative) return par1ItemStack;    	
    	if (par3EntityPlayer.getFoodStats().getFoodLevel() < spellData.spellObj.getHungerCost() && !inCreative) return par1ItemStack;
    	
    	if (spellData.castSpell(par2World, par3EntityPlayer))
    	{
    		if (!par2World.isRemote) SpellData.startCooldown(spellTag);  		
    		if (!inCreative) par3EntityPlayer.getFoodStats().addStats(-spellData.spellObj.getHungerCost(), 0);
    	}

    	return par1ItemStack;
    }
    
    /** Updates the remaining cooldown every game tick. */
    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) 
    {
    	if (!par2World.isRemote) SpellData.tickCooldown(this.getSpellTag(par1ItemStack));
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item par1Item, CreativeTabs par2CreativeTab, List par3List)
    {
    	for (int i1 = 0; i1 < Spells.spellList.length; i1++)
    	{
    		Spell spell = Spells.spellList[i1];
    		
    		if (spell != null)
    		{
    			for (short i2 = spell.getMinLevel(); i2 <= spell.getMaxLevel(); i2++)
            	{
    				par3List.add(((ItemSpellBook) ModItems.spell_book).getSpellItemStack(new SpellData(spell, i2)));
            	}
    		}
    	}
    }
}
