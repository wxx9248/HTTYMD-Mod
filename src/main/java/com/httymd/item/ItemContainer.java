package com.httymd.item;

import com.httymd.util.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * May eventually be container for dragon byproducts
 *
 * @author George Albany
 * @deprecated Currently useless
 */
@Deprecated
public class ItemContainer extends ItemExtension
{

    public final Class<? extends Entity> produceHolder;
    protected    float                   maxHoldable;

    public ItemContainer(String name, float maxHoldable, Class<? extends Entity> produceHolder)
    {
        super(name);
        this.produceHolder = produceHolder;
        this.maxHoldable   = maxHoldable;
    }

    public static float getMaxHoldable(ItemStack stack)
    {
        Item item = stack.getItem();
        if (item instanceof ItemContainer)
        {
            ItemContainer cont = (ItemContainer) item;
            return cont.maxHoldable;
        }
        return -1.0F;
    }

    public static float getProduceHeld(ItemStack stack)
    {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("ProduceHeld"))
            return stack.getTagCompound().getFloat("ProduceHeld");
        return 0.0F;
    }

    public static boolean hasProduce(ItemStack stack)
    {
        return getProduceHeld(stack) > 0.0F;
    }

    public static void resetProduce(ItemStack stack)
    {
        setProduceHeld(stack, 0.0F);
    }

    public static void setProduceHeld(ItemStack stack, float amount)
    {
        if (stack.hasTagCompound())
            stack.getTagCompound().setFloat("ProduceHeld", amount);
        else
        {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setFloat("ProduceHeld", amount);
            stack.setTagCompound(nbt);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        String text = TextFormatting.DARK_PURPLE + "" + TextFormatting.ITALIC
                      + Utils.getLocalString(this.getRegistryName() + ".produceType") + ": ";
        if (hasProduce(stack))
            text += getProduceHeld(stack) + " " + Utils.getLocalString("itemNBT.milliliter");
        else
            text += Utils.getLocalString("itemNBT.empty");

        tooltip.add(text);
    }

    public ItemStack getEmptyContainer()
    {
        ItemStack      stack = new ItemStack(this);
        NBTTagCompound tag   = new NBTTagCompound();
        tag.setFloat("ProduceHeld", 0.0F);
        stack.setTagCompound(tag);
        return stack;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (isInCreativeTab(tab))
        {
            for (int i = 0; i < 1; i++)
            {
                ItemStack is = new ItemStack(this, 1);
                resetProduce(is);
                items.add(is);
            }
        }
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if (entity.getClass() == this.produceHolder)
        {
            float currentHeld = getProduceHeld(stack);
            if (currentHeld < this.maxHoldable)
            {
                currentHeld += player.getRNG().nextFloat();
                currentHeld = MathHelper.clamp(currentHeld, 0.0F, this.maxHoldable);
                setProduceHeld(stack, currentHeld);
                return true;
            }
        }

        return super.onLeftClickEntity(stack, player, entity);
    }

}
