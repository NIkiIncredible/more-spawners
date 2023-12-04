package de.nehlen.dungeons.more_spawners.block;

import de.nehlen.dungeons.more_spawners.block.entity.BlockEntitiesManager;
import de.nehlen.dungeons.more_spawners.block.entity.NetheriteSpawnerBlockEntity;
import de.nehlen.dungeons.more_spawners.block.helper.MoreSpawnerHelper;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class NetheriteSpawner extends BlockWithEntity implements BlockEntityProvider{
    public NetheriteSpawner(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NetheriteSpawnerBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return NetheriteSpawner.checkType(type, BlockEntitiesManager.NETHERITE_SPAWNER_BLOCK_ENTITY, world.isClient ? NetheriteSpawnerBlockEntity::clientTick : NetheriteSpawnerBlockEntity::serverTick);
    }

    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
        super.onStacksDropped(state, world, pos, tool, dropExperience);
        if (dropExperience) {
            int i = (15*5) + world.random.nextInt(15) + world.random.nextInt(15);
            this.dropExperience(world, pos, i);
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        super.appendTooltip(stack, world, tooltip, options);
        Optional<Text> optional = this.getEntityNameForTooltip(stack);
        if (optional.isPresent()) {
            tooltip.add(optional.get());
        } else {
            tooltip.add(ScreenTexts.EMPTY);
            tooltip.add(Text.translatable("block.minecraft.spawner.desc1").formatted(Formatting.GRAY));
            tooltip.add(ScreenTexts.space().append(Text.translatable("block.minecraft.spawner.desc2").formatted(Formatting.BLUE)));
        }
    }

    private Optional<Text> getEntityNameForTooltip(ItemStack stack) {
        return MoreSpawnerHelper.getEntityNameText(stack);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            if (player.getMainHandStack().getItem() instanceof SpawnEggItem) {
                ItemStack itemStack = player.getMainHandStack();
                SpawnEggItem item = (SpawnEggItem) itemStack.getItem();
                NetheriteSpawnerBlockEntity netheriteSpawnerBlockEntity = (NetheriteSpawnerBlockEntity) world.getBlockEntity(pos);
                EntityType<?> entityType = item.getEntityType(itemStack.getNbt());

                netheriteSpawnerBlockEntity.setEntityType(entityType, world.getRandom());
                netheriteSpawnerBlockEntity.markDirty();
                world.updateListeners(pos, state, state, Block.NOTIFY_ALL);
                world.emitGameEvent((Entity) player, GameEvent.BLOCK_CHANGE, pos);
                itemStack.decrement(1);
                return ActionResult.SUCCESS;
            }
            return ActionResult.FAIL;
        }
        return ActionResult.SUCCESS;
    }
}
