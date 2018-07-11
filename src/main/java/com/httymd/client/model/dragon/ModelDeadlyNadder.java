package com.httymd.client.model.dragon;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.model.ModelRenderer;

import com.httymd.client.animation.Animation;
import com.httymd.client.model.ModelDragon;

public class ModelDeadlyNadder extends ModelDragon {

	public final ModelRenderer nadder;
	public final ModelRenderer body;
	public final ModelRenderer stomach;
	public final ModelRenderer upperbody;
	public final ModelRenderer tailg1;
	public final ModelRenderer tail1;
	public final ModelRenderer tailg2;
	public final ModelRenderer tail2;
	public final ModelRenderer tailg3;
	public final ModelRenderer tail3;
	public final ModelRenderer tailg4;
	public final ModelRenderer tail4;
	public final ModelRenderer tailg5;
	public final ModelRenderer tail5;
	public final ModelRenderer tailg6;
	public final ModelRenderer tail6;
	public final ModelRenderer spike5;
	public final ModelRenderer spike6;
	public final ModelRenderer spike2;
	public final ModelRenderer spike3;
	public final ModelRenderer LeftWing;
	public final ModelRenderer liftbicep;
	public final ModelRenderer leftwing;
	public final ModelRenderer leftforearm;
	public final ModelRenderer lefthook;
	public final ModelRenderer legs;
	public final ModelRenderer RightLeg;
	public final ModelRenderer rightleg2;
	public final ModelRenderer rightLegp2;
	public final ModelRenderer rightleg1;
	public final ModelRenderer rightclaw1;
	public final ModelRenderer rightclaw2;
	public final ModelRenderer rightclaw3;
	public final ModelRenderer rightclaw4;
	public final ModelRenderer rightclaw5;
	public final ModelRenderer LeftLeg;
	public final ModelRenderer leftleg2;
	public final ModelRenderer leftLegp2;
	public final ModelRenderer leftleg1;
	public final ModelRenderer leftclaw2;
	public final ModelRenderer leftclaw3;
	public final ModelRenderer leftclaw1;
	public final ModelRenderer leftclaw4;
	public final ModelRenderer leftclaw5;
	public final ModelRenderer spike4;
	public final ModelRenderer neck;
	public final ModelRenderer spike1;
	public final ModelRenderer neck2;
	public final ModelRenderer neckp2;
	public final ModelRenderer neck3;
	public final ModelRenderer neckp3;
	public final ModelRenderer neck1;
	public final ModelRenderer head;
	public final ModelRenderer cresthorn11;
	public final ModelRenderer cresthorn10;
	public final ModelRenderer cresthorn8;
	public final ModelRenderer jaw;
	public final ModelRenderer horn2;
	public final ModelRenderer cresthorn3;
	public final ModelRenderer cresthorn4;
	public final ModelRenderer cresthorn2;
	public final ModelRenderer cresthorn1;
	public final ModelRenderer cresthorn7;
	public final ModelRenderer cresthorn5;
	public final ModelRenderer head_main;
	public final ModelRenderer cresthorn9;
	public final ModelRenderer horn4;
	public final ModelRenderer cresthorn6;
	public final ModelRenderer horn1;
	public final ModelRenderer horn3;

	public final Animation idle;
	public final Animation flying;
	public final Animation angry;
	public final Animation sitting;

