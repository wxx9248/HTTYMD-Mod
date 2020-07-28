package com.httymd.item.util;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.httymd.item.ItemFoodDrop;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;

/**
 * An enum designated to hold types of food for easier registry management
 *
 * @author George Albany
 */
public enum EnumFoodType
{
    //MUTTON(true, 8, 0.8F, true, EntitySheep.class),

    CRAB(false, 6, 0.6F, false),
    HCOMB(false, 3, 0.3F, false),
    FWEED(false, 5, 0.5F, false),
    SFLOWER(false, 1, 0.1F, false),
    DNIP(false, 2, 0.5F, false);

    private final boolean                           isMeat;
    private final int                               heal;
    private final float                             satu;
    private final boolean                           wolfMeat;
    private final Class<? extends EntityLivingBase> dropsFor;

    EnumFoodType(boolean isMeat, int heal, float satu, boolean wolf)
    {
        this(isMeat, heal, satu, wolf, null);
    }

    EnumFoodType(boolean isMeat, int heal, float satu, boolean wolf,
                 Class<? extends EntityLivingBase> dropsFor)
    {
        this.isMeat   = isMeat;
        this.heal     = heal;
        this.satu     = satu;
        this.wolfMeat = wolf;
        this.dropsFor = dropsFor;
    }

    /**
     * Generates and returns a registered MultiMap for current food
     */
    public static Multimap<EnumFoodType, Item> generateFood()
    {
        Multimap<EnumFoodType, Item> foods = ArrayListMultimap.create();
        for (EnumFoodType food : EnumFoodType.values())
        {

            if (food.isMeat())
                foods.put(food, new ItemFoodDrop(food.toString() + "_raw", food.getHeal() / 2, food.getSaturation() / 2,
                                                 food.getForWolfs(), food.getDropFor()).registerItem());

            foods.put(food, new ItemFoodDrop(food.toString(), food.getHeal(), food.getSaturation(), food.getForWolfs(),
                                             food.getDropFor(), food.isMeat())
            {

                @Override
                public boolean isForEntity(EntityLivingBase entity)
                {
                    return super.isForEntity(entity) && entity.isBurning();
                }

            }.registerItem());
        }
        return foods;
    }

    /**
     * Retrieves the Entity class this food type will drop for (or null for none)
     */
    public Class<? extends EntityLivingBase> getDropFor()
    {
        return this.dropsFor;
    }

    /**
     * Whether a wolf can use this food type for healing
     */
    public boolean getForWolfs()
    {
        return this.wolfMeat;
    }

    /**
     * The amount of hunger restored by this food type (or amount healed for wolves)
     *
     * <p>(its called heal in minecraft, keeping consistent)</p>
     */
    public int getHeal()
    {
        return this.heal;
    }

    /**
     * The saturation level for food type
     *
     * @deprecated for incorrect spelling, removal in 2.0.0 (following http://semver.org guidelines)
     */
    @Deprecated
    public float getSatuartion()
    {
        return this.satu;
    }

    /**
     * The saturation level for food type
     */
    public float getSaturation()
    {
        return this.satu;
    }

    /**
     * Determines whether food is considered cookable or not
     */
    public boolean isMeat()
    {
        return this.isMeat;
    }
}
