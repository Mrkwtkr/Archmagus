package com.agadar.archmagus.itemblock;

import java.util.ArrayList;
import java.util.List;

import com.agadar.archmagus.Archmagus;
import com.agadar.archmagus.potion.ModPotions;
import com.agadar.brewingapi.BrewingRecipes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;

public class ItemPotionBase extends ItemPotion 
{
	@SideOnly(Side.CLIENT)
    private IIcon manaOverlayIcon;
	@SideOnly(Side.CLIENT)
	private IIcon manaRegenOverlayIcon;
    
	public ItemPotionBase()
	{
		super();
		this.setUnlocalizedName("potion_base");
		this.setTextureName("potion");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item par1Item, CreativeTabs par2CreativeTab, List par3List)
    {
		/** Potion of Mana. */
		ItemStack manaPotion = new ItemStack(par1Item, 1, 1);		
		List<PotionEffect> effects = new ArrayList<PotionEffect>();
		effects.add(new PotionEffect(ModPotions.mana.id, 1, 0));		
		BrewingRecipes.brewing().setEffects(manaPotion, effects);
		par3List.add(manaPotion);
		/** Potion of Mana (Amplified). */
		ItemStack manaPotionAmplified = new ItemStack(par1Item, 1, 1);
		effects = new ArrayList<PotionEffect>();
		effects.add(new PotionEffect(ModPotions.mana.id, 1, 1));
		BrewingRecipes.brewing().setEffects(manaPotionAmplified, effects);
		par3List.add(manaPotionAmplified);
		/** Splash Potion of Mana. */
		manaPotion = manaPotion.copy();
		manaPotion.setItemDamage(16384);
		par3List.add(manaPotion);
		/** Splash Potion of Mana (Amplified). */
		manaPotionAmplified = manaPotionAmplified.copy();
		manaPotionAmplified.setItemDamage(16384);
		par3List.add(manaPotionAmplified);
		
		/** Potion of Mana Regeneration. */
		ItemStack manaRegenPotion = new ItemStack(par1Item, 1, 1);		
		effects = new ArrayList<PotionEffect>();
		effects.add(new PotionEffect(ModPotions.manaRegen.id, 900, 0));		
		BrewingRecipes.brewing().setEffects(manaRegenPotion, effects);
		par3List.add(manaRegenPotion);
		/** Potion of Mana Regeneration (Amplified). */
		ItemStack manaRegenPotionAmplified = new ItemStack(par1Item, 1, 1);
		effects = new ArrayList<PotionEffect>();
		effects.add(new PotionEffect(ModPotions.manaRegen.id, 450, 1));
		BrewingRecipes.brewing().setEffects(manaRegenPotionAmplified, effects);
		par3List.add(manaRegenPotionAmplified);
		/** Splash Potion of Mana Regeneration. */
		manaRegenPotion = new ItemStack(par1Item, 1, 16384);		
		effects = new ArrayList<PotionEffect>();
		effects.add(new PotionEffect(ModPotions.manaRegen.id, 660, 0));		
		BrewingRecipes.brewing().setEffects(manaRegenPotion, effects);
		par3List.add(manaRegenPotion);
		/** Splash Potion of Mana Regeneration (Amplified). */
		manaRegenPotionAmplified = new ItemStack(par1Item, 1, 16384);
		effects = new ArrayList<PotionEffect>();
		effects.add(new PotionEffect(ModPotions.manaRegen.id, 320, 1));
		BrewingRecipes.brewing().setEffects(manaRegenPotionAmplified, effects);
		par3List.add(manaRegenPotionAmplified);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
    {
        return 16777215;
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public IIcon getIcon(ItemStack par1ItemStack, int par2Pass)
	{
		if (par2Pass == 0)
		{
			List<PotionEffect> effects = this.getEffects(par1ItemStack);
			
			if (effects.size() > 0)
			{
				int potionId = effects.get(0).getPotionID();
				
				if (potionId == ModPotions.mana.id)
				{
					return this.manaOverlayIcon;
				}
				else if (potionId == ModPotions.manaRegen.id)
				{
					return this.manaRegenOverlayIcon;
				}
			}
		}
		
		return super.getIcon(par1ItemStack, par2Pass);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
		super.registerIcons(par1IconRegister);
        this.manaOverlayIcon = par1IconRegister.registerIcon(Archmagus.MODID + ":mana_potion_overlay");
        this.manaRegenOverlayIcon = par1IconRegister.registerIcon(Archmagus.MODID + ":mana_regen_overlay");
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return false;
    }
}