	public ModelDeadlyNadder() {
		textureWidth = 512;
		textureHeight = 256;

		nadder = new ModelRenderer(model);
		nadder.setRotationPoint(0.0F, 0.0F, 0.0F);
		{
			body = new ModelRenderer(model);
			body.setRotationPoint(0.0F, 0.0F, 0.0F);
			{
				stomach = new ModelRenderer(model, 434, 33);
				stomach.addBox(0.0F, 0.0F, 0.0F, 19, 20, 20);
				stomach.setRotationPoint(-10.0F, -24.0F, -12.0F);
				stomach.setTextureSize(textureWidth, textureHeight);
				stomach.mirror = true;
				setRotation(stomach, -0.17453292649980456F, 0.0F, 0.0F);
				body.addChild(stomach);

				upperbody = new ModelRenderer(model, 463, 73);
				upperbody.addBox(0.0F, 0.0F, 0.0F, 15, 13, 10);
				upperbody.setRotationPoint(-8.0F, -26.0F, -18.0F);
				upperbody.setTextureSize(textureWidth, textureHeight);
				upperbody.mirror = true;
				setRotation(upperbody, -0.4363323338045284F, 0.0F, 0.0F);
				body.addChild(upperbody);

				tailg1 = new ModelRenderer(model);
				tailg1.setRotationPoint(-8.0F, -18.0F, 6.5F);
				{
					tail1 = new ModelRenderer(model, 442, 222);
					tail1.addBox(0.0F, 0.0F, 0.0F, 15, 14, 20);
					tail1.setRotationPoint(0.0F, 0.0F, 0.0F);
					tail1.setTextureSize(textureWidth, textureHeight);
					tail1.mirror = true;
					setRotation(tail1, 0.0F, 0.0F, 0.0F);
					tailg1.addChild(tail1);

					tailg2 = new ModelRenderer(model);
					tailg2.setRotationPoint(2.0F, 1.5F, 16.0F);
					{
						tail2 = new ModelRenderer(model, 380, 226);
						tail2.addBox(0.0F, 0.0F, 0.0F, 11, 10, 20);
						tail2.setRotationPoint(0.0F, 0.0F, 0.0F);
						tail2.setTextureSize(textureWidth, textureHeight);
						tail2.mirror = true;
						setRotation(tail2, 0.0F, 0.0F, 0.0F);
						tailg2.addChild(tail2);

						tailg3 = new ModelRenderer(model);
						tailg3.setRotationPoint(2.0F, 2.0F, 19.5F);
						{
							tail3 = new ModelRenderer(model, 326, 229);
							tail3.addBox(0.0F, 0.0F, 0.0F, 7, 7, 20);
							tail3.setRotationPoint(0.0F, 0.0F, 0.0F);
							tail3.setTextureSize(textureWidth, textureHeight);
							tail3.mirror = true;
							setRotation(tail3, 0.0F, 0.0F, 0.0F);
							tailg3.addChild(tail3);

							tailg4 = new ModelRenderer(model);
							tailg4.setRotationPoint(1.0F, 1.0F, 18.0F);
							{
								tail4 = new ModelRenderer(model, 276, 231);
								tail4.addBox(0.0F, 0.0F, 0.0F, 5, 5, 20);
								tail4.setRotationPoint(0.0F, 0.0F, 0.0F);
								tail4.setTextureSize(textureWidth, textureHeight);
								tail4.mirror = true;
								setRotation(tail4, 0.0F, 0.0F, 0.0F);
								tailg4.addChild(tail4);

								tailg5 = new ModelRenderer(model);
								tailg5.setRotationPoint(1.0F, 1.0F, 19.0F);
								{
									tail5 = new ModelRenderer(model, 230, 233);
									tail5.addBox(0.0F, 0.0F, 0.0F, 3, 3, 20);
									tail5.setRotationPoint(0.0F, 0.0F, 0.0F);
									tail5.setTextureSize(textureWidth, textureHeight);
									tail5.mirror = true;
									setRotation(tail5, 0.0F, 0.0F, 0.0F);
									tailg5.addChild(tail5);

									tailg6 = new ModelRenderer(model);
									tailg6.setRotationPoint(1.0F, 1.0F, 19.5F);
									{
										tail6 = new ModelRenderer(model, 188, 235);
										tail6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 20);
										tail6.setRotationPoint(0.0F, 0.0F, 0.0F);
										tail6.setTextureSize(textureWidth, textureHeight);
										tail6.mirror = true;
										setRotation(tail6, 0.0F, 0.0F, 0.0F);
										tailg6.addChild(tail6);
									}
									tailg5.addChild(tailg6);
								}
								tailg4.addChild(tailg5);
							}
							tailg3.addChild(tailg4);
						}
						tailg2.addChild(tailg3);
					}
					tailg1.addChild(tailg2);

					spike5 = new ModelRenderer(model, 16, 65);
					spike5.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
					spike5.setRotationPoint(7.0F, 0.5F, 5.5F);
					spike5.setTextureSize(textureWidth, textureHeight);
					spike5.mirror = true;
					setRotation(spike5, 0.0F, 0.0F, 0.0F);
					tailg1.addChild(spike5);

					spike6 = new ModelRenderer(model, 20, 67);
					spike6.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
					spike6.setRotationPoint(7.0F, 0.5F, 14.0F);
					spike6.setTextureSize(textureWidth, textureHeight);
					spike6.mirror = true;
					setRotation(spike6, 0.0F, 0.0F, 0.0F);
					tailg1.addChild(spike6);
				}
				body.addChild(tailg1);

				spike2 = new ModelRenderer(model, 4, 64);
				spike2.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1);
				spike2.setRotationPoint(-1.0F, -23.0F, -13.0F);
				spike2.setTextureSize(textureWidth, textureHeight);
				spike2.mirror = true;
				setRotation(spike2, -0.26179939560137916F, 0.0F, 0.0F);
				body.addChild(spike2);

				spike3 = new ModelRenderer(model, 8, 63);
				spike3.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1);
				spike3.setRotationPoint(-1.0F, -22.5F, -6.0F);
				spike3.setTextureSize(textureWidth, textureHeight);
				spike3.mirror = true;
				setRotation(spike3, -0.17453292649980456F, 0.0F, 0.0F);
				body.addChild(spike3);

