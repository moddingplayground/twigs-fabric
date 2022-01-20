package net.moddingplayground.twigs.client.model;

import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.moddingplayground.twigs.block.wood.WoodSet;
import net.moddingplayground.twigs.mixin.client.EntityModelLayersAccessor;
import net.moddingplayground.twigs.registry.TwigsRegistry;

import java.util.Map;

public class TwigsEntityModelLayers {
    private static final Map<WoodSet, EntityModelLayer> BOAT_LAYERS = Util.make(Maps.newHashMap(), map -> {
        for (WoodSet set : TwigsRegistry.WOOD) {
            Identifier id = TwigsRegistry.WOOD.getId(set);
            Identifier identifier = new Identifier(id.getNamespace(), "boat/%s".formatted(id.getPath()));
            EntityModelLayer layer = EntityModelLayersAccessor.invokeCreate(String.valueOf(identifier), EntityModelLayersAccessor.getMAIN());
            EntityModelLayerRegistry.registerModelLayer(layer, BoatEntityModel::getTexturedModelData);
            map.put(set, layer);
        }
    });

    public static EntityModelLayer getBoat(WoodSet wood) {
        return BOAT_LAYERS.get(wood);
    }
}
