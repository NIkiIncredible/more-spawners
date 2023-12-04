package de.nehlen.dungeons.more_spawners.block.helper;

import de.nehlen.dungeons.more_spawners.block.entity.GoldenSpawnerBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class MoreSpawnerHelper {

    @NotNull
    public static ActionResult getActionResult(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (!world.isClient) {
            if (player.getMainHandStack().getItem() instanceof SpawnEggItem) {
                ItemStack itemStack = player.getMainHandStack();
                SpawnEggItem item = (SpawnEggItem) itemStack.getItem();
                GoldenSpawnerBlockEntity goldenSpawnerBlockEntity = (GoldenSpawnerBlockEntity) world.getBlockEntity(pos);
                EntityType<?> entityType = item.getEntityType(itemStack.getNbt());

                goldenSpawnerBlockEntity.setEntityType(entityType, world.getRandom());
                goldenSpawnerBlockEntity.markDirty();
                world.updateListeners(pos, state, state, Block.NOTIFY_ALL);
                world.emitGameEvent((Entity) player, GameEvent.BLOCK_CHANGE, pos);
                itemStack.decrement(1);
                return ActionResult.SUCCESS;
            }
            return ActionResult.FAIL;
        }
        return ActionResult.SUCCESS;
    }

    @NotNull
    public static Optional<Text> getEntityNameText(ItemStack stack) {
        String string;
        Identifier identifier;
        NbtCompound nbtCompound = BlockItem.getBlockEntityNbt(stack);
        if (nbtCompound != null && nbtCompound.contains("SpawnData", NbtElement.COMPOUND_TYPE) && (identifier = Identifier.tryParse(string = nbtCompound.getCompound("SpawnData").getCompound("entity").getString("id"))) != null) {
            return Registries.ENTITY_TYPE.getOrEmpty(identifier).map(entityType -> Text.translatable(entityType.getTranslationKey()).formatted(Formatting.GRAY));
        }
        return Optional.empty();
    }
}
