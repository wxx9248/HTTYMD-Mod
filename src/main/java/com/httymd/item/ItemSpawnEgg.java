package com.httymd.item;

import org.apache.logging.log4j.Logger;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.httymd.HTTYMDMod;
import com.httymd.item.registry.IRegisterable;
import com.httymd.item.registry.ItemRegistry;
import com.httymd.item.util.ItemUtils;

/**
 * Creates a custom spawn egg which well handles custom entities
 *
 * @author George Albany (using Jabelar's Spawn Egg class)
 *
 */
public class ItemSpawnEgg extends ItemMonsterPlacer implements IRegisterable {

	public static Logger L = HTTYMDMod.getLogger();

	protected int colorBase = 0x000000;
	protected int colorSpots = 0xFFFFFF;
	protected String entityToSpawnName = "";
	protected ResourceLocation entityToSpawnNameFull = new ResourceLocation("");
	protected EntityLiving entityToSpawn = null;

	public ItemSpawnEgg(String entityName) {
		super();
		this.setUnlocalizedName(ItemUtils.findUnlocName("spawn_egg_" + entityName));
	}

	public ItemSpawnEgg(String parEntityToSpawnName, int parPrimaryColor, int parSecondaryColor) {
		this(parEntityToSpawnName);
		this.setHasSubtypes(false);
		this.setEntityToSpawnName(parEntityToSpawnName);
		this.setColors(parPrimaryColor, parSecondaryColor);
	}

	public int getColorBase() {
		return this.colorBase;
	}

	public int getColorSpots() {
		return this.colorSpots;
	}

	private String getEntityLocalName(ItemStack stack) {
		if (stack.hasDisplayName())
			return stack.getDisplayName();

		return "";
	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs parTab, NonNullList<ItemStack> parList) {
		if(isInCreativeTab(parTab)){
			parList.add(new ItemStack(this, 1, 0));
		}
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (world.isRemote)
			return new ActionResult<>(EnumActionResult.SUCCESS, stack);
		else {
			RayTraceResult ray = this.rayTrace(world, player, true);

			if (ray == null)
				return new ActionResult<>(EnumActionResult.SUCCESS, stack);
			else {
				if (ray.typeOfHit == RayTraceResult.Type.BLOCK) {
					int i = ray.getBlockPos().getX();
					int j = ray.getBlockPos().getY();
					int k = ray.getBlockPos().getZ();

					if (!world.canMineBlockBody(player, ray.getBlockPos()))
						return new ActionResult<>(EnumActionResult.SUCCESS, stack);

					if (!player.canPlayerEdit(ray.getBlockPos(), ray.sideHit, stack))
						return new ActionResult<>(EnumActionResult.SUCCESS, stack);

					if (world.getBlockState(ray.getBlockPos()).getMaterial().isLiquid()) {
						Entity entity = this.spawnEntity(world, i, j, k);

						if (entity != null) {
							if (entity instanceof EntityLivingBase && stack.hasDisplayName())
								((EntityLiving) entity).setCustomNameTag(stack.getDisplayName());

							if (!player.capabilities.isCreativeMode)
								stack.shrink(1);
						}
					}
				}

				return new ActionResult<>(EnumActionResult.SUCCESS, stack);
			}
		}
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return EnumActionResult.SUCCESS;
		else {
			ItemStack stack = player.getHeldItem(hand);
			IBlockState block = world.getBlockState(pos);

			pos = pos.offset(facing);
			double d0 = 0.0D;

			if (facing == EnumFacing.UP && block.getBlock().getRenderType(block).ordinal() == 11)
				d0 = 0.5D;

			Entity entity = this.spawnEntity(world, pos.getX() + 0.5D, pos.getY() + d0, pos.getZ() + 0.5D);

			if (entity != null) {
				if (entity instanceof EntityLivingBase && stack.hasDisplayName())
					((EntityLiving) entity).setCustomNameTag(stack.getDisplayName());

				if (!player.capabilities.isCreativeMode)
					stack.shrink(1);
			}

			return EnumActionResult.SUCCESS;
		}
	}


	@Override
	public Item registerItem() {
		ItemRegistry.registerItem(this, this.getUnlocalizedName());
		return this;
	}

	public void setColors(int parColorBase, int parColorSpots) {
		this.colorBase = parColorBase;
		this.colorSpots = parColorSpots;
	}

	public void setEntityToSpawnName(String parEntityToSpawnName) {
		this.entityToSpawnName = parEntityToSpawnName;
		this.entityToSpawnNameFull = new ResourceLocation(HTTYMDMod.ID, this.entityToSpawnName);
	}

	/**
	 * Spawns the creature specified by the egg's type in the location specified
	 * by the last three parameters. Parameters: world, entityID, x, y, z.
	 */
	public Entity spawnEntity(World parWorld, double parX, double parY, double parZ) {

		if (!parWorld.isRemote) // never spawn entity on client side
		{
			this.entityToSpawnNameFull = new ResourceLocation(HTTYMDMod.ID, this.entityToSpawnName);
			if (EntityList.isRegistered(this.entityToSpawnNameFull)) {
				this.entityToSpawn = (EntityLiving) EntityList.createEntityByIDFromName(this.entityToSpawnNameFull, parWorld);
				if (this.entityToSpawn == null) {
					L.error("Entity exists but can't be created " + this.entityToSpawnNameFull);
					return null;
				}
				this.entityToSpawn.setLocationAndAngles(parX, parY, parZ,
						MathHelper.wrapDegrees(parWorld.rand.nextFloat() * 360.0F), 0.0F);
				parWorld.spawnEntity(this.entityToSpawn);
				//this.entityToSpawn.onSpawnWithEgg((IEntityLivingData) null);
				this.entityToSpawn.playLivingSound();
			} else
				// DEBUG
				L.warn("Entity not found " + this.entityToSpawnName);
		}

		return this.entityToSpawn;
	}

}
