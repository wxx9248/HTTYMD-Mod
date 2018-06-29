package com.httymd.item.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

import com.httymd.item.util.ItemUtils;

public class MaterialRegistry {

	public static Item.ToolMaterial toolGronkle;

	public static ItemArmor.ArmorMaterial armorGronkle;

	public static void init() {
		toolGronkle = ItemUtils.addToolMaterial("GRONKLE", 3, 560, 7.0F, 2.5F, 16, ItemRegistry.gronkleIronIngot);

		armorGronkle = ItemUtils.addArmorMaterial("GRONKLE", 25, new int[] { 3, 7, 5, 3 }, 10,
				ItemRegistry.gronkleIronIngot);
	}
}
