package de.nehlen.dungeons.more_spawners;

import de.nehlen.dungeons.more_spawners.datagen.BlockTagProvider;
import de.nehlen.dungeons.more_spawners.datagen.ModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MoreSpawnersDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModelProvider::new);
		pack.addProvider(BlockTagProvider::new);

	}
}
