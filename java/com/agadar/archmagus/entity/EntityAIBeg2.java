package com.agadar.archmagus.entity;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/** A near-copy of vanilla's EntityAIBeg. Used for the EntitySummonedWolf. */
public class EntityAIBeg2 extends EntityAIBase
{
    private EntitySummonedWolf theWolf;
    private EntityPlayer thePlayer;
    private World worldObject;
    private float minPlayerDistance;
    private int field_75384_e;

    public EntityAIBeg2(EntitySummonedWolf par1EntityWolf, float par2)
    {
        this.theWolf = par1EntityWolf;
        this.worldObject = par1EntityWolf.worldObj;
        this.minPlayerDistance = par2;
        this.setMutexBits(2);
    }

    @Override
    public boolean shouldExecute()
    {
        this.thePlayer = this.worldObject.getClosestPlayerToEntity(this.theWolf, (double)this.minPlayerDistance);
        return this.thePlayer == null ? false : this.hasPlayerGotBreedingItemInHand(this.thePlayer);
    }

    @Override
    public boolean continueExecuting()
    {
        return !this.thePlayer.isEntityAlive() ? false : (this.theWolf.getDistanceSqToEntity(this.thePlayer) > (double)(this.minPlayerDistance * this.minPlayerDistance) ? false : this.field_75384_e > 0 && this.hasPlayerGotBreedingItemInHand(this.thePlayer));
    }

    @Override
    public void startExecuting()
    {
        this.theWolf.func_70918_i(true);
        this.field_75384_e = 40 + this.theWolf.getRNG().nextInt(40);
    }

    @Override
    public void resetTask()
    {
        this.theWolf.func_70918_i(false);
        this.thePlayer = null;
    }

    @Override
    public void updateTask()
    {
        this.theWolf.getLookHelper().setLookPosition(this.thePlayer.posX, this.thePlayer.posY + (double)this.thePlayer.getEyeHeight(), this.thePlayer.posZ, 10.0F, (float)this.theWolf.getVerticalFaceSpeed());
        --this.field_75384_e;
    }

    /** Returns whether the player is holding a breeding item. */
    private boolean hasPlayerGotBreedingItemInHand(EntityPlayer par1EntityPlayer)
    {
        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
        return itemstack == null ? false : this.theWolf.isBreedingItem(itemstack);
    }
}
