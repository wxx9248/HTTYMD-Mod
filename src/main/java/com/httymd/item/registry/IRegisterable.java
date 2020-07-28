package com.httymd.item.registry;

import com.httymd.item.util.ItemUtils;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * A registry consistent interface
 *
 * @author George Albany
 */
public interface IRegisterable
{

    /**
     * Retrieves Registry Name for Item (commonly using {@link ItemUtils#findRegistryName(String)}
     */
    ResourceLocation getRegistryName();

    /**
     * Registers the item (commonly using {@link ItemRegistry#registerItem(Item, String)}
     */
    Item registerItem();

}
