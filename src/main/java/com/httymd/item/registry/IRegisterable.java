package com.httymd.item.registry;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import com.httymd.item.util.ItemUtils;

/**
 * A registry consistent interface
 *
 * @author George Albany
 *
 */
public interface IRegisterable {

	/**
	 * Retrieves Registry Name for Item (commonly using {@link ItemUtils#findRegistryName(String)}
	 */
	public ResourceLocation getRegistryName();

	/**
	 * Registers the item (commonly using {@link ItemRegistry#registerItem(Item, String)}
	 */
	public Item registerItem();

}
