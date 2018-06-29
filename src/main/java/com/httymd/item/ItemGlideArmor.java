package com.httymd.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import net.minecraftforge.common.ISpecialArmor;

import com.httymd.client.model.ModelGlideSuit;
import com.httymd.item.util.ItemUtils.EnumArmorType;

public class ItemGlideArmor extends ItemArmorExtension implements ISpecialArmor {

	public static final String NBT_GLIDING = "IsGliding";

	public ItemGlideArmor(String name, ArmorMaterial mat, EntityEquipmentSlot type) {
		super(name, mat, type);
	}

	protected EnumArmorType[] getRequiredSlotsForFlight() {
		return new EnumArmorType[] { EnumArmorType.CHESTPLATE, EnumArmorType.LEGGINGS };
	}

	private boolean isInLiquid(EntityLivingBase entity) {
		return entity.world.getBlockState(new BlockPos(MathHelper.floor(entity.posX), MathHelper.floor(entity.posY),
				MathHelper.floor(entity.posZ))).getMaterial().isLiquid();
	}

	public boolean isFlyable(EntityLivingBase entity) {
		boolean flag = entity != null && (!entity.onGround && !this.isInLiquid(entity));

		if (entity instanceof EntityPlayer)
			flag = flag && !((EntityPlayer) entity).capabilities.isFlying;

		for (EnumArmorType slot : this.getRequiredSlotsForFlight()) {
			ItemStack armor = entity.getItemStackFromSlot(slot.slot);
			flag = flag && (armor != null && armor.getItem() instanceof ItemGlideArmor);
		}
		return flag;
	}

	public boolean canGlide(EntityLivingBase entity, ItemStack stack) {
		boolean canGlide = this.isFlyable(entity) && (this.isGliding(stack)
				|| (entity.motionY < -1.0 && entity.moveForward >= 0.1 && entity.isSneaking()));
		setGliding(stack, canGlide);
		if (canGlide) {
			for (int i = 1; i < 4; i++) {
				ItemStack armor = entity.getItemStackFromSlot(EnumArmorType.VALUES[i].slot);
				if (armor != null && armor.getItem() instanceof ItemGlideArmor)
					((ItemGlideArmor) armor.getItem()).setGliding(armor, true);
			}
		}
		return canGlide;
	}

	public boolean isGliding(ItemStack stack) {
		if (stack == null)
			return false;
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			return this.isGliding(stack);
		} else
			return stack.getTagCompound().getBoolean(NBT_GLIDING);
	}

	public void setGliding(ItemStack stack, boolean gliding) {
		if (!(stack.getItem() instanceof ItemGlideArmor))
			return;
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setBoolean(NBT_GLIDING, gliding);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		this.onArmorTick(world, (EntityLivingBase) player, stack);
	}

	public void onArmorTick(World world, EntityLivingBase entity, ItemStack stack) {
		/*NBTTagCompound tag = new NBTTagCompound();
		entity.writeEntityToNBT(tag);
		System.out.println(tag);*/
		/*Collection<AttributeModifier> c = entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE).getModifiers();
		System.out.println(c);*/
		boolean canGlide = this.canGlide(entity, stack);
		if (canGlide) {
			float m = 1;
			if(entity.moveForward > 0 && entity.isSprinting())m = 1.1f;
			entity.motionX = -(Math.sin(Math.toRadians(entity.getRotationYawHead())) * ((2 + entity.moveForward) / 3 * m));
			entity.motionZ = (Math.cos(Math.toRadians(entity.getRotationYawHead())) * ((2 + entity.moveForward) / 3 * m));
			float tr = 0.2f;
			if(entity.moveForward > 0){
				if(entity.isSprinting())
					tr = 0.5f;
				else
					tr = 0.3f;
			}
			if (entity.motionY < -tr) {
				entity.motionY /= 2;
			}
			if (entity instanceof EntityPlayer)
				((EntityPlayer) entity).resetActiveHand();
		} else {
			if (entity instanceof EntityPlayer)
				super.onArmorTick(world, (EntityPlayer) entity, stack);
		}
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack stack, EntityPlayer player) {
		setGliding(stack, false);
		return super.onDroppedByPlayer(stack, player);
	}

	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		if (isGliding(itemStack)) {
			if (armorSlot == EntityEquipmentSlot.LEGS) {
				return new ModelGlideSuit(0.5F);
			} else {
				return new ModelGlideSuit(1.0F);
			}
		}
		return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
	}
	@Override
	public ArmorProperties getProperties(EntityLivingBase entity, ItemStack armor, DamageSource source, double damage,
			int slot) {
		if (source == DamageSource.FALL && this.isGliding(armor)) {
			return new ArmorProperties(1, 1, 200);
		}
		return new ArmorProperties(0, 0, 0);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return 0;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack armor, DamageSource source, int damage, int slot) {
		if (source == DamageSource.FALL && this.isGliding(armor))
			return;
		armor.damageItem(damage, entity);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		/*if (this.isGliding(stack) && slot == EntityEquipmentSlot.LEGS) {
			return HTTYMDMod.ID + ":textures/armor/"
					+ getRegistryName().getResourcePath().substring(0, getRegistryName().getResourcePath().lastIndexOf("_")) + "_fins.png";
		}*/
		return super.getArmorTexture(stack, entity, slot, type);
	}
}