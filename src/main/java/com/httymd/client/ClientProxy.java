package com.httymd.client;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.function.Consumer;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.httymd.HTTYMDMod;
import com.httymd.client.event.PlayerClientHandler;
import com.httymd.client.model.dragon.ModelDeadlyNadder;
import com.httymd.client.model.dragon.ModelNightFury;
import com.httymd.client.model.dragon.ModelNightmare;
import com.httymd.client.model.dragon.ModelSkrill;
import com.httymd.client.model.dragon.ModelTerribleTerror;
import com.httymd.client.render.RenderDragon;
import com.httymd.client.util.KeyInputHandler;
import com.httymd.common.CommonProxy;
import com.httymd.entity.dragon.EntityLightFury;
import com.httymd.entity.dragon.EntityNadder;
import com.httymd.entity.dragon.EntityNightFury;
import com.httymd.entity.dragon.EntityNightmare;
import com.httymd.entity.dragon.EntitySkrill;
import com.httymd.entity.dragon.EntityTerribleTerror;
import com.httymd.event.KeyPressEventHandler;
import com.httymd.item.ItemShieldM;
import com.httymd.item.ItemWeapon;
import com.httymd.item.registry.ItemRegistry;

public class ClientProxy extends CommonProxy {
	public static KeyInputHandler keybindHandler;

	@Override
	public void onInit(FMLInitializationEvent evt) {
		super.onInit(evt);

		keybindHandler = new KeyInputHandler(this.getNetwork());

		MinecraftForge.EVENT_BUS.register(new PlayerClientHandler());
	}

	@SuppressWarnings({"unchecked"})
	private void registerEntityRendering() {
		// RenderingRegistry.registerEntityRenderingHandler(EntityGronkle.class, new RenderGronkle(new ModelGronkle(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityNightFury.class, m -> new RenderDragon(m, new ModelNightFury(), 2));
		RenderingRegistry.registerEntityRenderingHandler(EntityNightmare.class, m -> new RenderDragon(m, new ModelNightmare(), 2));
		RenderingRegistry.registerEntityRenderingHandler(EntityNadder.class, m -> new RenderDragon(m, new ModelDeadlyNadder(), 2));
		RenderingRegistry.registerEntityRenderingHandler(EntitySkrill.class, m -> new RenderDragon(m, new ModelSkrill(), 2));
		RenderingRegistry.registerEntityRenderingHandler(EntityTerribleTerror.class, m -> new RenderDragon(m, new ModelTerribleTerror(), 0.5F));
		// RenderingRegistry.registerEntityRenderingHandler(EntityZippleback.class, new RenderZippleback(new ModelZippleback(), 1));
		RenderingRegistry.registerEntityRenderingHandler(EntityLightFury.class, m -> new RenderDragon(m, new ModelNightFury(), 2));
	}

	@Override
	public void onPreInit(FMLPreInitializationEvent event) {
		super.onPreInit(event);
		this.registerItemRendering();
		this.registerEntityRendering();
	}

	private void registerItemRendering() {
		// MinecraftForgeClient.registerItemRenderer(ItemRegistry.zippleGasContainer, new RenderItemContainer());
		// empty for 1.8 compat
		System.out.println("Loading models");
		//new File(".", "modelgen").mkdirs();
		for(Entry<String, Item> e : ItemRegistry.itemRegistry.entrySet()){
			addRenderToRegistry(e.getValue(), 0, e.getKey());
			//writeModel(e.getKey(), e.getValue());
		}
		//throw new RuntimeException("Model gen finished");
	}

	private static void writeModel(String name, Item item){
		if(item instanceof ItemWeapon){
			writeModel(name, name, "item/handheld", w -> {});
		}else if(item instanceof ItemShieldM){
			writeModel(name, name, "httymd:item/shield_base", w -> {
				w.println(',');
				w.println("  \"overrides\": [");
				w.println("    { \"predicate\": { \"blocking\": 1 }, \"model\": \"httymd:item/" + name + "_blocking\" }");
				w.print  ("  ]");
			});
			writeModel(name + "_blocking", name, "httymd:item/shield_base_blocking", w -> {});
		}else{
			//writeModel(name, name, "item/generated", w -> {});
		}
	}

	private static void writeModel(String name, String tex, String parent, Consumer<PrintWriter> extra){
		File f = new File("./modelgen", name + ".json");
		try {
			PrintWriter wr = new PrintWriter(f);
			wr.println("{");
			wr.println("  \"parent\": \"" + parent + "\",");
			wr.println("  \"textures\": {");
			wr.println("    \"layer0\": \"httymd:items/" + tex + "\"");
			wr.print("  }");
			extra.accept(wr);
			wr.println();
			wr.println("}");
			wr.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private static void addRenderToRegistry(Item item, int meta, String name) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(HTTYMDMod.ID, name), "inventory"));
	}

	@Override
	protected void registerHandlers() {
		super.registerHandlers();
		MinecraftForge.EVENT_BUS.register(new KeyPressEventHandler());
	}
}
