package com.httymd.item.util;

import net.minecraft.block.Block;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * An enum designated to hold types of tools for easier registry management
 *
 * @author George Albany
 */
public enum EnumToolType
{

    PICKAXE(2.0F),
    AXE(3.0F),
    SHOVEL(1.0F),
    HOE(0.0F);

    private final String     toolName;
    private final float      naturalDamage;
    private       Set<Block> effectiveBlocks;
    private       int        fuelTime = 150;

    EnumToolType(float damage)
    {
        this.toolName        = this.toString().toLowerCase();
        this.naturalDamage   = damage;
        this.effectiveBlocks = null;
    }

    EnumToolType(float damage, int fuelTime)
    {
        this(damage);
        this.fuelTime = fuelTime;
    }

    EnumToolType(String name, float damage, int fuelTime, Set<Block> effectiveBlocks)
    {
        this.toolName        = name.toLowerCase();
        this.naturalDamage   = damage;
        this.fuelTime        = fuelTime;
        this.effectiveBlocks = effectiveBlocks;
    }

    /**
     * Retrieves all blocks a collection of {@link EnumToolType EnumToolTypes} is effective against
     */
    public static Set<Block> getAllEffectiveBlocksOf(Collection<EnumToolType> types)
    {
        Set<Block> result = new HashSet<Block>();
        for (EnumToolType t : types)
            result.addAll(t.getEffectiveBlocks());
        return result;
    }

    /**
     * Retrieves all names of a collection of {@link EnumToolType EnumToolTypes} into a set
     */
    public static Set<String> getAllNames(Collection<EnumToolType> types)
    {
        Set<String> result = new HashSet<String>();
        for (EnumToolType t : types)
            result.add(t.getName());
        return result;
    }

    /**
     * Retrieves the max possible damage a collection of {@link EnumToolType EnumToolTypes} can cause to entities
     */
    public static float getResultDamageOf(Collection<EnumToolType> types)
    {
        float result = 0;
        for (EnumToolType t : types)
            result = Math.max(result, t.getAttackDamage());
        return result;
    }

    /**
     * Retrieves an average of fuel time for a collection of {@link EnumToolType EnumToolTypes}
     */
    public static int getAverageFuelTime(Collection<EnumToolType> types)
    {
        int sum = 0;
        for (EnumToolType t : types)
            sum += t.getFuelTime();
        return sum / types.size();
    }

    /**
     * Gets a tool type's base damage
     */
    public float getAttackDamage()
    {
        return this.naturalDamage;
    }

    /**
     * Retrieves a fuel time for this tool type
     *
     * <p>Shall be ignored for fuel registry if value is less then 1</p>
     */
    public int getFuelTime()
    {
        return this.fuelTime;
    }

    /**
     * Retrieves all effective blocks for a tool
     *
     * <p>Normal tool types use {@link ItemUtils#getEffectiveForToolType(EnumToolType)}</p>
     */
    public Set<Block> getEffectiveBlocks()
    {
        if (this.effectiveBlocks == null)
            this.effectiveBlocks = ItemUtils.getEffectiveForToolType(this);
        return this.effectiveBlocks;
    }

    /**
     * Retrieves the lowercased name for the tool
     *
     * <p>For use with {@link net.minecraft.item.ItemTool#getToolClasses(net.minecraft.item.ItemStack)
     * ItemTool.getToolClasses(ItemStack)}</p>
     */
    public String getName()
    {
        return this.toolName;
    }
}
