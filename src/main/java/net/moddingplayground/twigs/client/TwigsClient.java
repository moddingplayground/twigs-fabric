package net.moddingplayground.twigs.client;

import com.google.common.reflect.Reflection;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.block.TwigsBlocks;
import net.moddingplayground.twigs.block.wood.TwigsWoodSet;
import net.moddingplayground.twigs.block.wood.WoodSet;
import net.moddingplayground.twigs.client.model.TwigsEntityModelLayers;
import net.moddingplayground.twigs.client.render.entity.TwigsBoatEntityRenderer;
import net.moddingplayground.twigs.entity.TwigsEntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.moddingplayground.twigs.block.TwigsBlocks.*;

@Environment(EnvType.CLIENT)
public final class TwigsClient implements ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("%s-client".formatted(Twigs.MOD_ID));

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing {}-client", Twigs.MOD_NAME);

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

        this.woods(WOOD_SETS.toArray(TwigsWoodSet[]::new));

        Reflection.initialize(TwigsEntityModelLayers.class);
        EntityRendererRegistry.register(TwigsEntityType.BOAT, TwigsBoatEntityRenderer::new);

        LOGGER.info("Initialized {}-client", Twigs.MOD_NAME);
    }

    public void woods(WoodSet... woods) {
        for (WoodSet wood : woods) wood.registerClient();
    }
}
