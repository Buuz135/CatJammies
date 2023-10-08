package com.buuz135.catjammies;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.CatModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

public class CatJammiesCatRenderer extends MobRenderer<Cat, CatModel<Cat>> {

	public CatJammiesCatRenderer(EntityRendererProvider.Context context) {
		super(context, new CatJammiesCatModel<>(context.bakeLayer(ModelLayers.CAT)), 0.4F);
		this.addLayer(new CatJammiesCatCollarLayer(this, context.getModelSet()));
	}

	public ResourceLocation getTextureLocation(Cat cat) {
		return cat.getResourceLocation();
	}

	protected void scale(Cat cat, PoseStack poseStack, float partialTickTime) {
		super.scale(cat, poseStack, partialTickTime);
		poseStack.scale(0.8F, 0.8F, 0.8F);
	}

	protected void setupRotations(Cat cat, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
		super.setupRotations(cat, poseStack, ageInTicks, rotationYaw, partialTicks);
		float f = cat.getLieDownAmount(partialTicks);
		if (f > 0.0F) {
			poseStack.translate((double) (0.4F * f), (double) (0.15F * f), (double) (0.1F * f));
			poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.rotLerp(f, 0.0F, 90.0F)));
			BlockPos blockpos = cat.blockPosition();

			for (Player playerentity : cat.level.getEntitiesOfClass(Player.class, (new AABB(blockpos)).inflate(2.0, 2.0, 2.0))) {
				if (playerentity.isSleeping()) {
					poseStack.translate((double) (0.15F * f), 0.0, 0.0);
					break;
				}
			}
		}
	}
}
