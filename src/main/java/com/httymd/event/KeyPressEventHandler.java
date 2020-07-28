package com.httymd.event;

import com.httymd.HTTYMDMod;
import com.httymd.common.network.PlyJumpMessage;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class KeyPressEventHandler
{

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event)
    {
        Minecraft mc = FMLClientHandler.instance().getClient();
        if (mc != null && mc.player != null && mc.world != null)
        {
            if (Keyboard.getEventKey() == mc.gameSettings.keyBindJump.getKeyCode())
            {
                HTTYMDMod.proxy.getNetwork().sendToServer(new PlyJumpMessage(true));
            }
        }
    }

}
