package com.buuz135.catjammies;

import net.minecraft.client.renderer.entity.model.CatModel;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.math.MathHelper;

public class CatJammiesCatModel<T extends CatEntity> extends CatModel<T> {

	public CatJammiesCatModel(float p_i51069_1_) {
		super(p_i51069_1_);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		int jamAmount = IJammyDetector.DETECTORS.stream().map((iJammyDetector) -> {
			return iJammyDetector.detect(entity.level, entity);
		}).mapToInt((value) -> {
			return value;
		}).sum();
		if (jamAmount > 0) {
			this.head.xRot = MathHelper.sin(ageInTicks) * 0.2F * (float) jamAmount;
			this.head.zRot = MathHelper.cos(ageInTicks) * 0.01F * (float) jamAmount;
			this.head.yRot = MathHelper.cos(ageInTicks) * 0.01F * (float) jamAmount;
		}
	}
}
