package de.nehlen.dungeons.more_spawners.datagen;

import de.nehlen.dungeons.more_spawners.block.BlocksManager;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleState(BlocksManager.GOLDEN_SPAWNER);
        blockStateModelGenerator.registerSimpleState(BlocksManager.DIAMOND_SPAWNER);
        blockStateModelGenerator.registerSimpleState(BlocksManager.NETHERITE_SPAWNER);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
