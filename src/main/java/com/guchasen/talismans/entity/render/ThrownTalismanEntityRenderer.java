package com.guchasen.talismans.entity.render;

import com.guchasen.talismans.entity.entities.ThrownTalismanEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ThrownTalismanEntityRenderer extends EntityRenderer<ThrownTalismanEntity> {

    private final ItemRenderer itemRenderer;
    // 使用一个默认的纹理，即使不使用也要提供
    private static final Identifier DEFAULT_TEXTURE = new Identifier("textures/block/stone.png");

    public ThrownTalismanEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }
    @Override
    public void render(ThrownTalismanEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        // 渲染物品
        ItemStack itemStack = entity.getStack();
        this.itemRenderer.renderItem(itemStack, ModelTransformationMode.GROUND, light, 0,
                matrices, vertexConsumers, entity.getWorld(), entity.getId());

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(ThrownTalismanEntity entity) {
        return DEFAULT_TEXTURE; // 返回一个有效的纹理
    }
}
