package com.buuz135.catjammies;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("catjammies")
public class CatJammies {

	private static final Logger LOGGER = LogManager.getLogger();

	public CatJammies() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> eventBus.addListener(this::doClientStuff));
	}

	private void doClientStuff(FMLClientSetupEvent event) {
		IJammyDetector.DETECTORS.add(IJammyDetector.MUSIC);
		IJammyDetector.DETECTORS.add(IJammyDetector.ME);
	}
}
