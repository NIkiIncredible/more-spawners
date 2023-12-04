package de.nehlen.dungeons.more_spawners.block.entity;

import de.nehlen.dungeons.more_spawners.block.helper.MoreSpawnerBlockEntity;
import de.nehlen.dungeons.more_spawners.block.logic.MoreSpawnerLogic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.MobSpawnerEntry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NetheriteSpawnerBlockEntity extends BlockEntity implements MoreSpawnerBlockEntity {
    private final MoreSpawnerLogic logic = new MoreSpawnerLogic(){

        @Override
        public void sendStatus(World world, BlockPos pos, int status) {
            world.addSyncedBlockEvent(pos, Blocks.SPAWNER, status, 0);
        }

        @Override
        public void setSpawnEntry(@Nullable World world, BlockPos pos, MobSpawnerEntry spawnEntry) {
            super.setSpawnEntry(world, pos, spawnEntry);
            if (world != null) {
                BlockState blockState = world.getBlockState(pos);
                world.updateListeners(pos, blockState, blockState, Block.NO_REDRAW);
            }
        }

        @Override
        public int getSpawnCount() {
            return 20;
        }

        @Override
        public int getMaxNearbyEntities() {
            return 60;
        }

        @Override
        public int getRequiredPlayerRange() {
            return 32;
        }

        @Override
        public int getMaxSpawnDelay() {
            return 300;
        }

        @Override
        public int getMinSpawnDelay() {
            return 50;
        }
    };

    public NetheriteSpawnerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntitiesManager.NETHERITE_SPAWNER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.logic.readNbt(this.world, this.pos, nbt);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        this.logic.writeNbt(nbt);
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, NetheriteSpawnerBlockEntity blockEntity) {
        blockEntity.getLogic().clientTick(world, pos);
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, NetheriteSpawnerBlockEntity blockEntity) {
        blockEntity.getLogic().serverTick((ServerWorld)world, pos);
    }

    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbtCompound = this.createNbt();
        nbtCompound.remove("SpawnPotentials");
        return nbtCompound;
    }

    @Override
    public boolean onSyncedBlockEvent(int type, int data) {
        if (this.logic.handleStatus(this.world, type)) {
            return true;
        }
        return super.onSyncedBlockEvent(type, data);
    }

    @Override
    public boolean copyItemDataRequiresOperator() {
        return true;
    }

    public void setEntityType(EntityType<?> entityType, Random random) {
        this.logic.setEntityId(entityType, this.world, random, this.pos);
    }

    public MoreSpawnerLogic getLogic() {
        return this.logic;
    }
}
