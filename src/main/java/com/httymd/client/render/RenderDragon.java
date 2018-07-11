package com.httymd.client.render;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.httymd.HTTYMDMod;
import com.httymd.client.model.ModelDragonOld;
import com.httymd.entity.EntityDragon;

import com.tom.animator.parser.OldModelFormatParser;

@SideOnly(Side.CLIENT)
public class RenderDragon extends RenderLiving {
	private static final String entityTexLoc = HTTYMDMod.ID + ":textures/entities/";

	public RenderDragon(RenderManager m, ModelBase modelbaseIn, float shadowsizeIn) {
		super(m, modelbaseIn, shadowsizeIn);
		if(modelbaseIn instanceof ModelDragonOld){
			ModelDragonOld old = (ModelDragonOld) modelbaseIn;
			OldModelFormatParser.dump(old.head, old.body, old.legs, old.wing);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		try {
			if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7))this.mainModel = mainModel.getClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return this.getEntityTexture((EntityDragon) p_110775_1_);
	}

	protected ResourceLocation getEntityTexture(EntityDragon dragon) {
		return new ResourceLocation(entityTexLoc+EntityList.getEntityString(dragon).replace(HTTYMDMod.ID+".", "")+".png");
	}
}