package com.httymd.client.render;

import com.httymd.HTTYMDMod;
import com.httymd.entity.EntityDragon;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.Objects;

@SideOnly(Side.CLIENT)
public class RenderDragon extends RenderLiving
{
    private static final String entityTexLoc = HTTYMDMod.ID + ":textures/entities/";

    public RenderDragon(RenderManager m, ModelBase modelbaseIn, float shadowsizeIn)
    {
        super(m, modelbaseIn, shadowsizeIn);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        try
        {
            if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)) this.mainModel = mainModel.getClass().newInstance();
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return this.getEntityTexture((EntityDragon) p_110775_1_);
    }

    protected ResourceLocation getEntityTexture(EntityDragon dragon)
    {
        return new ResourceLocation(
                entityTexLoc + Objects.requireNonNull(EntityList.getEntityString(dragon)).replace(HTTYMDMod.ID + ".", "") + ".png");
    }
}