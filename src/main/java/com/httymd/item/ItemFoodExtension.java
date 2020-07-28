package com.httymd.item;

import com.httymd.HTTYMDMod;
import com.httymd.item.registry.IRegisterable;
import com.httymd.item.registry.ItemRegistry;
import com.httymd.util.CreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

/**
 * An {@link IRegisterable} of {@link ItemFood}
 *
 * @author George Albany
 */
public class ItemFoodExtension extends ItemFood implements IRegisterable
{

    public ItemFoodExtension(String name, int heal, float satu, boolean wolf)
    {
        this(name, heal, satu, wolf, CreativeTab.DRAGON_TAB);
    }

    public ItemFoodExtension(String name, int heal, float satu, boolean wolf, CreativeTabs tab)
    {
        super(heal, satu, wolf);
        this.setTranslationKey(HTTYMDMod.ID + ":" + name.toLowerCase());
        //this.setTextureName(ItemUtils.findTextureName(this.getUnlocalizedName()));
        this.setCreativeTab(tab);
    }

    @Override
    public ItemFood registerItem()
    {
        ItemRegistry.registerItem(this, this.getTranslationKey());
        return this;
    }
}
