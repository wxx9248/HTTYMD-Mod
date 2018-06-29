package com.httymd.item.registry;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.common.registry.ForgeRegistries;

import com.httymd.HTTYMDMod;
import com.httymd.item.ItemDagger;
import com.httymd.item.ItemExtension;
import com.httymd.item.ItemFlameSword;
import com.httymd.item.ItemGlideArmor;
import com.httymd.item.ItemShieldM;
import com.httymd.item.ItemSpawnEgg;
import com.httymd.item.ItemToolExtension;
import com.httymd.item.ItemWarhammer;
import com.httymd.item.ItemWeapon;
import com.httymd.item.ItemWeaponCrossbow;
import com.httymd.item.util.EnumFoodType;
import com.httymd.item.util.EnumToolType;
import com.httymd.item.util.EnumWeaponType;
import com.httymd.item.util.ItemUtils;

import com.google.common.collect.Multimap;

public class ItemRegistry {

	private final static String swordN = "sword";

	private final static float swordD = 4f;

	public static final HashMap<Integer, ItemSpawnEgg> spawnEggIDMapping = new HashMap<>();
	public static final HashMap<String, Item> itemRegistry = new HashMap<>();

	public static Item daggerWood;
	public static Item clubWood;
	public static Item maceWood;
	public static Item hammerWood;
	public static Item waraxeWood;
	public static Item shieldWood;

	public static Item daggerStone;
	public static Item clubStone;
	public static Item maceStone;
	public static Item hammerStone;
	public static Item waraxeStone;
	public static Item shieldStone;

	public static Item daggerIron;
	public static Item clubIron;
	public static Item maceIron;
	public static Item hammerIron;
	public static Item waraxeIron;
	public static Item shieldIron;

	public static Item daggerGold;
	public static Item clubGold;
	public static Item maceGold;
	public static Item hammerGold;
	public static Item waraxeGold;
	public static Item shieldGold;

	public static Item daggerDiam;
	public static Item clubDiam;
	public static Item maceDiam;
	public static Item hammerDiam;
	public static Item waraxeDiam;
	public static Item shieldDiam;

	public static Item daggerGron;
	public static Item clubGron;
	public static Item maceGron;
	public static Item hammerGron;
	public static Item waraxeGron;
	public static Item shieldGron;

	public static Item swordGron;
	public static Item shovelGron;
	public static Item axeGron;
	public static Item pickaxeGron;
	public static Item hoeGron;

	public static Item crossbow;
	public static Item[] glideSuit;
	public static Multimap<EnumFoodType, Item> foods;
	public static Item gronkleIronIngot;
	public static Item wing;
	public static Item flameSword;

	//public static ItemContainer zippleGasContainer;
	//public static ItemContainer nightmareSalivaContainer;

