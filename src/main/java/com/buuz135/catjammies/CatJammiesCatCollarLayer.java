package com.buuz135.catjammies;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.CatModel;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.ResourceLocation;

public class CatJammiesCatCollarLayer extends LayerRenderer<CatEntity, CatModel<CatEntity>> {

	private static final ResourceLocation CAT_COLLAR = new ResourceLocation("textures/entity/cat/cat_collar.png");
	private final CatModel<CatEntity> model = new CatModel<>(0.01F);

	public CatJammiesCatCollarLayer(IEntityRenderer<CatEntity, CatModel<CatEntity>> entityRenderer) {
		super(entityRenderer);
	}

	public void render(MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn, CatEntity catEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (catEntity.isTame()) {
			float[] afloat = catEntity.getCollarColor().getTextureDiffuseColors();
			coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, CAT_COLLAR, matrixStack, bufferIn, packedLightIn, catEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, afloat[0], afloat[1], afloat[2]);
		}
	}
}
