package com.httymd.block;

import com.httymd.HTTYMDMod;
import com.httymd.block.registry.BlockRegistry;
import com.httymd.block.registry.IRegisterable;
import com.httymd.item.util.ItemUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * An {@link IRegisterable} of {@link Block}
 *
 * @author George Albany
 */
public class BlockExtension extends Block implements IRegisterable
{
    public BlockExtension(String name, Material mat)
    {
        this(name, HTTYMDMod.getCreativeTab(), mat);
    }

    public BlockExtension(String name, CreativeTabs tab, Material mat)
    {
        super(mat);
        this.setCreativeTab(tab);
        this.setTranslationKey(ItemUtils.findUnlocName(name));
    }

    @Override
    public Block registerBlock()
    {
        BlockRegistry.registerBlock(this, this.getTranslationKey());
        return this;
    }

}
