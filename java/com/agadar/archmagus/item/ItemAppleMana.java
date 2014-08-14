package com.agadar.archmagus.item;

import com.agadar.archmagus.Archmagus;
import com.agadar.archmagus.ManaProperties;
import com.agadar.archmagus.MaxManaMessage;
import com.agadar.archmagus.misc.References;

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
        this.setTextureName("apple");//References.MODID + ":" + getUnlocalizedName().substring(5));
        this.setCreativeTab(ModItems.tabSpellBooks);
    }
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack p_77636_1_)
    {
        return true;
    }
	
	/** Makes the item tooltip text a light blue color. */
    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
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
