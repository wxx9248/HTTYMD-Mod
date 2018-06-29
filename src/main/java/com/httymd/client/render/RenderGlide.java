package com.httymd.client.render;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

import com.httymd.client.model.ModelGlideSuit;

public class RenderGlide extends RenderPlayer {

	public static class ModelGlide extends ModelPlayer {

		public ModelGlide(float scale, boolean b1) {
			super(scale, false);
			//heldItemLeft = 0;
			//heldItemRight = 0;
			isSneak = false;
			//aimedBow = false;
			if(b1){
				textureHeight = 32;
				this.bipedHead = new ModelRenderer(this, 0, 0);
				this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale);
				this.bipedHead.setRotationPoint(0.0F, 0.0F + 0, 0.0F);
				this.bipedHeadwear = new ModelRenderer(this, 32, 0);
				this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale + 0.5F);
				this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + 0, 0.0F);
				this.bipedBody = new ModelRenderer(this, 16, 16);
				this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scale);
				this.bipedBody.setRotationPoint(0.0F, 0.0F + 0, 0.0F);
				this.bipedRightArm = new ModelRenderer(this, 40, 16);
				this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, scale);
				this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + 0, 0.0F);
				this.bipedLeftArm = new ModelRenderer(this, 40, 16);
				this.bipedLeftArm.mirror = true;
				this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, scale);
				this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + 0, 0.0F);
				this.bipedRightLeg = new ModelRenderer(this, 0, 16);
				this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
				this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + 0, 0.0F);
				this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
				this.bipedLeftLeg.mirror = true;
				this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
				this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F + 0, 0.0F);
			}
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			this.isSneak = false;
			final float headAngleAligner = -75.0f;
			//Minecraft mc = Minecraft.getMinecraft();
			//int bound = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
			//System.out.println(bound);
			//GL11.glPushMatrix();
			//if(bound == 101)
			//if(bound == 118)GL11.glTranslated(0, 2.5 - mc.world.getTotalWorldTime() / 10f % 3, 0);
			//GL11.glTranslated(0, 3, 0);
			//bipedHead.render(f5);
			//GL11.glPopMatrix();
			//if(bound != 112)
			super.render(entity, f, f1, f2, f3, f4 + headAngleAligner, f5);
		}

		@Override
		public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_,
				float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
			this.isSneak = false;
			// Prevents all alternate arm angles
			super.setRotationAngles(0.0F, 0.0F, 0.0F, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
			AbstractClientPlayer pl = (AbstractClientPlayer) p_78087_7_;
			float armBendAngle = (float) (Math.PI / 180 * (pl.isSprinting() ? 25 : 45));

			bipedRightArm.rotateAngleZ = armBendAngle;
			bipedLeftArm.rotateAngleZ = -armBendAngle;
			// Prevents any other leg movement
			bipedRightLeg.rotateAngleX = 0;
			bipedLeftLeg.rotateAngleX = 0;
			if (pl.moveForward <= 0) {
				float legBendAngle = (float) (Math.PI / 180 * 30);
				bipedRightLeg.rotateAngleZ = legBendAngle;
				bipedLeftLeg.rotateAngleZ = -legBendAngle;
			} else {
				bipedRightLeg.rotateAngleZ = 0;
				bipedLeftLeg.rotateAngleZ = 0;
			}
		}
	}

	public RenderGlide() {
		super(Minecraft.getMinecraft().getRenderManager());
		if (mainModel instanceof ModelBiped) {
			mainModel = new ModelGlide(0, false);
			LayerBipedArmor l = getArmorLayer();
			ModelBiped modelArmor;
			try {
				setField(LayerArmorBase.class, v -> {
					if(!(v instanceof ModelBase))return false;
					ModelBase b = (ModelBase) v;
					return l.getModelFromSlot(EntityEquipmentSlot.HEAD) == b;
				}, l, modelArmor = new ModelGlide(0, true));
				modelArmor.bipedRightArm.mirror = true;
				modelArmor.bipedRightLeg.mirror = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			//modelArmorChestplate = new ModelGlide(0);
			//modelArmorChestplate.bipedRightArm.mirror = true;
			//modelArmorChestplate.bipedRightLeg.mirror = true;
		}
	}

	@SuppressWarnings("rawtypes")
	public static void setField(Class clazz, Predicate<Object> insChecker, Object ins, Object value) throws Exception {
		for (Field f : clazz.getDeclaredFields()) {
			f.setAccessible(true);
			Object i = f.get(ins);
			if (insChecker.test(i)) {
				f.set(ins, value);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private LayerBipedArmor getArmorLayer(){
		for (int i = 0;i < layerRenderers.size();i++) {
			LayerRenderer e = layerRenderers.get(i);
			if(e.getClass() == LayerBipedArmor.class)return (LayerBipedArmor) e;
		}
		return null;
	}

	// TODO what does this method even do? is it exactly the same as
	// super.shouldRenderPass()?
	/*@Override
	protected int shouldRenderPass(AbstractClientPlayer ply, int slot, float p_77032_3_) {
		int result = super.shouldRenderPass(ply, slot, p_77032_3_);
		ItemStack s = ply.getCurrentArmor(3 - slot);

		if (s != null && s.getItem() instanceof ItemGlideArmor && this.modelArmor instanceof ModelGlideSuit) {
			this.bindTexture(RenderBiped.getArmorResource(ply, s, 5, null));
			ModelGlideSuit modelArmor = slot == 2 ? (ModelGlideSuit) this.modelArmor
					: (ModelGlideSuit) this.modelArmorChestplate;
			modelArmor.bipedHead.showModel = slot == 0;
			modelArmor.bipedHeadwear.showModel = slot == 0;
			modelArmor.bipedBody.showModel = slot == 1 || slot == 2;
			modelArmor.bipedRightArm.showModel = slot == 1;
			modelArmor.bipedLeftArm.showModel = slot == 1;
			modelArmor.bipedRightLeg.showModel = slot == 2 || slot == 3;
			modelArmor.bipedLeftLeg.showModel = slot == 2 || slot == 3;
			modelArmor.LeftWing.showModel = slot == 1;
			modelArmor.RightWing.showModel = slot == 1;
			modelArmor = (ModelGlideSuit) ForgeHooksClient.getArmorModel(ply, s, slot, modelArmor);
			modelArmor.onGround = this.mainModel.onGround;
			modelArmor.isRiding = this.mainModel.isRiding;
			modelArmor.isChild = this.mainModel.isChild;

			// Move outside if to allow for more then just CLOTH
			int j = ((ItemArmor) s.getItem()).getColor(s);
			if (j != -1) {
				float f1 = (float) (j >> 16 & 255) / 255.0F;
				float f2 = (float) (j >> 8 & 255) / 255.0F;
				float f3 = (float) (j & 255) / 255.0F;
				GL11.glColor3f(f1, f2, f3);

				if (s.isItemEnchanted()) {
					return 31;
				}

				return 16;
			}

			GL11.glColor3f(1.0F, 1.0F, 1.0F);

			if (s.isItemEnchanted()) {
				return 15;
			}

			return 1;
		}
		return result;
	}*/

	@Override
	public void doRender(AbstractClientPlayer player, double x, double y, double z,
			float entityYaw, float partialTicks) {
		//Minecraft mc = Minecraft.getMinecraft();
		float pitchTiltAngle = -90;
		if (player.moveForward < 0)
			pitchTiltAngle = -80;
		if (player.moveForward > 0){
			if(player.isSprinting())
				pitchTiltAngle = -110;
			else
				pitchTiltAngle = -100;
		}

		double playerYawSin = Math.sin(player.rotationYaw / 180 * Math.PI);
		double playerYawCos = Math.cos(player.rotationYaw / 180 * Math.PI);

		GL11.glPushMatrix();
		GL11.glTranslated(0, .8, 0);
		if(player.isSneaking())GL11.glTranslated(0, -.08d, 0);
		/*double d = 4;
		GL11.glTranslated(0, -2, -2);
		GL11.glScaled(d, d, d);*/
		GL11.glRotated(pitchTiltAngle, -playerYawCos, 0, -playerYawSin);
		float r = interpolateRotation(player.prevRenderYawOffset % 360, player.renderYawOffset, partialTicks);
		/*GL11.glTranslated(x, y+2, z);
		int i = (int) ((mc.world.getTotalWorldTime()) % 360) * 5;
		GL11.glRotated(i, 0, 0, 1);*/
		//GL11.glRotated(90, 0, 0, 1);
		if(player.isSneaking())GL11.glTranslated(0, 0.5D, 0);
		GL11.glTranslated(0, -2.8, 0);
		super.doRender(player, x, y, z, entityYaw, partialTicks);
		GL11.glRotated(-r, 0, 1, 0);
		GL11.glTranslated(0, player.isSneaking() ? 1.8 : 2.3, 0);
		GL11.glScaled(1, 1, -1);
		try {
			this.renderWings(player, 0.045F);
		} catch (Exception e) {
			e.printStackTrace();
		}
		GL11.glPopMatrix();
	}

	public void renderWings(Entity e, float f5) throws Exception {
		ModelGlideSuit m = new ModelGlideSuit(f5);
		/*String textureResource = null;
		ItemStack stack = null;
		if (e instanceof EntityLivingBase) {
			/*for (int i = 0; i < EnumArmorType.values().length; i++) {
				// TODO is it really (3 - i) and not (i + 1)? 0: Tool in
				// Hand; 1-4: Armor
				stack = ((EntityLivingBase) e).getItemStackFromSlot(EnumArmorType.VALUES[i].slot);
				if (stack != null && stack.getItem() instanceof ItemArmor)
					break;
				else
					stack = null;
			}*/
		/*stack = ((EntityLivingBase) e).getItemStackFromSlot(EnumArmorType.LEGGINGS.slot);
			if (stack != null)
				textureResource = ((ItemArmor) stack.getItem()).getArmorTexture(stack, e, EnumArmorType.LEGGINGS.slot, null);
		}
		if (textureResource != null) {*/
		this.bindTexture(new ResourceLocation("httymd:textures/armor/gsuit_fins.png"));
		m.renderWings(e, f5);
		//}
	}
}
