package de.nehlen.dungeons.more_spawners.item;

import de.nehlen.dungeons.more_spawners.MoreSpawners;
import de.nehlen.dungeons.more_spawners.block.BlocksManager;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemsGroups {

    public static ItemGroup MORE_SPAWNERS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(MoreSpawners.MOD_ID, "mod"), FabricItemGroup.builder().displayName(Text.literal("More Spawners"))
                    .icon(() -> new ItemStack(BlocksManager.GOLDEN_SPAWNER))
                    .entries(((displayContext, entries) -> {
                        entries.add(BlocksManager.GOLDEN_SPAWNER);
                        entries.add(BlocksManager.DIAMOND_SPAWNER);
                        entries.add(BlocksManager.NETHERITE_SPAWNER);
                    })).build());

    public static void registerItemGroups() {
        MoreSpawners.LOGGER.info("Registering Item Groups for " + MoreSpawners.MOD_ID);
    }
}
