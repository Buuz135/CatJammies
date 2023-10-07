package com.buuz135.catjammies;

import net.minecraft.client.model.CatModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Cat;

public class CatJammiesCatModel<T extends Cat> extends CatModel<T> {

	public CatJammiesCatModel(ModelPart modelPart) {
		super(modelPart);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		int jamAmount = IJammyDetector.DETECTORS.stream().map((iJammyDetector) -> {
			return iJammyDetector.detect(entity.level(), entity);
		}).mapToInt((value) -> {
			return value;
		}).sum();
		if (jamAmount > 0) {
			this.head.xRot = Mth.sin(ageInTicks) * 0.2F * (float) jamAmount;
			this.head.zRot = Mth.cos(ageInTicks) * 0.01F * (float) jamAmount;
			this.head.yRot = Mth.cos(ageInTicks) * 0.01F * (float) jamAmount;
		}
	}
}
