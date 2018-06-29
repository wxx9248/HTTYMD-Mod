package com.httymd.entity;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.httymd.HTTYMDMod;
import com.httymd.item.registry.ItemRegistry;
import com.httymd.util.Utils;

public abstract class EntityTameableFlying extends EntityTameable implements ITameable, IFlyable {

	///////////////////////////////////////////////////////////////////////////
	// NBT Names
	private static final String NBT_IS_FLYING = "Flying";
	///////////////////////////////////////////////////////////////////////////
	// Entity Attributes
	public static final IAttribute flyingSpeed = new RangedAttribute(null, Utils.getModString("flyingSpeed"), 1D, 0.0D,
			Double.MAX_VALUE).setDescription("Flying Speed").setShouldWatch(true);
	public static final IAttribute flyingYaw = new RangedAttribute(null, Utils.getModString("flyingYaw"), 25D, 0.0D,
			Double.MAX_VALUE).setDescription("Flying Yaw Speed").setShouldWatch(true);
	public static final IAttribute flyingPitch = new RangedAttribute(null, Utils.getModString("flyingPitch"), 20D, 0.0D,
			Double.MAX_VALUE).setDescription("Flying Pitch Speed").setShouldWatch(true);
	///////////////////////////////////////////////////////////////////////////
	// Datawatcher
	public static final DataParameter<Byte> BOOL_WATCHER = new DataParameter<>(16, DataSerializers.BYTE);
	///////////////////////////////////////////////////////////////////////////

	protected static final IAttribute healthAtt = SharedMonsterAttributes.MAX_HEALTH;
	protected static final IAttribute speedAtt = SharedMonsterAttributes.MOVEMENT_SPEED;
	protected static final IAttribute damageAtt = SharedMonsterAttributes.ATTACK_DAMAGE;

	protected EntityLivingBase owner = null;

	public EntityTameableFlying(World w) {
		super(w);
		dataManager.register(BOOL_WATCHER, Byte.valueOf((byte) 0));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(flyingSpeed);
		this.getAttributeMap().registerAttribute(flyingYaw);
		this.getAttributeMap().registerAttribute(flyingPitch);
	}

	@Override
	protected boolean canDespawn() {
		return !this.isTamed() && this.ticksExisted > 2400;
	}

	/**
	 * Retrieves the Flight Pitch Delta Speed
	 */
	public double getFlyPitch() {
		return this.getEntityAttribute(flyingPitch).getAttributeValue();
	}

	@Override
	public double getFlySpeed() {
		return this.getEntityAttribute(flyingSpeed).getAttributeValue();
	}

	/**
	 * Retrieves the Flight Yaw Delta Speed
	 */
	public double getFlyYaw() {
		return this.getEntityAttribute(flyingYaw).getAttributeValue();
	}

	/**
	 * Detects if there are air blocks below the entities lowest bounding box
	 * position based on range, centered on entity's x/z axis
	 */
	public boolean isAirBelow(int range) {
		for (int curBlock = 1; curBlock <= range; curBlock++)
			if (!this.world.isAirBlock(new BlockPos(MathHelper.floor(this.posX),
					MathHelper.floor(this.getEntityBoundingBox().minY) - curBlock, MathHelper.floor(this.posZ))))
				return false;
		return true;
	}

