package com.httymd.entity.dragon;

import com.httymd.entity.EntityDragon;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.UUID;

public class EntityNadder extends EntityDragon
{

    public static final AttributeModifier chickenSpeedModifier =
            new AttributeModifier(UUID.fromString("0A0E0DDB-5AAE-4153-9356-83A01BEF91A0"), "Chicken flight speed boost",
                                  0.09, 1);

    public EntityNadder(World world)
    {
        super(world);
        this.setSize(1.4F, 3.5F);
        this.stepHeight = 1;
        this.getNavigator().getNodeProcessor().setCanSwim(false);
        this.tasks.addTask(1, new EntityAISwimming(this));
        //this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 0.9D, false));
        this.tasks.addTask(3, new EntityAIFollowOwner(this, 1.5D, 20F, 50F));
        this.tasks.addTask(4, new EntityAITempt(this, 0.7, Items.FISH, true));
        this.tasks.addTask(4, new EntityAITempt(this, 0.7, Items.CHICKEN, true));
        this.tasks.addTask(4, new EntityAITempt(this, 0.7, Items.COOKED_CHICKEN, true));
        this.tasks.addTask(5, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
        this.setTamed(false);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(healthAtt).setBaseValue(17);
        this.getEntityAttribute(speedAtt).setBaseValue(0.6);
        this.getEntityAttribute(damageAtt).setBaseValue(1.5);
        this.getEntityAttribute(flyingSpeed).setBaseValue(0.1);
    }

    @Override
    public boolean canTame(EntityLivingBase e, ItemStack item)
    {
        return item.getItem() == Items.FISH || item.getItem() == Items.CHICKEN || item.getItem() == Items.COOKED_CHICKEN;
    }

    @Override
    public boolean onFeed(EntityLivingBase feeder, ItemStack feed)
    {

        IAttributeInstance instance = this.getEntityAttribute(flyingSpeed);
        if (feed.getItem() == Items.CHICKEN || feed.getItem() == Items.COOKED_CHICKEN)
        {
            instance.applyModifier(chickenSpeedModifier);
            this.setHealth(this.getHealth() + 0.5F);
        }
        else if (instance.getModifier(chickenSpeedModifier.getID()) != null)
        {
            instance.removeModifier(chickenSpeedModifier);
        } // If feed anything other then chicken, cancels speed effect, TODO make it timed

        return super.onFeed(feeder, feed);
    }
}
