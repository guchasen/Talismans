package com.guchasen.talismans;


import com.guchasen.talismans.block.ModBlocks;
import com.guchasen.talismans.block.entity.ModBlockEntities;
import com.guchasen.talismans.items.ModItemGroups;
import com.guchasen.talismans.items.ModItems;
import com.guchasen.talismans.recipe.ModRecipes;
import com.guchasen.talismans.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Talismans implements ModInitializer {
	public static final String MOD_ID = "talismans";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Talismans Mod Starts");

		//register items
		ModItems.registerModItems();
		//register item groups
		ModItemGroups.registerItemGroups();
		//register blocks
		ModBlocks.registerModBlocks();
		//register block entities
		ModBlockEntities.registerBlockEntities();
		//register screen handlers
		ModScreenHandlers.registerScreenHandlers();
		//register recipes
		ModRecipes.registerRecipes();
	}
}