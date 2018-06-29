package com.httymd.item;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.httymd.item.registry.IRegisterable;
import com.httymd.item.registry.ItemRegistry;
import com.httymd.item.util.EnumToolType;
import com.httymd.item.util.ItemUtils;

/**
 * Reproduces All vanilla tool behaviors (including a hoe, even if it isn't
 * technically a tool) without extending original tools. To detect type of tool
 * item, please use {@link #isToolType(EnumToolType)} instead of instanceof
 *
 * @author George Albany
 *
 */
public class ItemToolExtension extends ItemTool implements IRegisterable, IFuelHandler {

	protected final Collection<EnumToolType> toolTypes;

	public ItemToolExtension(Item.ToolMaterial material, EnumToolType type) {
		this(type.getName(), material, Collections.singleton(type));
	}

	public ItemToolExtension(String prefix, Item.ToolMaterial material, Collection<EnumToolType> types) {
		super(EnumToolType.getResultDamageOf(types), 1, material, EnumToolType.getAllEffectiveBlocksOf(types));
		this.toolTypes = types;
		this.setUnlocalizedName(ItemUtils.findUnlocName(prefix + "_" + material.toString()));
		//this.setTextureName(ItemUtils.findTextureName(this.getUnlocalizedName()));
		if(EnumToolType.getAverageFuelTime(this.toolTypes) > 0) GameRegistry.registerFuelHandler(this);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState hitBlock) {
		if (this.isToolType(EnumToolType.PICKAXE))
			return hitBlock.getMaterial() != Material.IRON && hitBlock.getMaterial() != Material.ANVIL
			&& hitBlock.getMaterial() != Material.ROCK ? super.getDestroySpeed(stack, hitBlock)
					: this.efficiency;
			if (this.isToolType(EnumToolType.AXE))
				return hitBlock.getMaterial() != Material.WOOD && hitBlock.getMaterial() != Material.PLANTS
				&& hitBlock.getMaterial() != Material.VINE ? super.getDestroySpeed(stack, hitBlock)
						: this.efficiency;
				return super.getDestroySpeed(stack, hitBlock);
	}

	@Override
	public boolean canHarvestBlock(IBlockState state) {
		Block hitBlock = state.getBlock();
		if (this.isToolType(EnumToolType.PICKAXE))
			return hitBlock == Blocks.OBSIDIAN ? this.toolMaterial.getHarvestLevel() == 3
			: hitBlock != Blocks.DIAMOND_BLOCK && hitBlock != Blocks.DIAMOND_ORE
			? hitBlock != Blocks.EMERALD_ORE && hitBlock != Blocks.EMERALD_BLOCK
			? hitBlock != Blocks.GOLD_BLOCK && hitBlock != Blocks.GOLD_ORE
			? hitBlock != Blocks.IRON_BLOCK && hitBlock != Blocks.IRON_ORE
			? hitBlock != Blocks.LAPIS_BLOCK && hitBlock != Blocks.LAPIS_ORE
			? hitBlock != Blocks.REDSTONE_ORE
			&& hitBlock != Blocks.LIT_REDSTONE_ORE
			? state.getMaterial() == Material.ROCK
			? true
					: state
					.getMaterial() == Material.IRON
					? true
							: state.getMaterial() == Material.ANVIL
							: this.toolMaterial.getHarvestLevel() >= 2
							: this.toolMaterial.getHarvestLevel() >= 1
							: this.toolMaterial.getHarvestLevel() >= 1
							: this.toolMaterial.getHarvestLevel() >= 2
							: this.toolMaterial.getHarvestLevel() >= 2
							: this.toolMaterial.getHarvestLevel() >= 2;
							if (this.isToolType(EnumToolType.SHOVEL))
								return hitBlock == Blocks.SNOW_LAYER ? true : hitBlock == Blocks.SNOW;
							return super.canHarvestBlock(state);
	}

	@Override
	public Set<String> getToolClasses(ItemStack stack) {
		return this.getToolTypes() != null ? EnumToolType.getAllNames(this.getToolTypes()) : super.getToolClasses(stack);
	}

	public Collection<EnumToolType> getToolTypes() {
		return this.toolTypes;
	}

	/**
	 * Determines whether this tool contains the same {@link EnumToolType} as type
	 */
	public boolean isToolType(EnumToolType type) {
		return this.getToolTypes().contains(type);
	}
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (!this.isToolType(EnumToolType.HOE))
			return super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);

		if (!player.canPlayerEdit(pos, facing, stack))
			return EnumActionResult.FAIL;
		else {
			UseHoeEvent event = new UseHoeEvent(player, stack, world, pos);
			if (MinecraftForge.EVENT_BUS.post(event))
				return EnumActionResult.FAIL;

			if (event.getResult() == Result.ALLOW) {
				stack.damageItem(1, player);
				return EnumActionResult.SUCCESS;
			}

			IBlockState block = world.getBlockState(pos);

			if (world.getBlockState(pos.up()).getMaterial() == Material.AIR
					&& (block == Blocks.GRASS || block == Blocks.DIRT)) {
				Block block1 = Blocks.FARMLAND;

				if (world.isRemote)
					return EnumActionResult.SUCCESS;
				else {
					world.setBlockState(pos, block1.getDefaultState());
					stack.damageItem(1, player);
					return EnumActionResult.SUCCESS;
				}
			} else
				return EnumActionResult.FAIL;
		}
	}

	@Override
	public Item registerItem() {
		ItemRegistry.registerItem(this, this.getUnlocalizedName());
		return this;
	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.getItem() == this) {
			return this.toolMaterial == ToolMaterial.WOOD ?
					EnumToolType.getAverageFuelTime(this.getToolTypes()) + 50
					: EnumToolType.getAverageFuelTime(this.getToolTypes());
		}
		return 0;
	}
}
