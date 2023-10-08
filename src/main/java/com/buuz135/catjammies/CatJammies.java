package com.buuz135.catjammies;

import net.minecraft.entity.EntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("catjammies")
public class CatJammies {

   private static final Logger LOGGER = LogManager.getLogger();

   public CatJammies() {
      FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
      MinecraftForge.EVENT_BUS.register(this);
   }

   private void doClientStuff(FMLClientSetupEvent event) {
      RenderingRegistry.registerEntityRenderingHandler(EntityType.CAT, CatJammiesCatRenderer::new);
      IJammyDetector.DETECTORS.add(IJammyDetector.MUSIC);
      IJammyDetector.DETECTORS.add(IJammyDetector.ME);
   }
}
