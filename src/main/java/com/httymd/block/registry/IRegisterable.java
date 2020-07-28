package com.httymd.block.registry;

import com.httymd.item.util.ItemUtils;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public interface IRegisterable
{

    /**
     * Retrieves Registry Name for Block (commonly using {@link ItemUtils#findRegistryName(String)}
     */
    ResourceLocation getRegistryName();

    /**
     * Registers the block (commonly using {@link BlockRegistry#registerBlock(Block, String)}
     */
    Block registerBlock();

}
