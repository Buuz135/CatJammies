package com.buuz135.catjammies;

import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

@Mod("catjammies")
public class CatJammies {

	private static final Logger LOGGER = LogUtils.getLogger();

	public CatJammies(IEventBus eventBus, Dist dist) {
		if (dist.isClient()){
			eventBus.addListener(this::doClientStuff);
		}
	}

	private void doClientStuff(FMLClientSetupEvent event) {
		IJammyDetector.DETECTORS.add(IJammyDetector.MUSIC);
		IJammyDetector.DETECTORS.add(IJammyDetector.ME);
	}
}
