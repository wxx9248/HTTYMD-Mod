package com.httymd.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.httymd.HTTYMDMod;
import com.httymd.item.util.EnumWeaponType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

/**
 * A Battlegear 2 manipulator extension of {@link ItemWeapon} for daggers
 *
 * @author George Albany
 */
public class ItemDagger extends ItemWeapon
{
    protected final float reach = -2;

    public ItemDagger(ToolMaterial weaponMat, String name, float weaponDam, CreativeTabs tab)
    {
        super(weaponMat, name, weaponDam, tab);
    }

    public ItemDagger(ToolMaterial weaponMat, String name, float weaponDam)
    {
        this(weaponMat, name, weaponDam, HTTYMDMod.getCreativeTab());
    }

    public ItemDagger(ToolMaterial mat)
    {
        this(mat, EnumWeaponType.DAGGER.getName(), EnumWeaponType.DAGGER.getDamage());
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.create();
        if (slot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
                         new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
                         new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -0.5f, 0));
        }
        //1.8: field_111210_e to itemModifierUUID
        return multimap;
    }
}
