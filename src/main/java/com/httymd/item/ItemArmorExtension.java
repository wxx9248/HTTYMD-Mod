package com.httymd.item;

import com.httymd.HTTYMDMod;
import com.httymd.item.registry.IRegisterable;
import com.httymd.item.registry.ItemRegistry;
import com.httymd.item.util.ItemUtils;
import com.httymd.util.CreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

/**
 * Am {@link IRegisterable} of {@link ItemArmor}, with some ease of use methods
 *
 * @author George Albany
 */
public class ItemArmorExtension extends ItemArmor implements IRegisterable
{

    public ItemArmorExtension(String name, ArmorMaterial mat, EntityEquipmentSlot type)
    {
        this(name, mat, type, CreativeTab.DRAGON_TAB);
    }

    public ItemArmorExtension(String name, ArmorMaterial mat, EntityEquipmentSlot type, CreativeTabs tab)
    {
        super(mat, 2, type);
        this.setTranslationKey(ItemUtils.findUnlocName(name));
        this.setCreativeTab(tab);
    }

    /**
     * Retrieves armor texture based on name
     *
     * <p>Directory in &lt;modid&gt;:textures/armor/name_<1 or 2>.png</p>
     */
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return HTTYMDMod.ID + ":textures/armor/"
               + this.getRegistryName().getPath()
                     .substring(0, this.getRegistryName().getPath().lastIndexOf("_")) + "_"
               + (slot == EntityEquipmentSlot.LEGS ? "2" : "1") + ".png";
    }

    @Override
    public ItemArmorExtension registerItem()
    {
        ItemRegistry.registerItem(this, this.getTranslationKey());
        return this;
    }
}
