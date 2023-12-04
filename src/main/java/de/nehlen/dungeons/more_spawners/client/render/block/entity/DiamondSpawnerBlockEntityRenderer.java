package de.nehlen.dungeons.more_spawners.client.render.block.entity;

import de.nehlen.dungeons.more_spawners.block.entity.DiamondSpawnerBlockEntity;
import de.nehlen.dungeons.more_spawners.block.logic.MoreSpawnerLogic;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class DiamondSpawnerBlockEntityRenderer implements BlockEntityRenderer<DiamondSpawnerBlockEntity> {
    private final EntityRenderDispatcher entityRenderDispatcher;

    public DiamondSpawnerBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.entityRenderDispatcher = ctx.getEntityRenderDispatcher();
    }
    @Override
    public void render(DiamondSpawnerBlockEntity diamondSpawnerBlockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        matrices.translate(0.5f, 0.0f, 0.5f);
        MoreSpawnerLogic mobSpawnerLogic = diamondSpawnerBlockEntity.getLogic();
        Entity entity = mobSpawnerLogic.getRenderedEntity(diamondSpawnerBlockEntity.getWorld(), diamondSpawnerBlockEntity.getWorld().getRandom(), diamondSpawnerBlockEntity.getPos());
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
