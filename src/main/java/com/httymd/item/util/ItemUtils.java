package com.httymd.item.util;

import com.google.common.collect.Sets;
import com.httymd.HTTYMDMod;
import com.httymd.item.ItemArmorExtension;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import java.util.Collections;
import java.util.Set;

public class ItemUtils
{
    private static boolean supportsFishHooking = true;

    /**
     * Creates and retrieves a new {@link ItemArmor.ArmorMaterial}
     *
     * @param craftingMaterial The material that can be used to repair armors that use this enum
     */
    public static ItemArmor.ArmorMaterial addArmorMaterial(String name, int durability, int[] reductionAmounts,
                                                           int enchantability, Item craftingMaterial)
    {
        ItemArmor.ArmorMaterial mat = EnumHelper
                .addArmorMaterial(name, name, durability, reductionAmounts, enchantability,
                                  SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1);
        mat.setRepairItem(new ItemStack(craftingMaterial));
        //mat.customCraftingMaterial = craftingMaterial;
        return mat;
    }

    /**
     * Adds a fishable itemstack through reflection to guarantee compatibility with previous versions of Forge that
     * don't support {@link net.minecraftforge.common.FishingHooks FishingHooks}
     *
     * @return whether adding fishing hooks is possible in current version
     */
    public static boolean addFish(ItemStack stack, int occurence)
    {
        if (!supportsFishHooking)
            return false;
        try
        {
			/*Class.forName("net.minecraftforge.common.FishingHooks").getMethod("addFish", WeightedRandomFishable
			.class)
			.invoke(null, new WeightedRandomFishable(stack, occurence));*/
            return true;
        }
        catch (Exception e)
        {
            HTTYMDMod.getLogger()
                     .warn("Probable Unsupported Forge Version, please update for all features\n\nExeception:\n" + e);
            supportsFishHooking = false;
            return false;
        }
    }

    /**
     * Creates and retrieves a new {@link Item.ToolMaterial}
     *
     * @param craftingMaterial The material that can be used to repair tools that use this enum
     */
    public static Item.ToolMaterial addToolMaterial(String name, int harvestLevel, int maxUses, float efficiency,
                                                    float damage, int enchantability, Item craftingMaterial)
    {
        Item.ToolMaterial mat = EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, damage,
                                                           enchantability);
        mat.setRepairItem(new ItemStack(craftingMaterial));
        //mat.customCraftingMaterial = craftingMaterial;
        return mat;
    }

    /**
     * Retrieves registry name from unlocalized name
     *
     * <p>Terminates modid section</p>
     */
    public static String findRegistryName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.lastIndexOf(':') + 1);
    }

    /**
     * Retrieves texture name from unlocalized name
     *
     * <p>Will get rid of unlocazied prefix (for items, its 'item.') and retrieves texture name</p>
     */
    public static String findTextureName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
    }

    /**
     * Creates unlocalized name for standardization
     *
     * <p>Syntax: "modid:&lt;lowercase name&gt;"</p>
     */
    public static String findUnlocName(String name)
    {
        return HTTYMDMod.ID + ":" + name.toLowerCase();
    }

    /**
     * Creates an array of armor from a class extending from ItemArmorExtension, uses reflection. (Reflection maybe be
     * disgusting and slow, but this provides efficiency, and is only ever called once on startup, and is easier to
     * manage)
     *
     * @param baseName The standardized prefix name to apply to the whole object
     * @param mat      The {@link ArmorMaterial} of the new armor
     */
    public static Item[] generateArmor(Class<? extends ItemArmorExtension> clazz, String baseName, ArmorMaterial mat)
    {
        final String[]       ARMORNAMES = { "helmet", "chestplate", "leggings", "boots" };
        ItemArmorExtension[] armor      = new ItemArmorExtension[ARMORNAMES.length];
        for (int i = 0; i < ARMORNAMES.length; i++)
            try
            {
                armor[i] = clazz.getConstructor(String.class, ArmorMaterial.class, EntityEquipmentSlot.class)
                                .newInstance(baseName + "_" + ARMORNAMES[i], mat, EnumArmorType.VALUES[i].slot);
                armor[i] = armor[i].registerItem();
            }
            catch (Exception ex)
            {
                HTTYMDMod.getLogger().fatal(ex);
            }
        return armor;
    }

    /**
     * Retrieves effective blocks for each EnumToolType, hard-coded based off of individual classes
     */
    public static Set<Block> getEffectiveForToolType(EnumToolType type)
    {
        Set<Block> result;
        switch (type)
        {
            case PICKAXE:
                result = Sets.newHashSet(Blocks.COBBLESTONE, Blocks.DOUBLE_STONE_SLAB, Blocks.STONE_SLAB,
                                         Blocks.STONE, Blocks.SANDSTONE, Blocks.MOSSY_COBBLESTONE, Blocks.IRON_ORE,
                                         Blocks.IRON_BLOCK,
                                         Blocks.COAL_ORE, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.DIAMOND_ORE,
                                         Blocks.DIAMOND_BLOCK,
                                         Blocks.ICE, Blocks.NETHERRACK, Blocks.LAPIS_ORE, Blocks.LAPIS_BLOCK,
                                         Blocks.REDSTONE_ORE,
                                         Blocks.LIT_REDSTONE_ORE, Blocks.RAIL, Blocks.DETECTOR_RAIL, Blocks.GOLDEN_RAIL,
                                         Blocks.ACTIVATOR_RAIL);
                break;
            case AXE:
                result = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2,
                                         Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN);
                break;
            case SHOVEL:
                result = Sets.newHashSet(Blocks.GRASS, Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL,
                                         Blocks.SNOW_LAYER, Blocks.SNOW, Blocks.CLAY, Blocks.FARMLAND, Blocks.SOUL_SAND,
                                         Blocks.MYCELIUM);
                break;
            default:
                result = Collections.singleton(null);
                break;
        }

        return result;
    }

    public static boolean isFood(ItemStack item)
    {
        if (item == null) return false;
        return item.getItem() instanceof ItemFood;
    }

    /**
     * A utility enum for determining a difference between armor types (for easy of access)
     */
    public enum EnumArmorType
    {
        /**
         * Represents a helmet
         */
        HELMET(EntityEquipmentSlot.HEAD),
        /**
         * Represents a chestplate
         */
        CHESTPLATE(EntityEquipmentSlot.CHEST),
        /**
         * Represents leggings (pants)
         */
        LEGGINGS(EntityEquipmentSlot.LEGS),
        /**
         * Represents boots
         */
        BOOTS(EntityEquipmentSlot.FEET);
        public static final EnumArmorType[]     VALUES = values();
        public final        EntityEquipmentSlot slot;

        EnumArmorType(EntityEquipmentSlot slot)
        {
            this.slot = slot;
        }
    }
}
