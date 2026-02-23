package com.guchasen.talismans;

import com.guchasen.talismans.items.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Talismans implements ModInitializer {
	public static final String MOD_ID = "talismans";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		//LOGGER.info("Hello Fabric world!");
		ModItems.registerModItems();
	}
}