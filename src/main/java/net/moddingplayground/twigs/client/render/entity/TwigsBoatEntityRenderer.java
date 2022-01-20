package net.moddingplayground.twigs.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;
import net.moddingplayground.twigs.block.wood.WoodSet;
import net.moddingplayground.twigs.client.model.TwigsEntityModelLayers;
import net.moddingplayground.twigs.entity.TwigsBoatEntity;
import net.moddingplayground.twigs.registry.TwigsRegistry;

import java.util.function.BiFunction;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class TwigsBoatEntityRenderer<T extends TwigsBoatEntity> extends EntityRenderer<T> {
    public static final Function<Identifier, Identifier> TEXTURES = Util.memoize(TwigsBoatEntityRenderer::texture);
    public static final BiFunction<EntityRendererFactory.Context, Identifier, BoatEntityModel> MODELS = Util.memoize(TwigsBoatEntityRenderer::modelLayer);

    private final EntityRendererFactory.Context context;

    public TwigsBoatEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.8f;
        this.context = ctx;
    }

    @Override
    public void render(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertices, int light) {
        matrices.push();
        matrices.translate(0.0, 0.375, 0.0);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0f - yaw));

        float wobble = (float)entity.getDamageWobbleTicks() - tickDelta;
        float wobbleStrength = Math.max(entity.getDamageWobbleStrength() - tickDelta, 0);
        if (wobble > 0.0f) {
            matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(MathHelper.sin(wobble) * wobble * wobbleStrength / 10.0f * (float)entity.getDamageWobbleSide()));
        }
        if (!MathHelper.approximatelyEquals(entity.interpolateBubbleWobble(tickDelta), 0.0f)) {
            matrices.multiply(new Quaternion(new Vec3f(1.0f, 0.0f, 1.0f), entity.interpolateBubbleWobble(tickDelta), true));
        }

        matrices.scale(-1.0f, -1.0f, 1.0f);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90.0f));

        BoatEntityModel model = this.getModelLayer(entity);
        model.setAngles(entity, tickDelta, 0.0f, -0.1f, 0.0f, 0.0f);

        Identifier texture = this.getTexture(entity);
        VertexConsumer vertex = vertices.getBuffer(model.getLayer(texture));
        model.render(matrices, vertex, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);

        if (!entity.isSubmergedInWater()) {
            VertexConsumer vertex2 = vertices.getBuffer(RenderLayer.getWaterMask());
            model.getWaterPatch().render(matrices, vertex2, light, OverlayTexture.DEFAULT_UV);
        }

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertices, light);
    }

    public static Identifier texture(Identifier id) {
        return new Identifier(id.getNamespace(), "textures/entity/boat/%s.png".formatted(id.getPath()));
    }

    public static BoatEntityModel modelLayer(EntityRendererFactory.Context ctx, Identifier id) {
        WoodSet set = TwigsRegistry.WOOD.get(id);
        EntityModelLayer layer = TwigsEntityModelLayers.getBoat(set);
        return new BoatEntityModel(ctx.getPart(layer));
    }

    public BoatEntityModel getModelLayer(T entity) {
        return MODELS.apply(this.context, entity.getWoodTypeId());
    }

    @Override
    public Identifier getTexture(T entity) {
        return TEXTURES.apply(entity.getWoodTypeId());
    }
}
