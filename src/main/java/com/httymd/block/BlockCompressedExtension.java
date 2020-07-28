package com.httymd.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * An extension of {@link BlockExtension} replicating {@link net.minecraft.block.BlockCompressed BlockCompressed}
 *
 * @author George Albany
 */
public class BlockCompressedExtension extends BlockExtension
{

    protected MapColor color;

    public BlockCompressedExtension(String name, MapColor color)
    {
        super(name, Material.IRON);
    }

    public BlockCompressedExtension(String name, CreativeTabs tab, MapColor color)
    {
        super(name, tab, Material.IRON);
        this.color = color;
    }

    public MapColor getMapColor(int p_149728_1_)
    {
        return this.color;
    }
}
