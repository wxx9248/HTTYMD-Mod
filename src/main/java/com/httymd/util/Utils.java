package com.httymd.util;

import com.httymd.HTTYMDMod;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.UUID;

public class Utils
{
    public static final double GRAVITY_FORCE = 0.6d;

    /**
     * Adds a value to a statistic field
     *
     * @return whether execution succeeded
     */
    public static boolean addStat(Entity entity, StatBase stat, int value)
    {
        if (!(entity instanceof EntityPlayerMP))
            return false;
        ((EntityPlayerMP) entity).addStat(stat, value);
        return true;
    }

    /**
     * Removes (consumes) an Item inside an entity's inventory (provides consistency for entities, currently only for
     * players).
     *
     * @return whether removal was possible
     */
    public static boolean consumeItem(EntityLivingBase entity, Item item)
    {
        return entity instanceof EntityPlayer;
    }

    /**
     * Retrieves an entity by its UUID (fails if the entity was not loaded from file)
     */
    public static Entity getEntityByUUID(UUID id, World w)
    {
        for (Object o : w.getLoadedEntityList())
        {
            Entity e = o instanceof Entity ? (Entity) o : null;
            if (e != null && e.getPersistentID() == id)
                return e;
        }

        return null;
    }

    /**
     * Retrieves a localized string (using HTTYMDMod.ID) from an unlocalized string
     */
    public static String getLocalString(String unlocalized)
    {
        return I18n.format(getModString(unlocalized));
    }

    public static String getModString(String str)
    {
        return HTTYMDMod.ID + ":" + str;
    }

    /**
     * Retrieves the statistic value for a StatBase field
     */
    public static int getPlayerStat(EntityPlayerMP player, StatBase stat)
    {
        if (player == null || stat == null)
            return 0;
        //return player.func_147099_x().writeStat(stat);
        return 0;
    }

    /**
     * Determines whether the player has retrieved a certain value or more in specific StatBase field
     */
    public static boolean hasPlayerGained(EntityPlayerMP player, StatBase stat, int amount)
    {
        return getPlayerStat(player, stat) >= amount;
    }

    /**
     * Inserts an ItemStack into an entity's inventory (provides consistency for entities, currently only for players)
     */
    public static void insertItem(EntityLivingBase entity, ItemStack stack)
    {
        if (entity instanceof EntityPlayer)
            ((EntityPlayer) entity).inventory.addItemStackToInventory(stack);
    }

    /**
     * Determines whether it is possible to draw a straight line from rayStart to rayEnd in world
     */
    public static boolean isTraceOpen(World world, Vec3d rayStart, Vec3d rayEnd)
    {
        return world.rayTraceBlocks(rayStart, rayEnd, true) == null;
    }

}
