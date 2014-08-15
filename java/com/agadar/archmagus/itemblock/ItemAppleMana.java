package com.agadar.archmagus.itemblock;

import java.util.List;

import com.agadar.archmagus.Archmagus;
import com.agadar.archmagus.misc.ManaProperties;
import com.agadar.archmagus.misc.MaxManaMessage;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/** The Mana Apple that permanently increases a player's maximum mana by 2. */
public class ItemAppleMana extends ItemFood 
{
	public ItemAppleMana()
    {
        super(4, 1.2F, false);
        this.setAlwaysEdible();
        this.setUnlocalizedName("apple_mana");
        this.setTextureName(Archmagus.MODID + ":" + getUnlocalizedName().substring(5));
        this.setCreativeTab(ModItemsBlocks.tabArchmagus);
    }
	
	/*@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack p_77636_1_)
    {
        return true;
    }*/
	
	/** Makes the item tooltip text a light blue color. */
    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }
    
	@Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	par3List.add("Maximum Mana +2.");
    }
    
    @Override
    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if (!world.isRemote)
        {
        	ManaProperties props = ManaProperties.get(entityPlayer);
    		int maxMana = props.getMaxMana();
    		
    		if (maxMana <= 18)
    		{
    			maxMana += 2;
	    		props.setMaxMana(maxMana);
	    		Archmagus.networkWrapper.sendTo(new MaxManaMessage(maxMana), (EntityPlayerMP) entityPlayer);
    		}
        }
    }
}
