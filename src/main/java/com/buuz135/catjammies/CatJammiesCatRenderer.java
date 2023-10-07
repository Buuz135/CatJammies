package com.buuz135.catjammies;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CatModel;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class CatJammiesCatRenderer extends MobRenderer<CatEntity, CatModel<CatEntity>> {

	public CatJammiesCatRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new CatJammiesCatModel<>(0.0F), 0.4F);
		this.addLayer(new CatJammiesCatCollarLayer(this));
	}

	public ResourceLocation getTextureLocation(CatEntity catEntity) {
		return catEntity.getResourceLocation();
	}

	protected void scale(CatEntity catEntity, MatrixStack matrixStack, float partialTickTime) {
		super.scale(catEntity, matrixStack, partialTickTime);
		matrixStack.scale(0.8F, 0.8F, 0.8F);
	}

	protected void setupRotations(CatEntity catEntity, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
		super.setupRotations(catEntity, matrixStack, ageInTicks, rotationYaw, partialTicks);
		float f = catEntity.getLieDownAmount(partialTicks);
		if (f > 0.0F) {
			matrixStack.translate((double) (0.4F * f), (double) (0.15F * f), (double) (0.1F * f));
			matrixStack.mulPose(Vector3f.ZP.rotationDegrees(MathHelper.rotLerp(f, 0.0F, 90.0F)));
			BlockPos blockpos = catEntity.blockPosition();

			for (PlayerEntity playerentity : catEntity.level.getEntitiesOfClass(PlayerEntity.class, (new AxisAlignedBB(blockpos)).inflate(2.0, 2.0, 2.0))) {
				if (playerentity.isSleeping()) {
					matrixStack.translate((double) (0.15F * f), 0.0, 0.0);
					break;
				}
			}
		}
	}
}
