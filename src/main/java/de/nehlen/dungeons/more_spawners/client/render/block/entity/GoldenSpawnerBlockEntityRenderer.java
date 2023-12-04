package de.nehlen.dungeons.more_spawners.client.render.block.entity;

import de.nehlen.dungeons.more_spawners.block.entity.GoldenSpawnerBlockEntity;
import de.nehlen.dungeons.more_spawners.block.logic.MoreSpawnerLogic;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class GoldenSpawnerBlockEntityRenderer implements BlockEntityRenderer<GoldenSpawnerBlockEntity> {
    private final EntityRenderDispatcher entityRenderDispatcher;

    public GoldenSpawnerBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.entityRenderDispatcher = ctx.getEntityRenderDispatcher();
    }
    @Override
    public void render(GoldenSpawnerBlockEntity goldenSpawnerBlockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        matrices.translate(0.5f, 0.0f, 0.5f);
        MoreSpawnerLogic mobSpawnerLogic = goldenSpawnerBlockEntity.getLogic();
        Entity entity = mobSpawnerLogic.getRenderedEntity(goldenSpawnerBlockEntity.getWorld(), goldenSpawnerBlockEntity.getWorld().getRandom(), goldenSpawnerBlockEntity.getPos());
        if (entity != null) {
            float g = 0.53125f;
            float h = Math.max(entity.getWidth(), entity.getHeight());
            if ((double)h > 1.0) {
                g /= h;
            }
            matrices.translate(0.0f, 0.4f, 0.0f);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) MathHelper.lerp((double)tickDelta, mobSpawnerLogic.getLastRotation(), mobSpawnerLogic.getRotation()) * 10.0f));
            matrices.translate(0.0f, -0.2f, 0.0f);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-30.0f));
            matrices.scale(g, g, g);
            this.entityRenderDispatcher.render(entity, 0.0, 0.0, 0.0, 0.0f, tickDelta, matrices, vertexConsumers, light);
        }
        matrices.pop();
    }
}
