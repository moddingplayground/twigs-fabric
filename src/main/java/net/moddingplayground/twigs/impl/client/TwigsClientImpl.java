package net.moddingplayground.twigs.impl.client;

import com.google.common.reflect.Reflection;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.moddingplayground.frame.api.util.InitializationLogger;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.api.block.TwigsBlocks;
import net.moddingplayground.twigs.impl.block.wood.TwigsWoodSet;
import net.moddingplayground.twigs.impl.block.wood.WoodSet;
import net.moddingplayground.twigs.impl.client.model.TwigsEntityModelLayers;
import net.moddingplayground.twigs.impl.client.render.entity.TwigsBoatEntityRenderer;
import net.moddingplayground.twigs.impl.entity.TwigsEntityType;

@Environment(EnvType.CLIENT)
public class TwigsClientImpl implements Twigs, ClientModInitializer {
    private static TwigsClientImpl instance = null;
    protected final InitializationLogger initializer;

    public TwigsClientImpl() {
        this.initializer = new InitializationLogger(LOGGER, MOD_NAME, EnvType.CLIENT);
        instance = this;
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void onInitializeClient() {
        this.initializer.start();

        BlockRenderLayerMap.INSTANCE.putBlocks(
            RenderLayer.getCutout(),

            TwigsBlocks.AZALEA_FLOWERS,
            TwigsBlocks.PEBBLE,
            TwigsBlocks.TWIG,
            TwigsBlocks.POTTED_AZALEA_FLOWERS,

            TwigsBlocks.PAPER_LANTERN,
            TwigsBlocks.ALLIUM_PAPER_LANTERN,
            TwigsBlocks.BLUE_ORCHID_PAPER_LANTERN,
            TwigsBlocks.DANDELION_PAPER_LANTERN,
            TwigsBlocks.CRIMSON_ROOTS_PAPER_LANTERN,

            TwigsBlocks.BAMBOO_LEAVES
        );

        this.woods(TwigsBlocks.WOOD_SETS.toArray(TwigsWoodSet[]::new));

        Reflection.initialize(TwigsEntityModelLayers.class);
        EntityRendererRegistry.register(TwigsEntityType.BOAT, TwigsBoatEntityRenderer::new);

        this.initializer.finish();
    }

    public void woods(WoodSet... woods) {
        for (WoodSet wood : woods) wood.registerClient();
    }

    public static TwigsClientImpl getInstance() {
        return instance;
    }
}
