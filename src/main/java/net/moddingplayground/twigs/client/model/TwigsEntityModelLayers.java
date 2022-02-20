package net.moddingplayground.twigs.client.model;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.moddingplayground.twigs.block.wood.WoodSet;
import net.moddingplayground.twigs.mixin.client.EntityModelLayersAccessor;
import net.moddingplayground.twigs.registry.TwigsRegistry;

import java.util.function.Function;

@Environment(EnvType.CLIENT)
public final class TwigsEntityModelLayers implements ClientModInitializer {
    public static final Function<WoodSet, EntityModelLayer> BOAT_LAYERS = Util.memoize(set -> {
        Identifier id = TwigsRegistry.WOOD.getId(set);
        Identifier identifier = new Identifier(id.getNamespace(), "boat/%s".formatted(id.getPath()));
        EntityModelLayer layer = EntityModelLayersAccessor.invokeCreate(String.valueOf(identifier), EntityModelLayersAccessor.getMAIN());
        EntityModelLayerRegistry.registerModelLayer(layer, BoatEntityModel::getTexturedModelData);
        return layer;
    });

    @Override
    public void onInitializeClient() {
        TwigsRegistry.WOOD.forEach(BOAT_LAYERS::apply);
    }
}