	public static void init() {

		daggerWood = new ItemDagger(ToolMaterial.WOOD).registerItem();
		clubWood = new ItemWeapon(ToolMaterial.WOOD, EnumWeaponType.CLUB).registerItem();
		maceWood = new ItemWeapon(ToolMaterial.WOOD, EnumWeaponType.MACE).registerItem();
		hammerWood = new ItemWarhammer(ToolMaterial.WOOD).registerItem();
		waraxeWood = new ItemWeapon(ToolMaterial.WOOD, EnumWeaponType.WARAXE).registerItem();

		daggerStone = new ItemDagger(ToolMaterial.STONE).registerItem();
		clubStone = new ItemWeapon(ToolMaterial.STONE, EnumWeaponType.CLUB).registerItem();
		maceStone = new ItemWeapon(ToolMaterial.STONE, EnumWeaponType.MACE).registerItem();
		hammerStone = new ItemWarhammer(ToolMaterial.STONE).registerItem();
		waraxeStone = new ItemWeapon(ToolMaterial.STONE, EnumWeaponType.WARAXE).registerItem();

		daggerIron = new ItemDagger(ToolMaterial.IRON).registerItem();
		clubIron = new ItemWeapon(ToolMaterial.IRON, EnumWeaponType.CLUB).registerItem();
		maceIron = new ItemWeapon(ToolMaterial.IRON, EnumWeaponType.MACE).registerItem();
		hammerIron = new ItemWarhammer(ToolMaterial.IRON).registerItem();
		waraxeIron = new ItemWeapon(ToolMaterial.IRON, EnumWeaponType.WARAXE).registerItem();

		daggerGold = new ItemDagger(ToolMaterial.GOLD).registerItem();
		clubGold = new ItemWeapon(ToolMaterial.GOLD, EnumWeaponType.CLUB).registerItem();
		maceGold = new ItemWeapon(ToolMaterial.GOLD, EnumWeaponType.MACE).registerItem();
		hammerGold = new ItemWarhammer(ToolMaterial.GOLD).registerItem();
		waraxeGold = new ItemWeapon(ToolMaterial.GOLD, EnumWeaponType.WARAXE).registerItem();

		daggerDiam = new ItemDagger(ToolMaterial.DIAMOND).registerItem();
		clubDiam = new ItemWeapon(ToolMaterial.DIAMOND, EnumWeaponType.CLUB).registerItem();
		maceDiam = new ItemWeapon(ToolMaterial.DIAMOND, EnumWeaponType.MACE).registerItem();
		hammerDiam = new ItemWarhammer(ToolMaterial.DIAMOND).registerItem();
		waraxeDiam = new ItemWeapon(ToolMaterial.DIAMOND, EnumWeaponType.WARAXE).registerItem();

		daggerGron = new ItemDagger(MaterialRegistry.toolGronkle).registerItem();
		clubGron = new ItemWeapon(MaterialRegistry.toolGronkle, EnumWeaponType.CLUB).registerItem();
		maceGron = new ItemWeapon(MaterialRegistry.toolGronkle, EnumWeaponType.MACE).registerItem();
		hammerGron = new ItemWarhammer(MaterialRegistry.toolGronkle).registerItem();
		waraxeGron = new ItemWeapon(MaterialRegistry.toolGronkle, EnumWeaponType.WARAXE).registerItem();

		swordGron = new ItemWeapon(MaterialRegistry.toolGronkle, swordN, swordD).registerItem();
		shovelGron = new ItemToolExtension(MaterialRegistry.toolGronkle, EnumToolType.SHOVEL).registerItem();
		pickaxeGron = new ItemToolExtension(MaterialRegistry.toolGronkle, EnumToolType.PICKAXE).registerItem();
		axeGron = new ItemToolExtension(MaterialRegistry.toolGronkle, EnumToolType.AXE).registerItem();
		hoeGron = new ItemToolExtension(MaterialRegistry.toolGronkle, EnumToolType.HOE).registerItem();

		crossbow = new ItemWeaponCrossbow("crossbow", 2).registerItem();

		glideSuit = ItemUtils.generateArmor(ItemGlideArmor.class, "gsuit", ArmorMaterial.IRON);

		gronkleIronIngot = new ItemExtension("giron_ingot").registerItem();
		wing = new ItemExtension("wing_item").registerItem();
		flameSword = new ItemFlameSword(ToolMaterial.IRON, "flame_sword", swordD).registerItem();

		shieldWood = new ItemShieldM(ToolMaterial.WOOD).registerItem();
		shieldStone = new ItemShieldM(ToolMaterial.STONE).registerItem();
		shieldIron = new ItemShieldM(ToolMaterial.IRON).registerItem();
		shieldGold = new ItemShieldM(ToolMaterial.GOLD).registerItem();
		shieldDiam = new ItemShieldM(ToolMaterial.DIAMOND).registerItem();
		shieldGron = new ItemShieldM(MaterialRegistry.toolGronkle).registerItem();

		// zippleGasContainer = new ItemContainer("zipple_gas_cont", 10.0F,
		// EntityZippleback.class);
		// nightmareSalivaContainer = new ItemContainer("night_saliva_cont",
		// 20.0F, EntityNightmare.class);

		foods = EnumFoodType.generateFood();

		ItemUtils.addFish(new ItemStack(foods.get(EnumFoodType.CRAB).iterator().next(), 1), 50);
	}

	/**
	 * Registers item using a string name (for consistency with 1.8)
	 * @param item Item to register
	 * @param regName Name to register item (mustn't have a colon)
	 */
	public static void registerItem(Item item, String regName) {
		//GameRegistry.registerItem(item, regName);
		if(regName.startsWith("item."))regName = regName.substring(5);
		if(regName.startsWith(HTTYMDMod.ID))regName = regName.substring(HTTYMDMod.ID.length());
		if(regName.startsWith(":"))regName = regName.substring(1);
		item.setRegistryName(HTTYMDMod.ID, regName);
		ForgeRegistries.ITEMS.register(item);
		itemRegistry.put(regName, item);
	}
}
