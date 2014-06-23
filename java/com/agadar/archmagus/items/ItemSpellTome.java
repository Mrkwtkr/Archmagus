package com.agadar.archmagus.items;

import java.util.List;

import com.agadar.archmagus.spells.Spell;
import com.agadar.archmagus.spells.SpellData;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class ItemSpellTome extends Item 
{
	public ItemSpellTome()
	{
		this.setMaxStackSize(1);
		this.setUnlocalizedName("spellTome");
		this.setTextureName("book_enchanted");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }

	@Override
    public boolean isItemTool(ItemStack par1ItemStack)
    {
        return false;
    }

    public NBTTagList func_92110_g(ItemStack par1ItemStack)
    {
        return par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("StoredSpell", 9) ? (NBTTagList)par1ItemStack.stackTagCompound.getTag("StoredSpell") : new NBTTagList();
    }
    
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        NBTTagList nbttaglist = this.func_92110_g(par1ItemStack);

        if (nbttaglist != null)
        {
            for (int i = 0; i < nbttaglist.tagCount(); ++i)
            {
                short short1 = nbttaglist.getCompoundTagAt(i).getShort("id");
                short short2 = nbttaglist.getCompoundTagAt(i).getShort("lvl");

                if (Spell.spellList[short1] != null)
                {
                    par3List.add(Spell.spellList[short1].getTranslatedName(short2));
                }
            }
        }
    }

    /**
     * Adds a stored spell to a spell tome ItemStack.
     */
    public void addSpell(ItemStack par1ItemStack, SpellData par2SpellData)
    {
        NBTTagList nbttaglist = this.func_92110_g(par1ItemStack);
        boolean flag = true;

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);

            if (nbttagcompound.getShort("id") == par2SpellData.spellObj.effectId)
            {
                if (nbttagcompound.getShort("lvl") < par2SpellData.spellLevel)
                {
                    nbttagcompound.setShort("lvl", (short)par2SpellData.spellLevel);
                }

                flag = false;
                break;
            }
        }

        if (flag)
        {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setShort("id", (short)par2SpellData.spellObj.effectId);
            nbttagcompound1.setShort("lvl", (short)par2SpellData.spellLevel);
            nbttaglist.appendTag(nbttagcompound1);
        }

        if (!par1ItemStack.hasTagCompound())
        {
            par1ItemStack.setTagCompound(new NBTTagCompound());
        }

        par1ItemStack.getTagCompound().setTag("StoredSpell", nbttaglist);
    }
    
    /**
     * Returns the ItemStack of a spell version of this item.
     */
    public ItemStack getSpellItemStack(SpellData par1SpellData)
    {
        ItemStack itemstack = new ItemStack(this);
        this.addSpell(itemstack, par1SpellData);
        return itemstack;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    public void func_92113_a(Spell par1Spell, List par2List)
    {
        for (int i = par1Spell.getMinLevel(); i <= par1Spell.getMaxLevel(); ++i)
        {
            par2List.add(this.getSpellItemStack(new SpellData(par1Spell, i)));
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if (!par2World.isRemote) 
    	{				
    		NBTTagList nbttaglist = this.func_92110_g(par1ItemStack);
    		if (nbttaglist != null)
    		{
    			for (int i = 0; i < nbttaglist.tagCount(); ++i)
    			{
    				short cooldown = nbttaglist.getCompoundTagAt(i).getShort("cooldown");	            	
    				if (cooldown == 0)
    				{
    					short spellId = nbttaglist.getCompoundTagAt(i).getShort("id");
    					if (par3EntityPlayer.getFoodStats().getFoodLevel() >= Spell.spellList[spellId].getHungerCost())
    					{
    						nbttaglist.getCompoundTagAt(i).setShort("cooldown", Spell.coolDown);
    						par3EntityPlayer.getFoodStats().addStats(-Spell.spellList[spellId].getHungerCost(), 0);        		
    						short level = nbttaglist.getCompoundTagAt(i).getShort("lvl");
    						Spell.spellList[spellId].cast(level, par2World, par3EntityPlayer);
    					}
    				}
    			}
    		}
    	}

    	return par1ItemStack;
    }
    
    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) 
    {
    	if (!par2World.isRemote)
    	{
    		NBTTagList nbttaglist = this.func_92110_g(par1ItemStack);
			
			if (nbttaglist != null)
	        {
	            for (int i = 0; i < nbttaglist.tagCount(); ++i)
	            {
	            	short cooldown = nbttaglist.getCompoundTagAt(i).getShort("cooldown");
	            	
	            	if (cooldown > 0)
	            	{
	            		nbttaglist.getCompoundTagAt(i).setShort("cooldown", (short) (cooldown - 1));
	            	}
	            }
			}
    	}
    }
}
