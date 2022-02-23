package net.moddingplayground.twigs.impl.client.model;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.moddingplayground.twigs.api.registry.TwigsRegistry;
import net.moddingplayground.twigs.impl.block.wood.WoodSet;

import java.util.function.Function;

@Environment(EnvType.CLIENT)
public final class TwigsEntityModelLayers implements ClientModInitializer {
    public static final Function<WoodSet, EntityModelLayer> BOAT_LAYERS = Util.memoize(set -> {
        Identifier id = TwigsRegistry.WOOD.getId(set);
        Identifier identifier = new Identifier(id.getNamespace(), "boat/%s".formatted(id.getPath()));
        EntityModelLayer ret = new EntityModelLayer(identifier, "main");
        EntityModelLayerRegistry.registerModelLayer(ret, BoatEntityModel::getTexturedModelData);
        return ret;
    });

    @Override
    public void onInitializeClient() {
        TwigsRegistry.WOOD.forEach(BOAT_LAYERS::apply);
    }
}
