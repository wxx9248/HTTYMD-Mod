package com.httymd.item.recipe;

import java.util.Collection;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.httymd.item.registry.ItemRegistry;
import com.httymd.item.util.EnumFoodType;

public class RecipeRegistry {

	public static void init() {
		/*addRecipe(new ItemStack(ItemRegistry.daggerWood),
				"#", "|", '|', "stickWood", '#', "plankWood");
		addRecipe(new ItemStack(ItemRegistry.daggerStone),
				"#", "|", '|', "stickWood", '#', "cobblestone");
		addRecipe(new ItemStack(ItemRegistry.daggerIron),
				"#", "|", '|', "stickWood", '#', "ingotIron");
		addRecipe(new ItemStack(ItemRegistry.daggerGold),
				"#", "|", '|', "stickWood", '#', "ingotGold");
		addRecipe(new ItemStack(ItemRegistry.daggerDiam),
				"#", "|", '|', "stickWood", '#', "gemDiamond");
		addRecipe(new ItemStack(ItemRegistry.daggerGron),
				"#", "|", '|', "stickWood", '#', ItemRegistry.gronkleIronIngot);

		addRecipe(new ItemStack(ItemRegistry.clubWood),
				"#|#", " | ", " | ", '|', "stickWood", '#', "plankWood");
		addRecipe(new ItemStack(ItemRegistry.clubStone),
				"#|#", " | ", " | ", '|', "stickWood", '#', "cobblestone");
		addRecipe(new ItemStack(ItemRegistry.clubIron),
				"#|#", " | ", " | ", '|', "stickWood", '#', "ingotIron");
		addRecipe(new ItemStack(ItemRegistry.clubGold),
				"#|#", " | ", " | ", '|', "stickWood", '#', "ingotGold");
		addRecipe(new ItemStack(ItemRegistry.clubDiam),
				"#|#", " | ", " | ", '|', "stickWood", '#', "gemDiamond");
		addRecipe(new ItemStack(ItemRegistry.clubGron),
				"#|#", " | ", " | ", '|', "stickWood", '#', ItemRegistry.gronkleIronIngot);

		addRecipe(new ItemStack(ItemRegistry.maceWood),
				" # ", "#|#", " | ", '|', "stickWood", '#', "plankWood");
		addRecipe(new ItemStack(ItemRegistry.maceStone),
				" # ", "#|#", " | ", '|', "stickWood", '#', "cobblestone");
		addRecipe(new ItemStack(ItemRegistry.maceIron),
				" # ", "#|#", " | ", '|', "stickWood", '#', "ingotIron");
		addRecipe(new ItemStack(ItemRegistry.maceGold),
				" # ", "#|#", " | ", '|', "stickWood", '#', "ingotGold");
		addRecipe(new ItemStack(ItemRegistry.maceDiam),
				" # ", "#|#", " | ", '|', "stickWood", '#', "gemDiamond");
		addRecipe(new ItemStack(ItemRegistry.maceGron),
				" # ", "#|#", " | ", '|', "stickWood", '#', ItemRegistry.gronkleIronIngot);

		addRecipe(new ItemStack(ItemRegistry.hammerWood),
				"###", "#|#", " | ", '|', "stickWood", '#', "plankWood");
		addRecipe(new ItemStack(ItemRegistry.hammerStone),
				"###", "#|#", " | ", '|', "stickWood", '#', "cobblestone");
		addRecipe(new ItemStack(ItemRegistry.hammerIron),
				"###", "#|#", " | ", '|', "stickWood", '#', "ingotIron");
		addRecipe(new ItemStack(ItemRegistry.hammerGold),
				"###", "#|#", " | ", '|', "stickWood", '#', "ingotGold");
		addRecipe(new ItemStack(ItemRegistry.hammerDiam),
				"###", "#|#", " | ", '|', "stickWood", '#', "gemDiamond");
		addRecipe(new ItemStack(ItemRegistry.hammerGron),
				"###", "#|#", " | ", '|', "stickWood", '#', ItemRegistry.gronkleIronIngot);

		addRecipe(new ItemStack(ItemRegistry.waraxeWood),
				"#|#", "#|#", " | ", '|', "stickWood", '#', "plankWood");
		addRecipe(new ItemStack(ItemRegistry.waraxeStone),
				"#|#", "#|#", " | ", '|', "stickWood", '#', "cobblestone");
		addRecipe(new ItemStack(ItemRegistry.waraxeIron),
				"#|#", "#|#", " | ", '|', "stickWood", '#', "ingotIron");
		addRecipe(new ItemStack(ItemRegistry.waraxeGold),
				"#|#", "#|#", " | ", '|', "stickWood", '#', "ingotGold");
		addRecipe(new ItemStack(ItemRegistry.waraxeDiam),
				"#|#", "#|#", " | ", '|', "stickWood", '#', "gemDiamond");
		addRecipe(new ItemStack(ItemRegistry.waraxeGron),
				"#|#", "#|#", " | ", '|', "stickWood", '#', ItemRegistry.gronkleIronIngot);

		addRecipe(new ItemStack(ItemRegistry.shieldWood),
				"###", "$#$", "###", '#', "plankWood", '$', "stickWood");
		addRecipe(new ItemStack(ItemRegistry.shieldStone),
				"###", "$#$", "###", '#', "cobblestone", '$', "stickWood");
		addRecipe(new ItemStack(ItemRegistry.shieldIron),
				"###", "$#$", "###", '#', "ingotIron", '$', "stickWood");
		addRecipe(new ItemStack(ItemRegistry.shieldGold),
				"###", "$#$", "###", '#', "ingotGold", '$', "stickWood");
		addRecipe(new ItemStack(ItemRegistry.shieldDiam),
				"###", "$#$", "###", '#', "gemDiamond", '$', "stickWood");
		addRecipe(new ItemStack(ItemRegistry.shieldGron),
				"###", "$#$", "###", '#', ItemRegistry.gronkleIronIngot, '$', "stickWood");

		addRecipe(new ItemStack(ItemRegistry.swordGron),
				"#", "#", "|", '#', ItemRegistry.gronkleIronIngot, '|', "stickWood");
		addRecipe(new ItemStack(ItemRegistry.axeGron),
				"##", "#|", " |", '#', ItemRegistry.gronkleIronIngot, '|', "stickWood");
		addRecipe(new ItemStack(ItemRegistry.pickaxeGron),
				"###", " | ", " | ", '#', ItemRegistry.gronkleIronIngot, '|', "stickWood");
		addRecipe(new ItemStack(ItemRegistry.shovelGron),
				"#", "|", "|", '#', ItemRegistry.gronkleIronIngot, '|', "stickWood");
		addRecipe(new ItemStack(ItemRegistry.hoeGron),
				"##", " |", " |", '#', ItemRegistry.gronkleIronIngot, '|', "stickWood");

		addRecipe(new ItemStack(ItemRegistry.crossbow),
				"S#S", "III", '#', Items.BOW, 'S', "string", 'I', "ingotIron");

		addRecipe(new ItemStack(ItemRegistry.glideSuit[0]),
				"IWI", "LIL", 'I', "ingotIron", 'W', Blocks.WOOL, 'L', "leather");
		addRecipe(new ItemStack(ItemRegistry.glideSuit[1]),
				"LWL", "#L#", "LLL", 'L', "leather", 'W', Blocks.WOOL, '#', ItemRegistry.wing);
		addRecipe(new ItemStack(ItemRegistry.glideSuit[2]),
				"LLL", "I I", "L L", 'L', "leather", 'I', "ingotIron");
		addRecipe(new ItemStack(ItemRegistry.glideSuit[3]),
				"I I", "L L", 'I', "ingotIron", 'L', "leather");

		addRecipe(new ItemStack(ItemRegistry.wing),
				"#I#", "I#I", "III", '#', "leather", 'I', "ingotIron");

		addRecipe(new ItemStack(ItemRegistry.flameSword),
				"S", "#", 'S', Items.IRON_SWORD, '#', Items.COAL);

		addRecipe(new ItemStack(BlockRegistry.gronkleIronBlock),
				"III", "III", "III", 'I', ItemRegistry.gronkleIronIngot);/*
		addShapelessRecipe(new ItemStack(ItemRegistry.gronkleIronIngot, 9), BlockRegistry.gronkleIronBlock);*/

		/*
		 * ItemStack stack = new ItemStack(ItemRegistry.zippleGasContainer);
		 * ItemContainer.setProduceHeld(stack, 15.0F);
		 * addRecipe(stack, "##", '#', Blocks.dirt);
		 */

		// addRecipe(ItemRegistry.zippleGasContainer.getEmptyContainer(),
		// "I#I", "I#I", "I#I", 'I', "ingotIron", '#',Blocks.glass_pane);
		// addRecipe(ItemRegistry.nightmareSalivaContainer.getEmptyContainer(),
		// "I-I", "I I", "III", 'I', "ingotIron", '-', Blocks.trapdoor);*/
	}
	private static void addRecipe(ItemStack stack, Object... objects){
		try {
			Class.forName("com.tom.api.recipes.RecipeHelper").getMethod("addRecipe", ItemStack.class, Object[].class).invoke(null, stack, objects);
		} catch (Exception e) {
		}
	}
	/**
	 * Retrieves food array based on {@link EnumFoodType}
	 */
	private static Item[] getFoodArrByEnum(EnumFoodType type) {
		Collection<Item> list = ItemRegistry.foods.get(type);
		return list.toArray(new Item[list.size()]);
	}
}
