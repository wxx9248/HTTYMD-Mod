package com.httymd.client.event;

import com.httymd.client.render.RenderGlide;
import com.httymd.item.ItemGlideArmor;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerClientHandler
{

    protected RenderGlide glideRender = null;

    protected float  playerTicks = 0.0F;
    protected Entity ent         = null;

    @SubscribeEvent
    public void beforeBodyRender(RenderLivingEvent.Pre event)
    {
        //GL11.glRotated(90, 0, 1, 0);
        if (!(event
                .getEntity() instanceof EntityPlayer) || event.getRenderer() == this.glideRender || event.getEntity() != this.ent)
            return;

        ItemStack stack = event.getEntity().getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        if (stack == null || !(stack.getItem() instanceof ItemGlideArmor))
            return;
        if (((ItemGlideArmor) stack.getItem()).isGliding(stack))
        {
            glideRender.doRender((AbstractClientPlayer) event.getEntity(), event.getX(),
                                 event.getY() + event.getEntity().getEyeHeight(), event.getZ(), 0.0F, playerTicks);
            event.setCanceled(true);
        }/*else{
			glideRender = null;
		}*/
    }

    @SubscribeEvent
    public void beforePlayerBodyRender(RenderPlayerEvent.Pre event)
    {
        this.renderLoad();

        this.playerTicks = event.getPartialRenderTick();
        this.ent         = event.getEntityLiving();

		/*GL11.glPushMatrix();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 124);
		GL11.glTranslated(0, 0, 50);
		Gui.drawModalRectWithCustomSizedTexture(-5, -10, 0, 0, 64, 64, 64, 32);
		GL11.glPopMatrix();*/
    }

    public void renderLoad()
    {
        if (this.glideRender == null)
        {
            this.glideRender = new RenderGlide();
        }
    }


}
