package com.httymd;

import com.httymd.common.CommonProxy;
import com.httymd.entity.EntityRegister;
import com.httymd.item.recipe.RecipeRegistry;
import com.httymd.util.CreativeTab;
import com.httymd.util.StatListMod;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

@Mod(modid = HTTYMDMod.ID, name = HTTYMDMod.NAME, guiFactory = HTTYMDMod.GUIFACTORY, updateJSON = HTTYMDMod.UPDATE_JSON)
public class HTTYMDMod
{
    //////////////////////////////////////////////////////
    // Constant Identifier Variables
    //////////////////////////////////////////////////////
    public static final String ID           = "httymd";
    public static final String NAME         = "HTTYMD";
    public static final String CLIENT_PROXY = "com.httymd.client.ClientProxy";
    public static final String COMMON_PROXY = "com.httymd.common.CommonProxy";
    public static final String GUIFACTORY   = "com.httymd.client.GuiFactoryDragons";
    public static final String UPDATE_JSON  = "https://raw.githubusercontent.com/wxx9248/HTTYMD-Mod/version-check.json";
    //////////////////////////////////////////////////////
    // End Constant Identifier Variables
    //////////////////////////////////////////////////////

    @Instance(ID)
    public static HTTYMDMod INSTANCE;

    @SidedProxy(modId = ID, clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy       proxy;
    private final Logger            log            = LogManager.getLogger(NAME);
    private final ArrayList<String> dragonNameList = new ArrayList<>();
    private       ModMetadata       metadata;
    private       Config            config;

    /**
     * Retrieves the config for the mod
     */
    public static Config getConfig()
    {
        return INSTANCE.config;
    }

    /**
     * Retrieves the mod Creative Tab instance
     */
    public static CreativeTab getCreativeTab()
    {
        return CreativeTab.DRAGON_TAB;
    }

    /**
     * Retrieves a clone list of the current list of registered dragon names
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<String> getDragonList()
    {
        return (ArrayList<String>) INSTANCE.dragonNameList.clone();
    }

    /**
     * Retrieves the mod's logger
     */
    public static Logger getLogger()
    {
        return INSTANCE.log;
    }

    /**
     * Retrieves the mod's metadata object
     */
    public static ModMetadata getMetadata()
    {
        return INSTANCE.metadata;
    }

    public static void registerDragonName(String name)
    {
        INSTANCE.dragonNameList.add(name);
    }

    public static void registerEntity(Class<? extends Entity> entityClass, String entityName, int solidColor,
                                      int spotColor)
    {
        EntityRegister.createEntity(entityClass, entityName, solidColor, spotColor);
    }

    @EventHandler
    public void modInit(FMLInitializationEvent event)
    {
        RecipeRegistry.init();
        MinecraftForge.EVENT_BUS.register(this);
        proxy.onInit(event);
    }

    @EventHandler
    public void modPostInit(FMLPostInitializationEvent event)
    {
        proxy.onPostInit(event);
    }

    @EventHandler
    public void modPreInit(FMLPreInitializationEvent event)
    {
        this.config   = new Config(event);
        this.metadata = event.getModMetadata();
        proxy.onPreInit(event);
        StatListMod.registerStats(); // Guarantees stats register with all information (not used yet)
    }

    @SubscribeEvent
    public void onConfigChanged(OnConfigChangedEvent eventArgs)
    {
        if (eventArgs.getModID().equals(ID))
            this.config.syncConfig();
    }

    @EventHandler
    public void onServerStarted(FMLServerStartedEvent evt)
    {
        proxy.onServerStarted(evt);
    }

    @EventHandler
    public void onServerStopped(FMLServerStoppedEvent evt)
    {
        proxy.onServerStopped(evt);
    }
}
