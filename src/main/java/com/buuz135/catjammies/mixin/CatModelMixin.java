package com.buuz135.catjammies.mixin;

import com.buuz135.catjammies.IJammyDetector;
import net.minecraft.client.model.CatModel;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Cat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CatModel.class)
public abstract class CatModelMixin extends OcelotModel<Cat> {

    public CatModelMixin(ModelPart root) {
        super(root);
    }

    @Inject(at = @At("TAIL"), method = "setupAnim(Lnet/minecraft/world/entity/animal/Cat;FFFFF)V")
    public void onAnimationSetup(Cat cat, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        int jamAmount = IJammyDetector.DETECTORS.stream()
                .mapToInt(detector -> detector.detect(cat.level(), cat))
                .sum();
        if (jamAmount > 0) {
            this.head.xRot = Mth.sin(ageInTicks) * 0.2F * (float) jamAmount;
            this.head.zRot = Mth.cos(ageInTicks) * 0.01F * (float) jamAmount;
            this.head.yRot = Mth.cos(ageInTicks) * 0.01F * (float) jamAmount;
        }
    }
}
