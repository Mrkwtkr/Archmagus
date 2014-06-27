package com.agadar.archmagus.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityRisenWitherSkeleton extends EntitySummonable implements IRangedAttackMob
{
    public EntityRisenWitherSkeleton(World par1World)
    {
        super(par1World);
        
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.2D, true));
        this.tasks.addTask(3, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));

        this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
    }

    @Override
    protected String getLivingSound()
    {
        return "mob.skeleton.say";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.skeleton.hurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.skeleton.death";
    }

    @Override
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound("mob.skeleton.step", 0.15F, 1.0F);
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity)
    {
    	((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
        float attackDamage = (float)getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
    	return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), attackDamage);
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        
        if (this.worldObj.isRemote)
        {
            this.setSize(0.72F, 2.34F);
        }
    }

    @Override
    public void updateRidden()
    {
        super.updateRidden();

        if (this.ridingEntity instanceof EntityCreature)
        {
            EntityCreature entitycreature = (EntityCreature)this.ridingEntity;
            this.renderYawOffset = entitycreature.renderYawOffset;
        }
    }

    @Override
    public double getYOffset()
    {
        return super.getYOffset() - 0.5D;
    }

	@Override
	protected String getSwimSound()
    {
        return "game.hostile.swim";
    }

	@Override
    protected String getSplashSound()
    {
        return "game.hostile.swim.splash";
    }
	
	@Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else if (super.attackEntityFrom(par1DamageSource, par2))
        {
            Entity entity = par1DamageSource.getEntity();

            if (this.riddenByEntity != entity && this.ridingEntity != entity)
            {
                if (entity != this)
                {
                    this.entityToAttack = entity;
                }

                return true;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

	@Override
	protected String func_146067_o(int p_146067_1_)
    {
        return p_146067_1_ > 4 ? "game.hostile.hurt.fall.big" : "game.hostile.hurt.fall.small";
    }

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase var1, float var2) 
	{
		return;
	}
}
