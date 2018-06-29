package com.httymd.block.registry;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import com.httymd.item.util.ItemUtils;

public interface IRegisterable {

	/**
	 * Retrieves Registry Name for Block (commonly using {@link ItemUtils#findRegistryName(String)}
	 */
	public ResourceLocation getRegistryName();

	/**
	 * Registers the block (commonly using {@link BlockRegistry#registerBlock(Block, String)}
	 */
	public Block registerBlock();

}
