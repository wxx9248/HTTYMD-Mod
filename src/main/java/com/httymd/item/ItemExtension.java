package com.httymd.item;

import com.httymd.item.registry.IRegisterable;
import com.httymd.item.registry.ItemRegistry;
import com.httymd.item.util.ItemUtils;
import com.httymd.util.CreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * An {@link IRegisterable} of {@link Item}
 *
 * @author George Albany
 */
public class ItemExtension extends Item implements IRegisterable
{

    public ItemExtension(String name)
    {
        this(name, CreativeTab.DRAGON_TAB);
    }

    public ItemExtension(String name, CreativeTabs tab)
    {
        this.setCreativeTab(tab);
        this.setTranslationKey(ItemUtils.findUnlocName(name));
    }

    @Override
    public Item registerItem()
    {
        ItemRegistry.registerItem(this, this.getTranslationKey());
        return this;
    }
}
