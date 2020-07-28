package com.httymd.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.httymd.item.util.EnumWeaponType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * A Battlegear 2 manipulator extension of {@link ItemWeapon} for Warhammers
 *
 * @author George Albany
 */
public class ItemWarhammer extends ItemWeapon
{
    protected final float hitTime = 4F;

    public ItemWarhammer(ToolMaterial toolMaterial, String weaponName, float weaponDamage)
    {
        super(toolMaterial, weaponName, weaponDamage);
    }

    public ItemWarhammer(ToolMaterial mat)
    {
        this(mat, EnumWeaponType.HAMMER.getName(), EnumWeaponType.HAMMER.getDamage());
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
                         new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -3f, 2));
        }
        //1.8: field_111210_e to itemModifierUUID
        return multimap;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity user, int p_77663_4_, boolean inHand)
    {
        if (inHand && user instanceof EntityLivingBase)
        {
            EntityLivingBase live = (EntityLivingBase) user;
            PotionEffect effect = new PotionEffect(
                    Potion.REGISTRY.getObject(new ResourceLocation("mining_fatigue")), 1, 10, true, false);
            live.addPotionEffect(effect);
        }
    }
}