				LeftWing = new WingRenderer(model);
				LeftWing.setRotationPoint(7.0F, -23.0F, -17.0F);
				{
					liftbicep = new ModelRenderer(model, 0, 106);
					liftbicep.addBox(0.0F, 0.0F, 0.0F, 22, 3, 3);
					liftbicep.setRotationPoint(0.0F, 0.0F, 0.0F);
					liftbicep.setTextureSize(textureWidth, textureHeight);
					liftbicep.mirror = true;
					setRotation(liftbicep, 0.0F, -0.5235987912027583F, 0.0F);
					LeftWing.addChild(liftbicep);

					leftwing = new ModelRenderer(model, 0, 0);
					leftwing.addBox(0.0F, 0.0F, -60.0F, 110, 0, 60);
					leftwing.setRotationPoint(-1.0F, 1.0F, 60.0F);
					leftwing.setTextureSize(textureWidth, textureHeight);
					leftwing.mirror = true;
					setRotation(leftwing, 0.0F, 0.0F, 0.0F);
					LeftWing.addChild(leftwing);

					leftforearm = new ModelRenderer(model, 0, 102);
					leftforearm.addBox(0.0F, 0.0F, -2.0F, 24, 2, 2);
					leftforearm.setRotationPoint(18.0F, 0.0F, 13.0F);
					leftforearm.setTextureSize(textureWidth, textureHeight);
					leftforearm.mirror = true;
					setRotation(leftforearm, 0.0F, 0.6806784145195723F, 0.0F);
					LeftWing.addChild(leftforearm);

					lefthook = new ModelRenderer(model, 0, 100);
					lefthook.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
					lefthook.setRotationPoint(35.0F, 0.5F, -3.5F);
					lefthook.setTextureSize(textureWidth, textureHeight);
					lefthook.mirror = true;
					setRotation(lefthook, 0.0F, 0.0F, 0.0F);
					LeftWing.addChild(lefthook);
				}
				body.addChild(LeftWing);

				legs = new ModelRenderer(model);
				legs.setRotationPoint(0.0F, 0.0F, 0.0F);
				{
					RightLeg = new ModelRenderer(model);
					RightLeg.setRotationPoint(-5.0F, 1.0F, -7.0F);
					{
						rightleg2 = new ModelRenderer(model, 482, 0);
						rightleg2.addBox(-8.0F, 0.0F, 0.0F, 8, 22, 7);
						rightleg2.setRotationPoint(-1.0F, -15.0F, -4.0F);
						rightleg2.setTextureSize(textureWidth, textureHeight);
						rightleg2.mirror = true;
						setRotation(rightleg2, -0.08726646324990228F, 0.0F, 0.0698131717702563F);
						RightLeg.addChild(rightleg2);

						rightLegp2 = new ModelRenderer(model);
						rightLegp2.setRotationPoint(0.0F, 0.0F, 0.0F);
						{
							rightleg1 = new ModelRenderer(model, 442, 0);
							rightleg1.addBox(-12.0F, 0.0F, 0.0F, 10, 23, 10);
							rightleg1.setRotationPoint(0.0F, 0.0F, 0.0F);
							rightleg1.setTextureSize(textureWidth, textureHeight);
							rightleg1.mirror = true;
							setRotation(rightleg1, 0.0F, 0.0F, 0.0F);
							rightLegp2.addChild(rightleg1);

							rightclaw1 = new ModelRenderer(model, 0, 79);
							rightclaw1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 7);
							rightclaw1.setRotationPoint(-14.0F, 21.0F, -6.0F);
							rightclaw1.setTextureSize(textureWidth, textureHeight);
							rightclaw1.mirror = true;
							setRotation(rightclaw1, 0.0F, 0.33161256151996316F, 0.0F);
							rightLegp2.addChild(rightclaw1);

							rightclaw2 = new ModelRenderer(model, 0, 79);
							rightclaw2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 7);
							rightclaw2.setRotationPoint(-1.0F, 21.0F, -6.0F);
							rightclaw2.setTextureSize(textureWidth, textureHeight);
							rightclaw2.mirror = true;
							setRotation(rightclaw2, 0.0F, -0.3490658529996091F, 0.0F);
							rightLegp2.addChild(rightclaw2);

							rightclaw3 = new ModelRenderer(model, 0, 88);
							rightclaw3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 9);
							rightclaw3.setRotationPoint(-10.0F, 20.0F, -8.0F);
							rightclaw3.setTextureSize(textureWidth, textureHeight);
							rightclaw3.mirror = true;
							setRotation(rightclaw3, 0.0F, 0.0698131717702563F, 0.0F);
							rightLegp2.addChild(rightclaw3);

							rightclaw4 = new ModelRenderer(model, 0, 88);
							rightclaw4.addBox(0.0F, 0.0F, 0.0F, 1, 3, 9);
							rightclaw4.setRotationPoint(-5.0F, 20.0F, -8.0F);
							rightclaw4.setTextureSize(textureWidth, textureHeight);
							rightclaw4.mirror = true;
							setRotation(rightclaw4, 0.0F, -0.08726646324990228F, 0.0F);
							rightLegp2.addChild(rightclaw4);

