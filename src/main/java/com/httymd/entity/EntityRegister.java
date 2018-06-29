package com.httymd.entity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.common.registry.EntityRegistry;

import com.httymd.HTTYMDMod;

public class EntityRegister {

	private static int nextID = 0;
	private static CreativeTabs currentTab = HTTYMDMod.getCreativeTab();
	private static List<Runnable> register = new ArrayList<>();
	public static void createEntity(Class<? extends Entity> entityClass, String entityName, int solidColor,
			int spotColor) {
		register.add(() -> {
			if (nextID < 1) {
				nextID = 1024;
			} else
				nextID += 1;
			EntityRegistry.registerModEntity(new ResourceLocation(HTTYMDMod.ID, entityName), entityClass, entityName, nextID, HTTYMDMod.INSTANCE, 50, 2, true);
			addSpawnInfo(HTTYMDMod.ID + ":" + entityName, solidColor, spotColor);
		});
		HTTYMDMod.registerDragonName(entityName);
		/*ItemSpawnEgg egg = (ItemSpawnEgg) new ItemSpawnEgg(entityName, solidColor, spotColor).setCreativeTab(currentTab);
		egg.registerItem();
		ItemRegistry.spawnEggIDMapping.put(nextID, egg);*/
	}

	public static void setCurrentTab(CreativeTabs tab) {
		currentTab = tab;
	}
	public static void registerEnities(){
		register.forEach(Runnable::run);
		register = null;
	}
	private static EntityList.EntityEggInfo addSpawnInfo(String id, int primaryColor, int secondaryColor)
	{
		ResourceLocation resourcelocation = new ResourceLocation(id);
		EntityList.EntityEggInfo egg = new EntityList.EntityEggInfo(resourcelocation, primaryColor, secondaryColor);
		net.minecraftforge.fml.common.registry.EntityEntry entry = net.minecraftforge.fml.common.registry.ForgeRegistries.ENTITIES.getValue(resourcelocation);
		if (entry != null) entry.setEgg(egg);
		return EntityList.ENTITY_EGGS.put(resourcelocation, egg);
	}
}
