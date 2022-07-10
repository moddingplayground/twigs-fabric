package net.moddingplayground.twigs.impl.client.block;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.moddingplayground.twigs.api.block.TwigsBlocks;

@Environment(EnvType.CLIENT)
public final class TwigsBlocksClientImpl implements TwigsBlocks, ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap renderLayers = BlockRenderLayerMap.INSTANCE;
        renderLayers.putBlocks(RenderLayer.getCutout(),
            TWIG,
            PEBBLE,
            SEA_SHELL,
            AZALEA_FLOWERS,
            POTTED_AZALEA_FLOWERS,
            PETRIFIED_LICHEN,

            PAPER_LANTERN,
            ALLIUM_PAPER_LANTERN,
            BLUE_ORCHID_PAPER_LANTERN,
            DANDELION_PAPER_LANTERN,
            CRIMSON_ROOTS_PAPER_LANTERN,

            BAMBOO_LEAVES,

            OAK_TABLE,
            SPRUCE_TABLE,
            BIRCH_TABLE,
            JUNGLE_TABLE,
            ACACIA_TABLE,
            DARK_OAK_TABLE,
            MANGROVE_TABLE,
            CRIMSON_TABLE,
            WARPED_TABLE,
            STRIPPED_BAMBOO_TABLE,

            STRIPPED_BAMBOO_DOOR,
            STRIPPED_BAMBOO_TRAPDOOR
        );
    }
}
