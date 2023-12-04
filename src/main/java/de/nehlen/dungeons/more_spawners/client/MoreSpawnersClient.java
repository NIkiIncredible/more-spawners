package de.nehlen.dungeons.more_spawners.client;

import de.nehlen.dungeons.more_spawners.block.BlocksManager;
import de.nehlen.dungeons.more_spawners.block.entity.BlockEntitiesManager;
import de.nehlen.dungeons.more_spawners.block.entity.NetheriteSpawnerBlockEntity;
import de.nehlen.dungeons.more_spawners.client.render.block.entity.DiamondSpawnerBlockEntityRenderer;
import de.nehlen.dungeons.more_spawners.client.render.block.entity.GoldenSpawnerBlockEntityRenderer;
import de.nehlen.dungeons.more_spawners.client.render.block.entity.NetheriteSpawnerBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class MoreSpawnersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksManager.GOLDEN_SPAWNER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksManager.DIAMOND_SPAWNER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksManager.NETHERITE_SPAWNER, RenderLayer.getCutout());
        BlockEntityRendererFactories.register(BlockEntitiesManager.GOLDEN_SPAWNER_BLOCK_ENTITY, GoldenSpawnerBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(BlockEntitiesManager.DIAMOND_SPAWNER_BLOCK_ENTITY, DiamondSpawnerBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(BlockEntitiesManager.NETHERITE_SPAWNER_BLOCK_ENTITY, NetheriteSpawnerBlockEntityRenderer::new);
    }
}
