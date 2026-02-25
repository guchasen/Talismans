package com.guchasen.talismans.entity.render;

import com.guchasen.talismans.entity.entities.ThunderTalismanEntity;
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
public class ThunderTalismanEntityRenderer extends EntityRenderer<ThunderTalismanEntity> {

    private final ItemRenderer itemRenderer;
    // 使用一个默认的纹理，即使不使用也要提供
    private static final Identifier DEFAULT_TEXTURE = new Identifier("textures/block/stone.png");

    public ThunderTalismanEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }
    @Override
    public void render(ThunderTalismanEntity entity, float yaw, float tickDelta, MatrixStack matrices,
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
    public Identifier getTexture(ThunderTalismanEntity entity) {
        return DEFAULT_TEXTURE; // 返回一个有效的纹理
    }
}
