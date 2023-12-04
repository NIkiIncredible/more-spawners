package de.nehlen.dungeons.more_spawners;

import de.nehlen.dungeons.more_spawners.block.BlocksManager;
import de.nehlen.dungeons.more_spawners.block.entity.BlockEntitiesManager;
import de.nehlen.dungeons.more_spawners.item.ItemsGroups;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreSpawners implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("more_spawners");
	public static final String MOD_ID = "more_spawners";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		BlocksManager.registerBlocks();
		ItemsGroups.registerItemGroups();
		BlockEntitiesManager.registerBlockEntities();
		LOGGER.info("Hello Fabric world!");
	}
}