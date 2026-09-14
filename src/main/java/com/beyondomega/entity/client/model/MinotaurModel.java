package com.beyondomega.entity.client.model;

import com.beyondomega.entity.client.animation.MinotaurAnimations;
import com.beyondomega.entity.client.renderer.MinotaurRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;
import net.minecraft.client.animation.KeyframeAnimation;

public class MinotaurModel extends EntityModel<MinotaurRenderState> {

	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(
					Identifier.fromNamespaceAndPath("beyondomega", "minotaur"),
					"main"
			);
	private final ModelPart root;
	private final ModelPart chest;

	private final ModelPart right_shoulder;
	private final ModelPart right_arm;
	private final ModelPart right_wrist;
	private final ModelPart right_pivot_for_hand;
	private final ModelPart right_hand;

	private final ModelPart head;
	private final ModelPart horn_right;
	private final ModelPart horn_left;

	private final ModelPart left_shoulder;
	private final ModelPart left_arm;
	private final ModelPart left_wrist;
	private final ModelPart left_pivot_for_hand;
	private final ModelPart left_hand;

	private final ModelPart right_hip;
	private final ModelPart right_leg;
	private final ModelPart right_foot;

	private final ModelPart left_hip;
	private final ModelPart left_leg;
	private final ModelPart left_foot;

	private final KeyframeAnimation runningAnimation;
	private final KeyframeAnimation idleAnimation;

	public MinotaurModel(ModelPart root) {
		super(root);

		this.root = root;
		this.chest = root.getChild("chest");

		this.right_shoulder = this.chest.getChild("right_shoulder");
		this.right_arm = this.right_shoulder.getChild("right_arm");
		this.right_wrist = this.right_arm.getChild("right_wrist");
		this.right_pivot_for_hand =
				this.right_wrist.getChild("right_pivot_for_hand");
		this.right_hand =
				this.right_pivot_for_hand.getChild("right_hand");

		this.head = this.chest.getChild("head");
		this.horn_right = this.head.getChild("horn_right");
		this.horn_left = this.head.getChild("horn_left");

		this.left_shoulder = this.chest.getChild("left_shoulder");
		this.left_arm = this.left_shoulder.getChild("left_arm");
		this.left_wrist = this.left_arm.getChild("left_wrist");
		this.left_pivot_for_hand =
				this.left_wrist.getChild("left_pivot_for_hand");
		this.left_hand =
				this.left_pivot_for_hand.getChild("left_hand");

		this.right_hip = this.chest.getChild("right_hip");
		this.right_leg = this.right_hip.getChild("right_leg");
		this.right_foot = this.right_leg.getChild("right_foot");

		this.left_hip = this.chest.getChild("left_hip");
		this.left_leg = this.left_hip.getChild("left_leg");
		this.left_foot = this.left_leg.getChild("left_foot");
		this.runningAnimation = MinotaurAnimations.running.bake(root);
		this.idleAnimation = MinotaurAnimations.idle.bake(root);
	}

