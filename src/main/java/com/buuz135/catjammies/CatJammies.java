package com.buuz135.catjammies;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.InterModComms;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.InterModProcessEvent;
import org.slf4j.Logger;

import java.util.function.BiFunction;
import java.util.function.ToIntBiFunction;

@Mod("catjammies")
public class CatJammies {

	private static final Logger LOGGER = LogUtils.getLogger();

	public CatJammies(IEventBus eventBus, Dist dist) {
		if (dist.isClient()){
			eventBus.addListener(this::doClientStuff);
			eventBus.addListener(this::getIMCDetectors);
		}
	}

	private void doClientStuff(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			IJammyDetector.DETECTORS.add(IJammyDetector.MUSIC);
			IJammyDetector.DETECTORS.add(IJammyDetector.ME);
		});
	}

	@SuppressWarnings("unchecked")
	private void getIMCDetectors(InterModProcessEvent event) {
		event.enqueueWork(() -> InterModComms.getMessages("catjammies", "detector"::matches).forEach(message -> {
			var obj = message.messageSupplier().get();
			switch (obj) {
				case IJammyDetector detector -> IJammyDetector.DETECTORS.add(detector);
				// Assume that generics match due to type erasure
				case BiFunction<?, ?, ?> func -> IJammyDetector.DETECTORS.add(((BiFunction<Level, Cat, Integer>) func)::apply);
				case ToIntBiFunction<?, ?> func -> IJammyDetector.DETECTORS.add(((ToIntBiFunction<Level, Cat>) func)::applyAsInt);
                default -> throw new IllegalStateException("Detector must be either an IJammyDetector, BiFunction, or ToIntBiFunction. Actual: " + obj.getClass());
            }
		}));
	}
}
