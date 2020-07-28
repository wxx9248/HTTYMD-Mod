package com.httymd.common.network;

import io.netty.channel.ChannelHandler.Sharable;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Sharable
public class ControlMessageHandler implements IMessageHandler<ControlMessage, IMessage>
{

    private static final Logger L = LogManager.getLogger();

    @SuppressWarnings("unused")
    @Override
    public IMessage onMessage(ControlMessage message, MessageContext ctx)
    {
        // check if the server is messing with the protocol
        if (ctx.side == Side.CLIENT)
        {
            L.warn("Recieved unexpected control message from server!");
            return null;
        }
        EntityPlayerMP player = ctx.getServerHandler().player;
        //if (player.ridingEntity instanceof EntityTameableFlying) EntityTameableFlying dragon =
        // (EntityTameableFlying) player.ridingEntity;
        // dragon.setControlFlags(message.getFlags());
        // receive only
        return null;
    }
}