	public static LayerDefinition createBodyLayer() {

		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition root = meshDefinition.getRoot();

		PartDefinition chest = root.addOrReplaceChild(
				"chest",
				CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(
								-18.0F, -4.0F, -10.0F,
								37.0F, 27.0F, 14.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offset(-1.0F, -49.0F, 2.0F)
		);

		chest.addOrReplaceChild(
				"cube_r1",
				CubeListBuilder.create()
						.texOffs(216, 0)
						.addBox(
								-6.0F, -8.0F, 0.0F,
								11.0F, 19.0F, 2.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						14.0F, -10.0F, 5.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		chest.addOrReplaceChild(
				"cube_r2",
				CubeListBuilder.create()
						.texOffs(212, 81)
						.addBox(
								-6.0F, -9.0F, -1.0F,
								11.0F, 20.0F, 2.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						-10.0F, -10.0F, 6.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		chest.addOrReplaceChild(
				"cube_r3",
				CubeListBuilder.create()
						.texOffs(0, 88)
						.addBox(
								-8.0F, -24.0F, -11.0F,
								18.0F, 47.0F, 16.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, -14.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition rightShoulder = chest.addOrReplaceChild(
				"right_shoulder",
				CubeListBuilder.create(),
				PartPose.offset(-23.0F, -16.0F, -3.0F)
		);

		rightShoulder.addOrReplaceChild(
				"right_shoulder_r1",
				CubeListBuilder.create()
						.texOffs(132, 152)
						.addBox(
								-8.0F, 0.0F, -8.0F,
								14.0F, 16.0F, 16.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 0.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition rightArm = rightShoulder.addOrReplaceChild(
				"right_arm",
				CubeListBuilder.create(),
				PartPose.offset(-8.0F, 6.0F, 0.0F)
		);

		rightArm.addOrReplaceChild(
				"right_arm_r1",
				CubeListBuilder.create()
						.texOffs(146, 77)
						.addBox(
								0.0F, -7.0F, -7.0F,
								19.0F, 14.0F, 14.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 0.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition rightWrist = rightArm.addOrReplaceChild(
				"right_wrist",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, 19.0F, 0.0F)
		);

		rightWrist.addOrReplaceChild(
				"right_wrist_r1",
				CubeListBuilder.create()
						.texOffs(120, 184)
						.addBox(
								0.0F, -8.0F, -8.0F,
								14.0F, 16.0F, 16.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 0.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition rightPivot = rightWrist.addOrReplaceChild(
				"right_pivot_for_hand",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, 10.0F, 1.0F)
		);

		rightPivot.addOrReplaceChild(
				"right_pivot_for_hand_r1",
				CubeListBuilder.create()
						.texOffs(68, 124)
						.addBox(
								-8.0F, -7.0F, -13.0F,
								21.0F, 14.0F, 14.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 4.0F, 5.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition rightHand = rightPivot.addOrReplaceChild(
				"right_hand",
				CubeListBuilder.create(),
				PartPose.offset(1.0F, 8.0F, -1.0F)
		);

		rightHand.addOrReplaceChild(
				"right_hand_r1",
				CubeListBuilder.create()
						.texOffs(180, 184)
						.addBox(
								-1.0F, -7.0F, -8.0F,
								14.0F, 16.0F, 16.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 0.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition head = chest.addOrReplaceChild(
				"head",
				CubeListBuilder.create(),
				PartPose.offset(1.0F, -21.0F, 0.0F)
		);

		head.addOrReplaceChild(
				"cube_r4",
				CubeListBuilder.create()
						.texOffs(88, 84)
						.addBox(
								-2.6759F, -1.0699F, 0.0F,
								7.0F, 2.0F, 1.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						-6.0F, -19.0F, 10.0F,
						0.0F, 0.0F, -2.7489F
				)
		);

		head.addOrReplaceChild(
				"cube_r5",
				CubeListBuilder.create()
						.texOffs(88, 77)
						.addBox(
								-9.1467F, -3.5595F, 0.0F,
								12.0F, 6.0F, 1.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						13.0F, -16.0F, 0.0F,
						0.0F, 0.0F, -2.6616F
				)
		);

		head.addOrReplaceChild(
				"cube_r6",
				CubeListBuilder.create()
						.texOffs(102, 28)
						.addBox(
								-3.0319F, -3.2383F, 0.0F,
								12.0F, 6.0F, 1.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						-13.0F, -15.0F, 0.0F,
						0.0F, 0.0F, 2.7053F
				)
		);

		head.addOrReplaceChild(
				"cube_r7",
				CubeListBuilder.create()
						.texOffs(102, 35)
						.addBox(
								-3.8771F, -1.4257F, 0.0F,
								7.0F, 2.0F, 1.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						6.0F, -20.0F, 10.0F,
						0.0F, 0.0F, 2.7053F
				)
		);

		head.addOrReplaceChild(
				"cube_r8",
				CubeListBuilder.create()
						.texOffs(172, 0)
						.addBox(
								-10.0F, -8.0F, 0.0F,
								11.0F, 16.0F, 11.0F,
								new CubeDeformation(0.0F)
						)
						.texOffs(0, 41)
						.addBox(
								-22.0F, -13.0F, -21.0F,
								23.0F, 26.0F, 21.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, -2.0F, 10.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition hornRight = head.addOrReplaceChild(
				"horn_right",
				CubeListBuilder.create()
						.texOffs(212, 60)
						.addBox(
								-21.0F, -17.0F, -5.0F,
								8.0F, 13.0F, 8.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offset(-13.0F, -18.0F, 0.0F)
		);

		hornRight.addOrReplaceChild(
				"cube_r9",
				CubeListBuilder.create()
						.texOffs(0, 211)
						.addBox(
								-4.0F, -1.0F, -5.0F,
								8.0F, 22.0F, 8.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 0.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition hornLeft = head.addOrReplaceChild(
				"horn_left",
				CubeListBuilder.create()
						.texOffs(64, 212)
						.addBox(
								-21.0F, -18.0F, -5.0F,
								8.0F, 13.0F, 8.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						13.0F, -17.0F, 0.0F,
						0.0F, 3.1416F, 0.0F
				)
		);

		hornLeft.addOrReplaceChild(
				"cube_r10",
				CubeListBuilder.create()
						.texOffs(32, 212)
						.addBox(
								-4.0F, -1.0F, -5.0F,
								8.0F, 22.0F, 8.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, -1.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition leftShoulder = chest.addOrReplaceChild(
				"left_shoulder",
				CubeListBuilder.create(),
				PartPose.offset(24.0F, -16.0F, -2.0F)
		);

		leftShoulder.addOrReplaceChild(
				"left_shoulder_r1",
				CubeListBuilder.create()
						.texOffs(166, 28)
						.addBox(
								-8.0F, -16.0F, -9.0F,
								14.0F, 16.0F, 16.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 0.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition leftArm = leftShoulder.addOrReplaceChild(
				"left_arm",
				CubeListBuilder.create(),
				PartPose.offset(8.0F, 6.0F, -1.0F)
		);

		leftArm.addOrReplaceChild(
				"left_arm_r1",
				CubeListBuilder.create()
						.texOffs(138, 124)
						.addBox(
								0.0F, -7.0F, -7.0F,
								19.0F, 14.0F, 14.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 0.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition leftWrist = leftArm.addOrReplaceChild(
				"left_wrist",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, 19.0F, 0.0F)
		);

		leftWrist.addOrReplaceChild(
				"left_wrist_r1",
				CubeListBuilder.create()
						.texOffs(0, 179)
						.addBox(
								0.0F, -8.0F, -8.0F,
								14.0F, 16.0F, 16.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 0.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition leftPivot = leftWrist.addOrReplaceChild(
				"left_pivot_for_hand",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, 9.0F, 0.0F)
		);

		leftPivot.addOrReplaceChild(
				"left_pivot_for_hand_r1",
				CubeListBuilder.create()
						.texOffs(102, 0)
						.addBox(
								-8.0F, -7.0F, -13.0F,
								21.0F, 14.0F, 14.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 5.0F, 6.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition leftHand = leftPivot.addOrReplaceChild(
				"left_hand",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, 9.0F, -1.0F)
		);

		leftHand.addOrReplaceChild(
				"left_hand_r1",
				CubeListBuilder.create()
						.texOffs(60, 180)
						.addBox(
								-1.0F, -8.0F, -7.0F,
								14.0F, 16.0F, 16.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 0.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition rightHip = chest.addOrReplaceChild(
				"right_hip",
				CubeListBuilder.create(),
				PartPose.offset(-11.0F, 24.0F, -3.0F)
		);

		rightHip.addOrReplaceChild(
				"right_hip_r1",
				CubeListBuilder.create()
						.texOffs(68, 88)
						.addBox(
								-1.0F, -9.0F, -9.0F,
								21.0F, 18.0F, 18.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 0.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition rightLeg = rightHip.addOrReplaceChild(
				"right_leg",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, 21.0F, 0.0F)
		);

		rightLeg.addOrReplaceChild(
				"right_leg_r1",
				CubeListBuilder.create()
						.texOffs(66, 152)
						.addBox(
								-1.0F, -7.0F, -7.0F,
								19.0F, 14.0F, 14.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 0.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition rightFoot = rightLeg.addOrReplaceChild(
				"right_foot",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, 19.0F, 0.0F)
		);

		rightFoot.addOrReplaceChild(
				"right_foot_r1",
				CubeListBuilder.create()
						.texOffs(204, 141)
						.addBox(
								-1.0F, -9.0F, -9.0F,
								10.0F, 18.0F, 18.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 0.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition leftHip = chest.addOrReplaceChild(
				"left_hip",
				CubeListBuilder.create(),
				PartPose.offset(12.0F, 24.0F, -3.0F)
		);

		leftHip.addOrReplaceChild(
				"left_hip_r1",
				CubeListBuilder.create()
						.texOffs(88, 41)
						.addBox(
								-1.0F, -9.0F, -9.0F,
								21.0F, 18.0F, 18.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 0.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition leftLeg = leftHip.addOrReplaceChild(
				"left_leg",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, 20.0F, 0.0F)
		);

		leftLeg.addOrReplaceChild(
				"left_leg_r1",
				CubeListBuilder.create()
						.texOffs(0, 151)
						.addBox(
								0.0F, -7.0F, -7.0F,
								19.0F, 14.0F, 14.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 0.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		PartDefinition leftFoot = leftLeg.addOrReplaceChild(
				"left_foot",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, 20.0F, 0.0F)
		);

		leftFoot.addOrReplaceChild(
				"left_foot_r1",
				CubeListBuilder.create()
						.texOffs(204, 105)
						.addBox(
								-1.0F, -9.0F, -9.0F,
								10.0F, 18.0F, 18.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						0.0F, 0.0F, 0.0F,
						0.0F, 0.0F, 1.5708F
				)
		);

		return LayerDefinition.create(meshDefinition, 512, 512);
	}

	@Override
	public void setupAnim(MinotaurRenderState state) {
		super.setupAnim(state);

		this.root.getAllParts().forEach(ModelPart::resetPose);

		if (state.walkAnimationSpeed > 0.01F) {

			this.runningAnimation.applyWalk(
					state.walkAnimationPos,
					state.walkAnimationSpeed,
					1.0F,
					1.0F
			);

		} else {

			this.idleAnimation.apply(
					(long) state.ageInTicks,
					1.0F
			);
		}

		this.root.yRot = (float) Math.PI;
	}
}