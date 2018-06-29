package com.httymd.item;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemShieldM extends ItemExtension {

	protected final float damDecay;
	protected final float natDecay;

	private Item.ToolMaterial material = null;

	public ItemShieldM(String name, float decay, float damDecay, int maxDam) {
		super("shield_" + name);
		this.setFull3D();
		this.setMaxDamage(maxDam);
		this.setMaxStackSize(1);
		this.setHasSubtypes(false);
		this.natDecay = decay;
		this.damDecay = damDecay;
		this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter()
		{
			@Override
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
			{
				return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
			}
		});
	}
	public ItemShieldM(String suffix, Item.ToolMaterial material) {
		this(material.toString().toLowerCase() + suffix, 1F / (material.getAttackDamage() + 1F) / 20F,
				1F / (material.getAttackDamage() + 19), material.getMaxUses());
		this.material = material;
	}
	public ItemShieldM(Item.ToolMaterial material) {
		this("", material);
	}
	@Override
	public int getItemBurnTime(ItemStack fuel) {
		if (fuel.getItem() == this) {
			return this.material.equals(ToolMaterial.WOOD) ? 300 : 200;
		}
		return 0;
	}
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BLOCK;
	}
	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}

	/**
	 * Called when the equipped item is right clicked.
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		playerIn.setActiveHand(handIn);
		return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
	}

	/**
	 * Return whether this item is repairable in an anvil.
	 *
	 * @param toRepair the {@code ItemStack} being repaired
	 * @param repair the {@code ItemStack} being used to perform the repair
	 */
	@Override
	public boolean getIsRepairable(ItemStack itemStack, ItemStack repair) {
		return this.material.getRepairItemStack().isItemEqual(repair);
	}
}