							rightclaw5 = new ModelRenderer(model, 0, 72);
							rightclaw5.addBox(0.0F, 0.0F, 0.0F, 2, 2, 5);
							rightclaw5.setRotationPoint(-8.0F, 15.0F, 10.0F);
							rightclaw5.setTextureSize(textureWidth, textureHeight);
							rightclaw5.mirror = true;
							setRotation(rightclaw5, -0.4363323338045284F, 0.0F, 0.0F);
							rightLegp2.addChild(rightclaw5);
						}
						RightLeg.addChild(rightLegp2);
					}
					legs.addChild(RightLeg);

					LeftLeg = new ModelRenderer(model);
					LeftLeg.setRotationPoint(15.0F, -14.0F, -11.0F);
					{
						leftleg2 = new ModelRenderer(model, 482, 0);
						leftleg2.addBox(-8.0F, 0.0F, 0.0F, 8, 22, 7);
						leftleg2.setRotationPoint(0.0F, 0.0F, 0.0F);
						leftleg2.setTextureSize(textureWidth, textureHeight);
						leftleg2.mirror = true;
						setRotation(leftleg2, -0.08726646324990228F, 0.0F, -0.08726646324990228F);
						LeftLeg.addChild(leftleg2);

						leftLegp2 = new ModelRenderer(model);
						leftLegp2.setRotationPoint(0.0F, 0.0F, 0.0F);
						{
							leftleg1 = new ModelRenderer(model, 442, 0);
							leftleg1.addBox(0.0F, 0.0F, 0.0F, 10, 23, 10);
							leftleg1.setRotationPoint(-7.0F, 15.0F, 4.0F);
							leftleg1.setTextureSize(textureWidth, textureHeight);
							leftleg1.mirror = true;
							setRotation(leftleg1, 0.0F, 0.0F, 0.0F);
							leftLegp2.addChild(leftleg1);

							leftclaw2 = new ModelRenderer(model, 0, 79);
							leftclaw2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 7);
							leftclaw2.setRotationPoint(4.0F, 36.0F, -2.0F);
							leftclaw2.setTextureSize(textureWidth, textureHeight);
							leftclaw2.mirror = true;
							setRotation(leftclaw2, 0.0F, -0.3490658529996091F, 0.0F);
							leftLegp2.addChild(leftclaw2);

							leftclaw3 = new ModelRenderer(model, 0, 88);
							leftclaw3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 9);
							leftclaw3.setRotationPoint(-5.0F, 35.0F, -4.0F);
							leftclaw3.setTextureSize(textureWidth, textureHeight);
							leftclaw3.mirror = true;
							setRotation(leftclaw3, 0.0F, 0.0698131717702563F, 0.0F);
							leftLegp2.addChild(leftclaw3);

							leftclaw1 = new ModelRenderer(model, 0, 79);
							leftclaw1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 7);
							leftclaw1.setRotationPoint(-9.0F, 36.0F, -2.0F);
							leftclaw1.setTextureSize(textureWidth, textureHeight);
							leftclaw1.mirror = true;
							setRotation(leftclaw1, 0.0F, 0.33161256151996316F, 0.0F);
							leftLegp2.addChild(leftclaw1);

							leftclaw4 = new ModelRenderer(model, 0, 88);
							leftclaw4.addBox(0.0F, 0.0F, 0.0F, 1, 3, 9);
							leftclaw4.setRotationPoint(0.0F, 35.0F, -4.0F);
							leftclaw4.setTextureSize(textureWidth, textureHeight);
							leftclaw4.mirror = true;
							setRotation(leftclaw4, 0.0F, -0.08726646324990228F, 0.0F);
							leftLegp2.addChild(leftclaw4);

							leftclaw5 = new ModelRenderer(model, 0, 72);
							leftclaw5.addBox(0.0F, 0.0F, 0.0F, 2, 2, 5);
							leftclaw5.setRotationPoint(-3.0F, 30.0F, 14.0F);
							leftclaw5.setTextureSize(textureWidth, textureHeight);
							leftclaw5.mirror = true;
							setRotation(leftclaw5, -0.4363323338045284F, 0.0F, 0.0F);
							leftLegp2.addChild(leftclaw5);
						}
						LeftLeg.addChild(leftLegp2);
					}
					legs.addChild(LeftLeg);
				}
				body.addChild(legs);

				spike4 = new ModelRenderer(model, 12, 64);
				spike4.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1);
				spike4.setRotationPoint(-1.0F, -21.5F, 2.0F);
				spike4.setTextureSize(textureWidth, textureHeight);
				spike4.mirror = true;
				setRotation(spike4, -0.17453292649980456F, 0.0F, 0.0F);
				body.addChild(spike4);

				neck = new ModelRenderer(model);
				neck.setRotationPoint(-6.0F, -27.0F, -23.0F);
				{
					spike1 = new ModelRenderer(model, 0, 65);
					spike1.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
					spike1.setRotationPoint(5.0F, 0.5F, 2.0F);
					spike1.setTextureSize(textureWidth, textureHeight);
					spike1.mirror = true;
					setRotation(spike1, 0.0F, 0.0F, 0.0F);
					neck.addChild(spike1);

					neck2 = new ModelRenderer(model, 479, 131);
					neck2.addBox(0.0F, 0.0F, 0.0F, 11, 10, 6);
					neck2.setRotationPoint(0.0F, 0.0F, 0.0F);
					neck2.setTextureSize(textureWidth, textureHeight);
					neck2.mirror = true;
					setRotation(neck2, 0.0F, 0.0F, 0.0F);
					neck.addChild(neck2);

					neckp2 = new ModelRenderer(model);
					neckp2.setRotationPoint(1.0F, 0.5F, -7.0F);
					{
						neck3 = new ModelRenderer(model, 477, 114);
						neck3.addBox(0.0F, 0.0F, 0.0F, 9, 8, 9);
						neck3.setRotationPoint(0.0F, 0.0F, 0.0F);
						neck3.setTextureSize(textureWidth, textureHeight);
						neck3.mirror = true;
						setRotation(neck3, 0.0F, 0.0F, 0.0F);
						neckp2.addChild(neck3);

						neckp3 = new ModelRenderer(model);
						neckp3.setRotationPoint(-1.0F, 0.0F, -6.0F);
						{
							neck1 = new ModelRenderer(model, 473, 96);
							neck1.addBox(0.0F, 0.0F, 0.0F, 11, 9, 9);
							neck1.setRotationPoint(0.0F, 0.0F, 0.0F);
							neck1.setTextureSize(textureWidth, textureHeight);
							neck1.mirror = true;
							setRotation(neck1, 0.0F, 0.0F, 0.0F);
							neckp3.addChild(neck1);

							head = new ModelRenderer(model);
							head.setRotationPoint(5.5F, 4.0F, 1.0F);
							{
								cresthorn11 = new ModelRenderer(model, 0, 144);
								cresthorn11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
								cresthorn11.setRotationPoint(5.5F, 0.0F, 0.0F);
								cresthorn11.setTextureSize(textureWidth, textureHeight);
								cresthorn11.mirror = true;
								setRotation(cresthorn11, -0.2268928126420872F, 0.6806784145195723F, 0.0F);
								head.addChild(cresthorn11);

								cresthorn10 = new ModelRenderer(model, 0, 144);
								cresthorn10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
								cresthorn10.setRotationPoint(-6.5F, 0.0F, 0.0F);
								cresthorn10.setTextureSize(textureWidth, textureHeight);
								cresthorn10.mirror = true;
								setRotation(cresthorn10, -0.2268928126420872F, -0.6981317059992183F, 0.0F);
								head.addChild(cresthorn10);

								cresthorn8 = new ModelRenderer(model, 0, 149);
								cresthorn8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 8);
								cresthorn8.setRotationPoint(-6.5F, -2.0F, -1.0F);
								cresthorn8.setTextureSize(textureWidth, textureHeight);
								cresthorn8.mirror = true;
								setRotation(cresthorn8, -0.08726646324990228F, -0.6108652486009883F, 0.0F);
								head.addChild(cresthorn8);

								jaw = new ModelRenderer(model, 62, 227);
								jaw.addBox(-7.0F, 0.0F, -19.0F, 14, 10, 19);
								jaw.setRotationPoint(0.0F, 0.0F, 0.0F);
								jaw.setTextureSize(textureWidth, textureHeight);
								jaw.mirror = true;
								setRotation(jaw, 0.0F, 0.0F, 0.0F);
								head.addChild(jaw);

								horn2 = new ModelRenderer(model, 0, 124);
								horn2.addBox(0.0F, -4.0F, -3.0F, 3, 5, 3);
								horn2.setRotationPoint(-1.5F, -8.0F, -14.0F);
								horn2.setTextureSize(textureWidth, textureHeight);
								horn2.mirror = true;
								setRotation(horn2, -0.6108652486009883F, 0.0F, 0.0F);
								head.addChild(horn2);

								cresthorn3 = new ModelRenderer(model, 0, 188);
								cresthorn3.addBox(-2.0F, 0.0F, 0.0F, 2, 2, 18);
								cresthorn3.setRotationPoint(4.5F, -7.0F, -2.0F);
								cresthorn3.setTextureSize(textureWidth, textureHeight);
								cresthorn3.mirror = true;
								setRotation(cresthorn3, 0.4188790423248824F, 0.0698131717702563F, 0.0F);
								head.addChild(cresthorn3);

								cresthorn4 = new ModelRenderer(model, 0, 172);
								cresthorn4.addBox(-2.0F, 0.0F, 0.0F, 2, 2, 14);
								cresthorn4.setRotationPoint(5.5F, -7.0F, -2.0F);
								cresthorn4.setTextureSize(textureWidth, textureHeight);
								cresthorn4.mirror = true;
								setRotation(cresthorn4, 0.0698131717702563F, 0.33161256151996316F, 0.0F);
								head.addChild(cresthorn4);

								cresthorn2 = new ModelRenderer(model, 0, 188);
								cresthorn2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 18);
								cresthorn2.setRotationPoint(-4.5F, -7.0F, -2.0F);
								cresthorn2.setTextureSize(textureWidth, textureHeight);
								cresthorn2.mirror = true;
								setRotation(cresthorn2, 0.4188790423248824F, -0.08726646324990228F, 0.0F);
								head.addChild(cresthorn2);

								cresthorn1 = new ModelRenderer(model, 0, 208);
								cresthorn1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 20);
								cresthorn1.setRotationPoint(-1.0F, -7.0F, -2.0F);
								cresthorn1.setTextureSize(textureWidth, textureHeight);
								cresthorn1.mirror = true;
								setRotation(cresthorn1, 0.5235987912027583F, 0.0F, 0.0F);
								head.addChild(cresthorn1);

								cresthorn7 = new ModelRenderer(model, 0, 159);
								cresthorn7.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 12);
								cresthorn7.setRotationPoint(6.5F, -4.0F, -1.0F);
								cresthorn7.setTextureSize(textureWidth, textureHeight);
								cresthorn7.mirror = true;
								setRotation(cresthorn7, 0.0F, 0.33161256151996316F, 0.0F);
								head.addChild(cresthorn7);

								cresthorn5 = new ModelRenderer(model, 0, 172);
								cresthorn5.addBox(0.0F, 0.0F, 0.0F, 2, 2, 14);
								cresthorn5.setRotationPoint(-5.5F, -7.0F, -2.0F);
								cresthorn5.setTextureSize(textureWidth, textureHeight);
								cresthorn5.mirror = true;
								setRotation(cresthorn5, 0.0698131717702563F, -0.3490658529996091F, 0.0F);
								head.addChild(cresthorn5);

								head_main = new ModelRenderer(model, 0, 230);
								head_main.addBox(-6.5F, -8.0F, -18.0F, 13, 8, 18);
								head_main.setRotationPoint(0.0F, 0.0F, 0.0F);
								head_main.setTextureSize(textureWidth, textureHeight);
								head_main.mirror = true;
								setRotation(head_main, 0.0698131717702563F, 0.0F, 0.0F);
								head.addChild(head_main);

								cresthorn9 = new ModelRenderer(model, 0, 150);
								cresthorn9.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 8);
								cresthorn9.setRotationPoint(6.5F, -2.0F, -1.0F);
								cresthorn9.setTextureSize(textureWidth, textureHeight);
								cresthorn9.mirror = true;
								setRotation(cresthorn9, -0.08726646324990228F, 0.5934119571213423F, 0.0F);
								head.addChild(cresthorn9);

								horn4 = new ModelRenderer(model, 0, 112);
								horn4.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
								horn4.setRotationPoint(-0.5F, -17.0F, -7.0F);
								horn4.setTextureSize(textureWidth, textureHeight);
								horn4.mirror = true;
								setRotation(horn4, -1.0471975824055166F, 0.0F, 0.0F);
								head.addChild(horn4);

								cresthorn6 = new ModelRenderer(model, 0, 159);
								cresthorn6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 12);
								cresthorn6.setRotationPoint(-6.5F, -4.0F, -1.0F);
								cresthorn6.setTextureSize(textureWidth, textureHeight);
								cresthorn6.mirror = true;
								setRotation(cresthorn6, 0.0F, -0.4363323338045284F, 0.0F);
								head.addChild(cresthorn6);

								horn1 = new ModelRenderer(model, 0, 132);
								horn1.addBox(0.0F, -3.0F, 0.0F, 4, 3, 9);
								horn1.setRotationPoint(-2.0F, -6.0F, -18.0F);
								horn1.setTextureSize(textureWidth, textureHeight);
								horn1.mirror = true;
								setRotation(horn1, -0.26179939560137916F, 0.0F, 0.0F);
								head.addChild(horn1);

								horn3 = new ModelRenderer(model, 0, 117);
								horn3.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2);
								horn3.setRotationPoint(-1.0F, -16.0F, -10.0F);
								horn3.setTextureSize(textureWidth, textureHeight);
								horn3.mirror = true;
								setRotation(horn3, -0.8726646676090568F, 0.0F, 0.0F);
								head.addChild(horn3);
							}
							neckp3.addChild(head);
						}
						neckp2.addChild(neckp3);
					}
					neck.addChild(neckp2);
				}
				body.addChild(neck);
			}
			nadder.addChild(body);
		}
		{
			ModelRenderer[] components = { head, body, tailg1, tail1, LeftWing, stomach, neck, upperbody, tailg2, tailg3, jaw, cresthorn1, legs, rightLegp2, leftLegp2, tailg4, tailg5, tailg6, };
			int duration = 1000;

			float[][][] data = {
					{{5.5f, 5.5f, },{4.0f, 4.0f, },{1.0f, 1.0f, },{30.0f, 32.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//head
					{{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//body
					{{-8.0f, -8.0f, },{-18.0f, -18.0f, },{6.5f, 6.5f, },{-8.0f, -6.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//tailg1
					{{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//tail1
					{{7.0f, 7.0f, },{-23.0f, -23.0f, },{-17.0f, -17.0f, },{-50.0f, -50.0f, },{-79.0f, -80.0f, },{-50.0f, -50.0f, },},//LeftWing
					{{-10.0f, -10.0f, },{-24.0f, -24.5f, },{-12.0f, -12.0f, },{-10.0f, -10.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//stomach
					{{-6.0f, -6.0f, },{-27.0f, -27.0f, },{-23.0f, -23.0f, },{-30.0f, -29.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//neck
					{{-8.0f, -8.0f, },{-26.0f, -26.5f, },{-18.0f, -18.0f, },{-25.0f, -25.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//upperbody
					{{2.0f, 2.0f, },{1.5f, 1.5f, },{16.0f, 16.0f, },{5.0f, 5.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//tailg2
					{{2.0f, 2.0f, },{2.0f, 2.0f, },{19.5f, 19.5f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//tailg3
					{{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//jaw
					{{-1.0f, -1.0f, },{-7.0f, -7.0f, },{-2.0f, -2.0f, },{30.0f, 30.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//cresthorn1
					{{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//legs
					{{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//rightLegp2
					{{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//leftLegp2
					{{1.0f, 1.0f, },{1.0f, 1.0f, },{18.0f, 18.0f, },{5.0f, 5.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//tailg4
					{{1.0f, 1.0f, },{1.0f, 1.0f, },{19.0f, 19.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//tailg5
					{{1.0f, 1.0f, },{1.0f, 1.0f, },{19.5f, 19.5f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//tailg6
			};

			idle = new Animation(components, data, duration);
		}
		{
			ModelRenderer[] components = { LeftWing, neck, tailg1, body, };
			int duration = 1000;

			float[][][] data = {
					{{7.0f, 7.0f, 7.0f, 7.0f, },{-23.0f, -23.0f, -23.0f, -23.0f, },{-17.0f, -17.0f, -17.0f, -17.0f, },{0.0f, 0.0f, 0.0f, 0.0f, },{0.0f, 0.0f, 0.0f, 0.0f, },{0.0f, 30.0f, 0.0f, -30.0f, },},//LeftWing
					{{-6.0f, -6.0f, -6.0f, -6.0f, },{-27.0f, -27.0f, -27.0f, -27.0f, },{-23.0f, -23.0f, -23.0f, -23.0f, },{-30.0f, -32.0f, -30.0f, -28.0f, },{0.0f, 0.0f, 0.0f, 0.0f, },{0.0f, 0.0f, 0.0f, 0.0f, },},//neck
					{{-8.0f, -8.0f, -8.0f, -8.0f, },{-18.0f, -18.0f, -18.0f, -18.0f, },{6.5f, 6.5f, 6.5f, 6.5f, },{-8.0f, -5.0f, -8.0f, -11.0f, },{0.0f, 0.0f, 0.0f, 0.0f, },{0.0f, 0.0f, 0.0f, 0.0f, },},//tailg1
					{{0.0f, 0.0f, 0.0f, 0.0f, },{0.0f, -0.5f, 0.0f, 0.5f, },{0.0f, 0.0f, 0.0f, 0.0f, },{0.0f, 0.0f, 0.0f, 0.0f, },{0.0f, 0.0f, 0.0f, 0.0f, },{0.0f, 0.0f, 0.0f, 0.0f, },},//body
			};

			flying = new Animation(components, data, duration);
		}
		{
			ModelRenderer[] components = { spike2, spike3, spike4, spike5, spike6, tailg3, jaw, spike1, body, };
			int duration = 1000;

			float[][][] data = {
					{{-1.0f, },{-31.0f, },{-13.0f, },{-15.0f, },{0.0f, },{0.0f, },},//spike2
					{{-1.0f, },{-31.0f, },{-6.0f, },{-10.0f, },{0.0f, },{0.0f, },},//spike3
					{{-1.0f, },{-28.0f, },{2.0f, },{-10.0f, },{0.0f, },{0.0f, },},//spike4
					{{7.0f, },{-5.5f, },{5.5f, },{0.0f, },{0.0f, },{0.0f, },},//spike5
					{{7.0f, },{-3.5f, },{14.0f, },{0.0f, },{0.0f, },{0.0f, },},//spike6
					{{2.0f, },{2.0f, },{19.5f, },{0.0f, },{0.0f, },{0.0f, },},//tailg3
					{{0.0f, },{0.0f, },{0.0f, },{20.0f, },{0.0f, },{0.0f, },},//jaw
					{{5.0f, },{-5.5f, },{2.0f, },{0.0f, },{0.0f, },{0.0f, },},//spike1
					{{0.0f, },{0.0f, },{0.0f, },{0.0f, },{0.0f, },{0.0f, },},//body
			};

			angry = new Animation(components, data, duration);
		}
		{
			ModelRenderer[] components = { body, legs, rightLegp2, leftLegp2, leftclaw5, rightclaw5, tailg1, tailg2, tailg3, tailg4, tailg5, tailg6, tail2, neck, neckp2, neckp3, head, LeftWing, upperbody, };
			int duration = 1000;

			float[][][] data = {
					{{0.0f, 0.0f, },{21.0f, 21.0f, },{0.0f, 0.0f, },{-30.0f, -30.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//body
					{{0.0f, 0.0f, },{0.0f, 0.0f, },{-11.5f, -11.5f, },{-60.0f, -60.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//legs
					{{0.0f, 0.0f, },{5.5f, 5.5f, },{-3.5f, -3.5f, },{20.0f, 20.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//rightLegp2
					{{0.0f, 0.0f, },{5.5f, 5.5f, },{-8.0f, -8.0f, },{20.0f, 20.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//leftLegp2
					{{-3.0f, -3.0f, },{30.0f, 30.0f, },{14.0f, 14.0f, },{-91.0f, -91.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//leftclaw5
					{{-8.0f, -8.0f, },{15.0f, 15.0f, },{10.0f, 10.0f, },{-91.0f, -91.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//rightclaw5
					{{-8.0f, -8.0f, },{-18.0f, -18.0f, },{0.5f, 0.5f, },{10.0f, 10.0f, },{-5.0f, -5.0f, },{0.0f, 0.0f, },},//tailg1
					{{2.0f, 2.0f, },{1.5f, 1.5f, },{16.0f, 16.0f, },{13.0f, 13.0f, },{-3.0f, -3.0f, },{0.0f, 0.0f, },},//tailg2
					{{2.0f, 2.0f, },{2.0f, 2.0f, },{18.5f, 18.5f, },{6.0f, 6.0f, },{-3.0f, -3.0f, },{0.0f, 0.0f, },},//tailg3
					{{1.0f, 1.0f, },{1.0f, 1.0f, },{18.0f, 18.0f, },{5.0f, 5.0f, },{-7.0f, -7.0f, },{0.0f, 0.0f, },},//tailg4
					{{1.0f, 1.0f, },{1.0f, 1.0f, },{19.0f, 19.0f, },{0.0f, 0.0f, },{-9.0f, -9.0f, },{0.0f, 0.0f, },},//tailg5
					{{1.0f, 1.0f, },{1.0f, 1.0f, },{19.5f, 19.5f, },{0.0f, 0.0f, },{-8.0f, -8.0f, },{0.0f, 0.0f, },},//tailg6
					{{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//tail2
					{{-6.0f, -6.0f, },{-27.0f, -27.0f, },{-23.0f, -23.0f, },{-20.0f, -19.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//neck
					{{1.0f, 1.0f, },{0.5f, 0.5f, },{-7.0f, -7.0f, },{-1.0f, -1.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//neckp2
					{{-1.0f, -1.0f, },{0.0f, 0.0f, },{-6.0f, -6.0f, },{9.0f, 9.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//neckp3
					{{5.5f, 5.5f, },{3.0f, 3.0f, },{1.5f, 1.5f, },{41.0f, 41.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//head
					{{7.0f, 7.0f, },{-23.0f, -23.0f, },{-17.0f, -17.0f, },{-12.0f, -11.0f, },{-42.0f, -42.0f, },{-70.0f, -70.0f, },},//LeftWing
					{{-8.0f, -8.0f, },{-26.0f, -26.0f, },{-18.0f, -18.0f, },{-25.0f, -24.0f, },{0.0f, 0.0f, },{0.0f, 0.0f, },},//upperbody
			};

			sitting = new Animation(components, data, duration);
		}
	}

	@Override
	public ModelRenderer getRoot() {
		return nadder;
	}
	@Override
	public ModelRenderer getHead() {
		return head;
	}
	@Override
	public List<ModelRenderer> getLegs() {
		List<ModelRenderer> legs = new ArrayList<>();
		legs.add(RightLeg);
		legs.add(LeftLeg);
		return legs;
	}
	@Override
	public ModelRenderer getWing() {
		return LeftWing;
	}
	@Override
	public Animation getIdle() {
		return idle;
	}
	@Override
	public Animation getFlying() {
		return flying;
	}

	@Override
	public Animation getSitting() {
		return sitting;
	}
}