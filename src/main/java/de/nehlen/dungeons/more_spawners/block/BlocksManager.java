package de.nehlen.dungeons.more_spawners.block;

import de.nehlen.dungeons.more_spawners.MoreSpawners;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class BlocksManager {

    public static void registerBlocks() {
        MoreSpawners.LOGGER.info("Registering Blocks for" + MoreSpawners.MOD_ID);
    }

    public static final Block GOLDEN_SPAWNER = registerBlock("golden_spawner",
            new GoldenSpawner(FabricBlockSettings.copyOf(Blocks.SPAWNER)));
    public static final Block DIAMOND_SPAWNER = registerBlock("diamond_spawner",
            new DiamondSpawner(FabricBlockSettings.copyOf(Blocks.SPAWNER)));

    public static final Block NETHERITE_SPAWNER = registerBlock("netherite_spawner",
            new NetheriteSpawner(FabricBlockSettings.copyOf(Blocks.SPAWNER)));

    public static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(MoreSpawners.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(MoreSpawners.MOD_ID, name),
                block);
    }
}
