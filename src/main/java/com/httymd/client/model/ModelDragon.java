package com.httymd.client.model;

import com.httymd.client.animation.Animation;
import com.httymd.client.animation.AnimationHandler;
import com.httymd.client.animation.AnimationHandler.Priority;
import com.httymd.entity.EntityDragon;
import com.httymd.entity.EntityTameableFlying;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.List;

public abstract class ModelDragon extends ModelBase
{

    private final HashMap<EntityDragon, AnimationHandler> animationHandlerList;

    protected ModelBase model = this;

    public ModelDragon()
    {
        animationHandlerList = new HashMap<>();
    }

    public abstract ModelRenderer getRoot();

    public abstract ModelRenderer getHead();

    public abstract List<ModelRenderer> getLegs();

    public abstract ModelRenderer getWing();

    public abstract Animation getIdle();

    public abstract Animation getFlying();

    public abstract Animation getSitting();

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {

        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        GL11.glPushMatrix();

        //GL11.glRotated(90, 0, 1, 0);

        boolean flying  = ((EntityTameableFlying) entity).isFlying();
        boolean sitting = ((EntityTameable) entity).isSitting();

        if (flying)
            getAnimationHandler((EntityDragon) entity)
                    .addAnimation(getFlying(), Priority.WAIT_FOR_ANIM_TO_FINISH, true);
        else if (sitting)
            getAnimationHandler((EntityDragon) entity)
                    .addAnimation(getSitting(), Priority.WAIT_FOR_ANIM_TO_FINISH, true);
        else
            getAnimationHandler((EntityDragon) entity).addAnimation(getIdle(), Priority.WAIT_FOR_ANIM_TO_FINISH, true);

        float pitch = flying ? entity.rotationPitch : 0;

        GL11.glRotatef(pitch / 2, 1, 0, 0);

        getRoot().render(f5);

        GL11.glPopMatrix();
    }

    /**
     * @param f      step of moving
     * @param f1     body part amplitude
     * @param f2     ?
     * @param f3     lookAt spherical angle Y
     * @param f4     lookAt spherical angle X
     * @param f5     some weird parameter scaling the rotation point @param f
     * @param entity
     *
     * @see net.minecraft.client.model.ModelBase#setRotationAngles(float, float, float, float, float, float,
     * net.minecraft.entity.Entity)
     */
    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        if (getHead() != null)
        {
            getHead().rotateAngleX = f4 / (180F / (float) Math.PI);
            getHead().rotateAngleY = f3 / (180F / (float) Math.PI);
        }

        getAnimationHandler((EntityDragon) entity).animate();

        if (((EntityDragon) entity).isFlying())
        {
            if (getHead() != null)
            {
                getHead().rotateAngleX /= 2;
                getHead().rotateAngleY /= 2;
            }
            if (getLegs() != null)
                for (int i = 0; i < getLegs().size(); i++)
                     getLegs().get(i).rotateAngleX = 0;
        }
        else if (!((EntityDragon) entity).isSitting())
        {
            if (getLegs() != null)
                for (int i = 0; i < getLegs().size(); i++)
                {
                    float rx = (i % 2 == 0 ? 1 : -1) * MathHelper.cos(f * 0.6662F) * 1.4f * f1;
                    //System.out.println(rx);
                    getLegs().get(i).rotateAngleX = rx / 3;
                }
        }
    }

    protected void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    private AnimationHandler getAnimationHandler(EntityDragon entity)
    {
        AnimationHandler animationHandler = animationHandlerList.get(entity);
        if (animationHandler == null)
        {
            animationHandler = new AnimationHandler(this);
            animationHandlerList.put(entity, animationHandler);
        }
        return animationHandler;
    }

    // XXX Here or in own class?
    public class WingRenderer extends ModelRenderer
    {
        public WingRenderer(ModelBase p_i1173_1_)
        {
            super(p_i1173_1_);
        }

        public WingRenderer(ModelBase p_i1174_1, int p_i1174_2, int p_i1174_3_)
        {
            super(p_i1174_1, p_i1174_2, p_i1174_3_);
        }

        public WingRenderer(ModelBase p_i1172_1, String p_i1172_2)
        {
            super(p_i1172_1, p_i1172_2);
        }

        @Override
        public void render(float p_78785_1_)
        {
            GL11.glEnable(GL11.GL_CULL_FACE);
            super.render(p_78785_1_);
            GL11.glScalef(-1, 1, 1);
            GL11.glCullFace(GL11.GL_FRONT);
            super.render(p_78785_1_);
            GL11.glScalef(-1, 1, 1);
            GL11.glCullFace(GL11.GL_BACK);
            GL11.glDisable(GL11.GL_CULL_FACE);
        }

    }
}