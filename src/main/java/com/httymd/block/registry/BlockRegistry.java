package com.httymd.block.registry;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.item.ItemBlock;

import net.minecraftforge.fml.common.registry.ForgeRegistries;

import com.httymd.HTTYMDMod;
import com.httymd.block.BlockCompressedExtension;
import com.httymd.item.registry.ItemRegistry;

public class BlockRegistry {

	public static final HashMap<String, Block> blockRegistry = new HashMap<>();

	public static Block gronkleIronBlock;

	public static void init() {
		gronkleIronBlock = new BlockCompressedExtension("giron_block", MapColor.SILVER).registerBlock();
	}

	/**
	 * Registers block using a string name (for consistency with 1.8)
	 * @param block Block to register
	 * @param regName Name to register block (mustn't have a colon)
	 */
	public static void registerBlock(Block block, String regName) {
		if(regName.startsWith("tile."))regName = regName.substring(5);
		if(regName.startsWith(HTTYMDMod.ID))regName = regName.substring(HTTYMDMod.ID.length());
		if(regName.startsWith(":"))regName = regName.substring(1);
		block.setRegistryName(HTTYMDMod.ID, regName);
		ForgeRegistries.BLOCKS.register(block);
		blockRegistry.put(regName, block);
		ItemRegistry.registerItem(new ItemBlock(block).setUnlocalizedName(block.getUnlocalizedName()).setCreativeTab(block.getCreativeTabToDisplayOn()), regName);
	}
}
