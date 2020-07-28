package com.httymd.item;

import com.httymd.HTTYMDMod;
import com.httymd.util.CreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Base Item class for Hiccup's Inferno
 *
 * @author George Albany
 */
public class ItemFlameSword extends ItemWeapon
{

    public static final  String DISABLED_SUFFIX = "_disabled";
    private static final String NBT_ISON        = "IsOn";

    protected float defaultDamage;

    public ItemFlameSword(Item.ToolMaterial toolMaterial, String name, float defAttackDamage)
    {
        super(name, toolMaterial, defAttackDamage, CreativeTab.DRAGON_TAB);
        this.defaultDamage = this.attackDamage;
        this.addPropertyOverride(new ResourceLocation(HTTYMDMod.ID, "flame_on"), new IItemPropertyGetter()
        {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return isToggled(stack) ? 1 : 0;
            }
        });
    }

    /**
     * Creates an explosion usable by this item
     */
    private void explode(World world, Entity entity, float explosionSize)
    {
        world.createExplosion(entity, entity.posX, entity.posY, entity.posZ, explosionSize, false);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list)
    {
        if (isInCreativeTab(tab))
        {
            for (int i = 0; i < 1; i++)
            {
                ItemStack is = new ItemStack(this, 1);
                this.onToggle(is, false);
                list.add(is);
            }
        }
    }

    /**
     * Determines if ItemStack is toggled based on NBT
     */
    public boolean isToggled(ItemStack stack)
    {
        if (stack.getItem() instanceof ItemFlameSword && stack.hasTagCompound())
            return stack.getTagCompound().getBoolean(NBT_ISON);
        return false;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn)
    {
        if (player.isSneaking())
        {
            if (!world.isRemote)
                this.explode(world, player, 2F);
        }
        else
        {
            //world.playSoundAtEntity(player, "mob.sheep.shear", 2.0F, 1F);
            ItemStack stack = player.getHeldItem(handIn);
            this.onToggle(stack, !this.isToggled(stack));
        }
        return super.onItemRightClick(world, player, handIn);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if (this.isToggled(stack)) entity.setFire(60);
        return super.onLeftClickEntity(stack, player, entity);
    }

    /**
     * Sets toggle for ItemStack
     */
    public void onToggle(ItemStack stack, boolean toggle)
    {
        if (!(stack.getItem() instanceof ItemFlameSword))
            return;
        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setBoolean(NBT_ISON, toggle);
        this.attackDamage = toggle ? this.defaultDamage : 1;
    }
}
