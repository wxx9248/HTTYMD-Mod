package com.httymd.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityArrow.PickupStatus;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import com.httymd.util.CreativeTab;
import com.httymd.util.Utils;

/**
 * A crossbow item which is able to hold a charge a later fire (aka: charge and forget)
 *
 * @author George Albany
 *
 */
public class ItemWeaponCrossbow extends ItemExtension {

	public static final String NBT_POWER = "BowStoredPower";

	public static final float RESET_POWER = 0.0F;
	public static final float MAX_POWER = 1.7F;

	public ItemWeaponCrossbow(String name, int numberOfActions) {
		this(name, numberOfActions, CreativeTab.DRAGON_TAB);
	}

	public ItemWeaponCrossbow(String name, int numberOfActions, CreativeTabs tab) {
		super(name, tab);
		this.maxStackSize = 1;
		this.setMaxDamage(384);
		this.setFull3D();
		addPropertyOverride(new ResourceLocation("httymd:bowstate"), new IItemPropertyGetter() {

			@Override
			public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
				if(isBowDrawn(stack))return 1;
				if(entityIn == null)return 0;
				ItemStack using = entityIn.getActiveItemStack();
				if(using != stack)return 0;
				int deltaDuration = entityIn.getItemInUseCount();
				int max = getMaxItemUseDuration(stack);
				return deltaDuration / (float)max;
			}
		});
	}

	public float getBowPower(ItemStack stack) {
		if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey(NBT_POWER)) {
			this.resetBow(stack);
			return RESET_POWER; // quicker, might as well optimize anyway
		}
		return stack.getTagCompound().getFloat(NBT_POWER);
	}


	@Override
	public int getItemEnchantability() {
		return 1;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack item) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack item) {
		return 72000;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if(isInCreativeTab(tab))
			for (int i = 0; i < 1; i++)
				list.add(new ItemStack(this, 1));
	}

	private void insertToInventory(EntityLivingBase entity, ItemStack itemStack) {
		Utils.insertItem(entity, itemStack);
	}

	public boolean isBowDrawn(ItemStack stack) {
		return this.getBowPower(stack) > RESET_POWER;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		return this.onStartUsing(player.getHeldItem(handIn), world, player, handIn);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		this.onStopUsing(stack, worldIn, entityLiving, timeLeft);
	}

	public ActionResult<ItemStack> onStartUsing(ItemStack stack, World world, EntityLivingBase entity, EnumHand hand) {
		boolean isCreative = entity instanceof EntityPlayer ? ((EntityPlayer) entity).capabilities.isCreativeMode : false;

		if (this.isBowDrawn(stack)) {
			if (entity.isSneaking()) {
				this.insertToInventory(entity, new ItemStack(Items.ARROW, 1));
				this.resetBow(stack);
				return new ActionResult<>(EnumActionResult.SUCCESS, stack);
			}

			float arrowPower = this.getBowPower(stack);
			EntityArrow entityarrow = new EntityTippedArrow(world, entity);
			entityarrow.shoot(entity, entity.rotationPitch, entity.rotationYaw, -1, arrowPower * 2, 0.1f);
			if (arrowPower > 1)
				entityarrow.setIsCritical(true);

			int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.REGISTRY.getObject(new ResourceLocation("power")), stack);
			if (powerLevel > 0)
				entityarrow.setDamage(entityarrow.getDamage() + powerLevel * 0.5D + 0.5D);

			int knockbackLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.REGISTRY.getObject(new ResourceLocation("punch")), stack);
			if (knockbackLevel > 0)
				entityarrow.setKnockbackStrength(knockbackLevel);

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.REGISTRY.getObject(new ResourceLocation("flame")), stack) > 0)
				entityarrow.setFire(100);

			stack.damageItem(1, entity);
			world.playSound((EntityPlayer)null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);

			if (isCreative)
				entityarrow.pickupStatus = PickupStatus.CREATIVE_ONLY;

			if (!world.isRemote)
				world.spawnEntity(entityarrow);

			this.resetBow(stack);
		} else {

			ArrowNockEvent event = new ArrowNockEvent((EntityPlayer) entity, stack, hand, world, true);
			MinecraftForge.EVENT_BUS.post(event);
			if (event.isCanceled())
				return event.getAction();

			if (entity instanceof EntityPlayer)
				//((EntityPlayer) entity).setItemInUse(stack, this.getMaxItemUseDuration(stack));
				entity.setActiveHand(hand);

		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	public void onStopUsing(ItemStack item, World world, EntityLivingBase entity, int dur) {
		boolean bowDrawn = this.isBowDrawn(item);

		if (!bowDrawn) {
			double durationDelta = this.getMaxItemUseDuration(item) - dur;

			ArrowLooseEvent event = new ArrowLooseEvent((EntityPlayer) entity, item, world, (int) durationDelta, true);
			MinecraftForge.EVENT_BUS.post(event);
			if (event.isCanceled())
				return;

			durationDelta = event.getCharge();
			if (this.pullInventory(entity, item, Items.ARROW)) {
				float arrowPower = (float) (durationDelta * 0.05F);
				arrowPower = (arrowPower * arrowPower + arrowPower * 2) / 3 * 2;

				if (arrowPower < 0.1D) {
					this.insertToInventory(entity, new ItemStack(Items.ARROW));
					return;
				}
				if (arrowPower > MAX_POWER) arrowPower = MAX_POWER;
				this.setBowPower(item, arrowPower);
				//world.playSound(entity, "mob.sheep.shear", 2.0F, 0.8F);// Best loading sound
				world.playSound((EntityPlayer)null, entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 2, 0.8f);
			}
		}
	}

	private boolean pullInventory(EntityLivingBase entity, ItemStack itemStack, Item item) {
		if (entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isCreativeMode
				|| EnchantmentHelper.getEnchantmentLevel(Enchantment.REGISTRY.getObject(new ResourceLocation("infinity")), itemStack) > 0)
			return true;

		return Utils.consumeItem(entity, item);
	}

	public void resetBow(ItemStack stack) {
		this.setBowPower(stack, RESET_POWER);
	}

	public void setBowPower(ItemStack stack, float power) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setFloat(NBT_POWER, power);
	}
}
