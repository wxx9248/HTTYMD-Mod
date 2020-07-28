package com.httymd.entity;

import com.httymd.HTTYMDMod;
import com.httymd.item.registry.ItemRegistry;
import com.httymd.item.util.ItemUtils;
import com.httymd.util.DragonDamageSource;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityDragon extends EntityTameableFlying
{

    private static final int BOOL_IS_ANGRY = 2;

    private static final String NBT_IS_STARTLED = "IsStartled";

    protected boolean startled = false;

    public EntityDragon(World world)
    {
        super(world);
        this.isImmuneToFire     = true;
        this.ignoreFrustumCheck = true;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(damageAtt);
    }

    public boolean isRideableBy(Entity rider)
    {
        return rider != null && rider instanceof EntityLivingBase;
    }

    private boolean isRidden()
    {
        return this.isRideableBy(this.getControllingPassenger());
    }

    private void onMount(Entity mounter)
    {
        mounter.rotationYaw   = this.rotationYaw;
        mounter.rotationPitch = this.rotationPitch;

        if (!this.world.isRemote)
        {
            mounter.startRiding(this);
        }
    }

    @Override
    public void travel(float strafe, float up, float forward)
    {
		/*if (this.isRidden()) {
			Entity r = this.getControllingPassenger();
			this.prevRotationYaw = this.rotationYaw = r.rotationYaw;
			this.rotationPitch = r.rotationPitch * 0.8F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
			if (r instanceof EntityLivingBase) {
				strafe = ((EntityLivingBase) r).moveStrafing * 0.5F;
				forward = ((EntityLivingBase) r).moveForward;
			}
			forward *= 0.5;
			strafe *= 0.5;
			if (this.isFlying() && forward > 0 && HTTYMDMod.getConfig().getVerticalDragonRiding()) {
				float sin = MathHelper.sin(this.rotationPitch * (float) Math.PI / 180.0F);
				this.motionY += (-sin * forward) * 1.5;
			}
			if (this.world.isRemote)
				return;
		}*/
        if (this.isRidden())
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase) this.getControllingPassenger();
            this.rotationYaw     = entitylivingbase.rotationYaw;
            this.prevRotationYaw = this.rotationYaw;
            this.rotationPitch   = entitylivingbase.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.renderYawOffset = this.rotationYaw;
            this.rotationYawHead = this.renderYawOffset;
            strafe               = entitylivingbase.moveStrafing * 0.5F;
            forward              = entitylivingbase.moveForward;
        }
        super.travel(strafe, up, forward);
    }

    /**
     * Behavior described when eating food
     *
     * @param feeder The feeding entity
     * @param feed   The food item
     *
     * @return Should the item stack will decrease
     */
    public boolean onFeed(EntityLivingBase feeder, ItemStack feed)
    {
        if (HTTYMDMod.getConfig().canFeedHeal())
        {
            this.setHealth(this.getHealth() + 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean processInteract(EntityPlayer ply, EnumHand h)
    {
        ItemStack hand = ply.getHeldItem(h);
        if (hand != null)
        {
            if (HTTYMDMod.getConfig().isDebugMode() && hand.getItem() == ItemRegistry.wing)
            {
                this.onTakeoff();
                return true;
            }
            else if (ItemUtils.isFood(hand) && !this.canTame(ply, hand) &&
                     this.onFeed(ply, hand) && !ply.capabilities.isCreativeMode && hand.getCount() <= 0)
                ply.inventory.setInventorySlotContents(ply.inventory.currentItem, null);
        }
        if (this.isOwner(ply) && ply.isSneaking())
        {
            setSitting(!isSitting());
            return true;
        }
        else if (this.isOwner(ply) && this.isRideableBy(ply))
        {
            setSitting(false);
            this.onMount(ply);
            return true;
        }
        return super.processInteract(ply, h);
    }

    @Override
    public boolean attackEntityAsMob(Entity target)
    {
        double damage    = 2.0D;
        int    knockback = 0;

        if (target instanceof EntityLivingBase)
        {
            //damage += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase) target);
            knockback += EnchantmentHelper.getKnockbackModifier(this);
        }

        if (target.attackEntityFrom(DragonDamageSource.getDirectDamage(this), (float) damage))
        {
            if (knockback > 0)
            {
                target.addVelocity(-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F,
                                   0.1D,
                                   MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F);
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int fire = EnchantmentHelper.getFireAspectModifier(this);

            if (fire > 0)
                target.setFire(fire * 4);

            //if (target instanceof EntityLivingBase)
            //EnchantmentHelper.func_151384_a((EntityLivingBase) target, this);

            //EnchantmentHelper.func_151385_b(this, target);
            EnchantmentHelper.applyThornEnchantments(this, target);

            return true;
        }
        return false;
    }

    public boolean isAngry()
    {
        return (this.dataManager.get(BOOL_WATCHER) & BOOL_IS_ANGRY) != 0;
    }

    /**
     * Sets whether this dragon is angry or not.
     */
    public void setAngry(boolean p_70916_1_)
    {
        byte boolByte = this.dataManager.get(BOOL_WATCHER);

        if (p_70916_1_)
            this.dataManager.set(BOOL_WATCHER, Byte.valueOf((byte) (boolByte | BOOL_IS_ANGRY)));
        else
            this.dataManager.set(BOOL_WATCHER, Byte.valueOf((byte) (boolByte & -(BOOL_IS_ANGRY + 1))));
    }

    public boolean isStartled()
    {
        return this.startled;
    }

    public void setStartled(boolean startled)
    {
        this.startled = startled;
    }

    public boolean isTameable(EntityLivingBase tamer)
    {
        return !this.isAngry();
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag)
    {
        super.readEntityFromNBT(tag);
        this.setStartled(tag.getBoolean(NBT_IS_STARTLED));
    }

    @Override
    public void setAttackTarget(EntityLivingBase p_70624_1_)
    {
        super.setAttackTarget(p_70624_1_);

        if (p_70624_1_ == null)
            this.setAngry(false);
        else if (!this.isTamed())
            this.setAngry(true);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag)
    {
        super.writeEntityToNBT(tag);
        tag.setBoolean(NBT_IS_STARTLED, this.isStartled());
    }

    /**
     * Sets isImmuneToFire to false, allows dragon burning
     *
     * <p>
     * For dragons that are not so fire proof
     * </p>
     */
    protected void setNotFireproof()
    {
        this.isImmuneToFire = false;
    }

    public int getAge()
    {
        return 0;//TODO
    }

    /**
     * For vehicles, the first passenger is generally considered the controller and "drives" the vehicle. For example,
     * Pigs, Horses, and Boats are generally "steered" by the controlling passenger.
     */
    @Override
    @Nullable
    public Entity getControllingPassenger()
    {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }
}
