package com.buuz135.catjammies;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.CatModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cat;

public class CatJammiesCatCollarLayer extends RenderLayer<Cat, CatModel<Cat>> {

	private static final ResourceLocation CAT_COLLAR = new ResourceLocation("textures/entity/cat/cat_collar.png");
	private final CatModel<Cat> catModel;

	public CatJammiesCatCollarLayer(RenderLayerParent<Cat, CatModel<Cat>> entityRenderer, EntityModelSet modelSet) {
		super(entityRenderer);
		this.catModel = new CatModel<>(modelSet.bakeLayer(ModelLayers.CAT_COLLAR));
	}

	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn, Cat cat, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (cat.isTame()) {
			float[] afloat = cat.getCollarColor().getTextureDiffuseColors();
			coloredCutoutModelCopyLayerRender(this.getParentModel(), this.catModel, CAT_COLLAR, poseStack, bufferSource, packedLightIn, cat, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, afloat[0], afloat[1], afloat[2]);
		}
	}
}
