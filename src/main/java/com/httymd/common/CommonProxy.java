package com.httymd.common;

import com.httymd.HTTYMDMod;
import com.httymd.block.registry.BlockRegistry;
import com.httymd.common.network.PlyJumpMessage;
import com.httymd.entity.EntityRegister;
import com.httymd.entity.dragon.*;
import com.httymd.event.ForgeEventHandler;
import com.httymd.item.registry.ItemRegistry;
import com.httymd.item.registry.MaterialRegistry;
import com.httymd.item.registry.WorldItemRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy
{

    private SimpleNetworkWrapper network;

    /**
     * Retrieves {@link SimpleNetworkWrapper} of this proxy
     */
    public SimpleNetworkWrapper getNetwork()
    {
        return this.network;
    }

    public void onInit(FMLInitializationEvent evt)
    {
        EntityRegister.registerEnities();
        this.registerHandlers();

        this.network = NetworkRegistry.INSTANCE.newSimpleChannel(HTTYMDMod.ID + "Channel");
        this.network.registerMessage(PlyJumpMessage.PlyJumpMsgHandler.class, PlyJumpMessage.class, 0, Side.SERVER);
    }

    public void onPostInit(FMLPostInitializationEvent event)
    {
    }

    public void onPreInit(FMLPreInitializationEvent event)
    {
        MaterialRegistry.init();
        ItemRegistry.init();
        BlockRegistry.init();
        WorldItemRegistry.init();
        this.registerEntities();
    }

    ///////////////////////////////////////
    // Server Functions
    ///////////////////////////////////////
    public void onServerStarted(FMLServerStartedEvent evt)
    {
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        server.getCommandManager();
    }

    public void onServerStopped(FMLServerStoppedEvent evt)
    {
    }
    ///////////////////////////////////////
    // End Server Functions
    ///////////////////////////////////////

    private void registerEntities()
    {
        HTTYMDMod.registerEntity(EntityTerribleTerror.class, "TerribleTerror", 0x00FF00, 0x44FF44);
        HTTYMDMod.registerEntity(EntitySkrill.class, "Skrill", 0xFF0000, 0xFF4444);
        HTTYMDMod.registerEntity(EntityNightFury.class, "NightFury", 0x000000, 0x222222);
        HTTYMDMod.registerEntity(EntityNightmare.class, "MountrousNightmare", 0xE3172F, 0x000000);
        HTTYMDMod.registerEntity(EntityNadder.class, "DeadlyNadder", 0x1B99BF, 0xE3E017);
        //HTTYMDMod.registerEntity(EntityGronkle.class, "Gronkle", 0x1B99BF, 0xE3E017);
        //HTTYMDMod.registerEntity(EntityZippleback.class, "Zippleback", 0x00FF00, 0x44FF44);
        HTTYMDMod.registerEntity(EntityLightFury.class, "LightFury", 0xffffff, 0xdddddd);
    }

    protected void registerHandlers()
    {
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
    }
}
