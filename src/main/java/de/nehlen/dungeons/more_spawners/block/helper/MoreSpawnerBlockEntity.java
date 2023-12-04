package de.nehlen.dungeons.more_spawners.block.helper;

import de.nehlen.dungeons.more_spawners.block.logic.MoreSpawnerLogic;
import net.fabricmc.fabric.api.blockview.v2.RenderDataBlockEntity;
import net.minecraft.block.entity.BlockEntity;

public interface MoreSpawnerBlockEntity extends RenderDataBlockEntity {
    public MoreSpawnerLogic getLogic();
}
