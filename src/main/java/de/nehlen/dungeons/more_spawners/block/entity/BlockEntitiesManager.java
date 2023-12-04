package de.nehlen.dungeons.more_spawners.block.entity;

import de.nehlen.dungeons.more_spawners.MoreSpawners;
import de.nehlen.dungeons.more_spawners.block.BlocksManager;
import de.nehlen.dungeons.more_spawners.client.render.block.entity.DiamondSpawnerBlockEntityRenderer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockEntitiesManager {
    public static final BlockEntityType<GoldenSpawnerBlockEntity> GOLDEN_SPAWNER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MoreSpawners.MOD_ID, "gold_spawner_block_entity"),
                    FabricBlockEntityTypeBuilder.create(GoldenSpawnerBlockEntity::new,
                            BlocksManager.GOLDEN_SPAWNER).build());

    public static final BlockEntityType<DiamondSpawnerBlockEntity> DIAMOND_SPAWNER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MoreSpawners.MOD_ID, "diamond_spawner_block_entity"),
                    FabricBlockEntityTypeBuilder.create(DiamondSpawnerBlockEntity::new,
                            BlocksManager.DIAMOND_SPAWNER).build());

    public static final BlockEntityType<NetheriteSpawnerBlockEntity> NETHERITE_SPAWNER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MoreSpawners.MOD_ID, "netherite_spawner_block_entity"),
                    FabricBlockEntityTypeBuilder.create(NetheriteSpawnerBlockEntity::new,
                            BlocksManager.NETHERITE_SPAWNER).build());

    public static void registerBlockEntities() {
        MoreSpawners.LOGGER.info("Registering Block Entities for " + MoreSpawners.MOD_ID);
    }
}
