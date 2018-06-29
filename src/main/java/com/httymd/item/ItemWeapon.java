package com.httymd.item;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.httymd.item.registry.IRegisterable;
import com.httymd.item.registry.ItemRegistry;
import com.httymd.item.util.EnumWeaponType;
import com.httymd.item.util.ItemUtils;
import com.httymd.util.CreativeTab;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * A generic ItemWeapon class for ease of weapon creation, and handles fuel for fuel items
 *
 * @author George Albany
 *
 */
public class ItemWeapon extends ItemSword implements IRegisterable, IFuelHandler {

	private static final HashMap<ToolMaterial, HashMap<EnumWeaponType, ItemWeapon>> weaponMap = new HashMap<>();

	protected float attackDamage;
	protected EnumWeaponType type = null;

	public ItemWeapon(Item.ToolMaterial mat, EnumWeaponType wepType) {
		this(mat, wepType.getName(), wepType.getDamage());
		this.type = wepType;
		if(this.type != null && this.type.getFuelTime() > 0) GameRegistry.registerFuelHandler(this);
		registerWeapon(mat, this.type, this);
	}

	public ItemWeapon(Item.ToolMaterial toolMaterial, String weaponName, float weaponDamage) {
		this(toolMaterial, weaponName, weaponDamage, CreativeTab.DRAGON_TAB);
	}

	public ItemWeapon(ToolMaterial toolMaterial, String weaponName, float weaponDam, CreativeTabs tab) {
		this(weaponName + "_" + toolMaterial.toString(), toolMaterial, weaponDam, tab);
	}

	public ItemWeapon(String name, ToolMaterial weaponMat, float weaponDam, CreativeTabs tab) {
		super(weaponMat);
		this.setCreativeTab(tab);
		this.attackDamage = weaponDam + weaponMat.getAttackDamage();
		this.setUnlocalizedName(ItemUtils.findUnlocName(name));
		//this.setTextureName(ItemUtils.findTextureName(this.getUnlocalizedName()));
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();
		if (slot == EntityEquipmentSlot.MAINHAND)
		{
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDamage, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
		}
		//1.8: field_111210_e to itemModifierUUID
		return multimap;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack item) {
		return 72000;
	}

	/**
	 * Retrieves the weapon's material's base entity attack damage (for 1.8 consistency)
	 */
	public float MaterialAttackDamage() {
		return this.getAttackDamage(); //getDamageVsEntity in 1.8
	}

	/**
	 * Retrieves the mining speed for the weapon (for 1.8 consistency)
	 */
	public float MineSpeed(ItemStack item, Block block) {
		return 1; //getStrVsBlock in 1.8
	}

	@Override
	public ItemSword registerItem() {
		ItemRegistry.registerItem(this, this.getUnlocalizedName());
		return this;
	}

	/**
	 * Whether item is effective against block
	 */
	/*public boolean func_150897_b(Block block) {
		return this.type == EnumWeaponType.WARAXE && block.getMaterial(state) == Material.WOOD;
	}*/


	/**
	 * Retrieves the amount of time able to spend on burning an item
	 */
	@Override
	public int getBurnTime(ItemStack fuel) {
		if (fuel.getItem() == this) {
			return this.getToolMaterialName().equals(ToolMaterial.WOOD.toString()) ?
					this.type.getFuelTime() + 100 : this.type.getFuelTime();
		}
		return 0;
	}

	public static HashMap<EnumWeaponType, ItemWeapon> getWeaponMap(ToolMaterial mat) {
		return weaponMap.get(mat);
	}

	public static ItemWeapon getWeaponFor(ToolMaterial mat, EnumWeaponType type) {
		return getWeaponMap(mat).get(type);
	}

	private static void registerWeapon(ToolMaterial mat, EnumWeaponType type, ItemWeapon wep) {
		if(mat != null && type != null) {
			if(!weaponMap.containsKey(mat)) {
				weaponMap.put(mat, new HashMap<EnumWeaponType, ItemWeapon>());
			}
			weaponMap.get(mat).put(type, wep);
		}
	}
}