	/**
	 * Retrieves if this entity is inside a liquid material (provides a better
	 * cross-mod implementation then {@link Entity#isInsideOfMaterial(Material)}
	 * )
	 */
	private boolean isInLiquid() {
		return this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY),
				MathHelper.floor(this.posZ))).getMaterial().isLiquid();
	}

	@Override
	public boolean isFlyable() {
		return true;
	}

	@Override
	public boolean isFlying() {
		if (!this.isFlyable()) {
			this.setFlying(false);
			return false;
		}
		return (this.dataManager.get(BOOL_WATCHER) & 32) != 0;
	}

	@Override
	public boolean canTame(EntityLivingBase tamer, ItemStack item) {
		return item.getItem() == Items.FISH && HTTYMDMod.getConfig().isTameable(this);
	}

	@Override
	public void travel(float strafe, float up, float forward) {
		if (this.isFlying()) {
			if ((!this.isAirBelow(1) && this.motionY < -0.1) || this.isInLiquid()) {
				this.setFlying(false);
				this.travel(strafe, up, forward);
				return;
			}
			if (this.motionY < 0)
				this.motionY *= 0.8;
			if (forward < 0)
				forward *= 0.15;

			final float timeSpeedMultipler = 0.91F;

			this.motionX *= timeSpeedMultipler;
			this.motionY *= timeSpeedMultipler;
			this.motionZ *= timeSpeedMultipler;

			this.moveFlying(strafe, forward, (float) this.getFlySpeed());
			//super.travel(strafe, forward, (float) this.getFlySpeed());
			//this.moveEntity(this.motionX, this.motionY, this.motionZ);
			//System.out.println(motionX + " " + motionY + " " + motionZ);
			move(MoverType.SELF, motionX, motionY, motionZ);
		} else {
			if(!isSitting())super.travel(strafe, up, forward);
			else super.travel(0, 0, 0);
		}
	}

	public void moveFlying(float strafe, float forward, float flySpeed) {
		jumpMovementFactor = .5f;
		super.travel(strafe * flySpeed, (float) (forward * Math.tan(Math.toRadians(MathHelper.clamp(-rotationPitch*2, -45, 45))) * flySpeed + 0.133f), forward * flySpeed * 2);
	}

	@Override
	public void onTakeoff() {
		if (!this.isFlying() && this.onGround) {
			this.jump();
			this.motionY += this.getFlySpeed();
		}// else
		//this.motionY += this.getFlySpeed() * 0.25;
		this.setFlying(true);
	}

	@Override
	public void setFlying(boolean flying) {
		byte b0 = this.dataManager.get(BOOL_WATCHER);

		if (flying && this.isFlyable())
			this.dataManager.set(BOOL_WATCHER, Byte.valueOf((byte) (b0 | 32)));
		else
			this.dataManager.set(BOOL_WATCHER, Byte.valueOf((byte) (b0 & -33)));
	}

	/*@Override
	public EntityLivingBase getOwner() {
		EntityLivingBase result = this.owner != null ? this.owner : super.getOwner();
		if (result == null) {
			Iterator<?> it = this.world.loadedEntityList.iterator();
			while (it.hasNext()) {
				Entity e = (Entity) it.next();
				if (e == null || !(e instanceof EntityLivingBase))
					continue;
				if (e.getUniqueID().toString().equals(this.getOwnerString())) {
					result = (EntityLivingBase) e;
					break;
				}
			}
		}
		this.owner = result;
		if (this.owner != null)
			this.setTamed(true);
		return result;
	}*/

	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
		if (this.isFlying()) {
			this.fallDistance = 0;
		} else if (this.fallDistance > 3.2F) {
			this.setFlying(true);
			this.updateFallState(y, onGroundIn, state, pos);
		}
		super.updateFallState(y, onGroundIn, state, pos);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setBoolean(NBT_IS_FLYING, this.isFlying());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		this.setFlying(tag.getBoolean(NBT_IS_FLYING));
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand h) {
		ItemStack hand = player.getHeldItem(h);

		if (this.isOwner(player) && player.isSneaking()){
			setSitting(!isSitting());
			return true;
		}

		if (hand == null) {
			if (this.interactEmpty(player))
				return true;
		} else if (HTTYMDMod.getConfig().isDebugMode() && hand.getItem() == ItemRegistry.wing) {
			this.onTakeoff();
			return true;
		} else if (!this.isTamed() && this.canTame(player, hand)) {
			if (!player.capabilities.isCreativeMode) {
				player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
			}

			if (!this.world.isRemote) {
				if (this.rand.nextInt(3) == 0) {
					this.setTamed(true);
					//this.setPathToEntity((PathEntity) null);
					this.setAttackTarget((EntityLivingBase) null);
					this.setHealth(20.0F);
					this.setOwnerId(player.getUniqueID());
					this.playTameEffect(true);
					this.world.setEntityState(this, (byte) 7);
				} else {
					this.playTameEffect(false);
					this.world.setEntityState(this, (byte) 6);
				}
			}
			return true;
		}
		return super.processInteract(player, h);
	}

	/**
	 * Actions taken with an empty hand, halts interaction this tick if true
	 */
	protected boolean interactEmpty(EntityPlayer player) {
		return false;
	}

	@Override
	public boolean isBreedingItem(ItemStack p_70877_1_) {
		return false;
	}

	/**
	 * X Offset of mounted entity
	 *
	 * <p>Useful for odd positioning of rider location (in comparison to regular mobs)</p>
	 *
	 * @see #getMountedYOffset()
	 * @see #getMountedZOffset()
	 */
	public double getMountedXOffset() {
		return 0;
	}

	/**
	 * Z Offset of mounted entity
	 *
	 * <p>Useful for odd positioning of rider location (in comparison to regular mobs)</p>
	 *
	 * @see #getMountedYOffset()
	 * @see #getMountedXOffset()
	 */
	public double getMountedZOffset() {
		return 0;
	}

	@Override
	public void updateRidden() {
		super.updateRidden();
		if (this.getRidingEntity() != null)
		{
			this.getRidingEntity().setPosition(this.posX + this.getMountedXOffset(), this.posY + this.getMountedYOffset() + this.getRidingEntity().getYOffset(), this.posZ + this.getMountedZOffset());
		}
	}

	public void doJump() {
		if(!this.isAirBelow(1)) {
			this.jump();
			move(MoverType.SELF, 0, motionY, 0);
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable mate) {
		if (!this.getClass().equals(mate.getClass()))
			return null;
		try {
			return this.getClass().getConstructor(World.class).newInstance(this.world);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * For vehicles, the first passenger is generally considered the controller and "drives" the vehicle. For example,
	 * Pigs, Horses, and Boats are generally "steered" by the controlling passenger.
	 */
	@Override
	@Nullable
	public Entity getControllingPassenger() {
		return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
	}

	/**
	 * returns true if all the conditions for steering the entity are met. For pigs, this is true if it is being ridden
	 * by a player and the player is holding a carrot-on-a-stick
	 */
	@Override
	public boolean canBeSteered()
	{
		Entity entity = this.getControllingPassenger();
		return entity instanceof EntityLivingBase;
	}
}
