package net.moddingplayground.twigs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.moddingplayground.twigs.init.TwigsBlocks;

public class TwigsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(
            RenderLayer.getCutout(),

            TwigsBlocks.AZALEA_FLOWERS,
            TwigsBlocks.PEBBLE,
            TwigsBlocks.TWIG,
            TwigsBlocks.POTTED_AZALEA_FLOWERS,

            TwigsBlocks.OAK_TABLE,
            TwigsBlocks.SPRUCE_TABLE,
            TwigsBlocks.BIRCH_TABLE,
            TwigsBlocks.ACACIA_TABLE,
            TwigsBlocks.JUNGLE_TABLE,
            TwigsBlocks.DARK_OAK_TABLE,
            TwigsBlocks.CRIMSON_TABLE,
            TwigsBlocks.WARPED_TABLE,
            TwigsBlocks.STRIPPED_BAMBOO_TABLE,

            TwigsBlocks.PAPER_LANTERN,
            TwigsBlocks.ALLIUM_PAPER_LANTERN,
            TwigsBlocks.BLUE_ORCHID_PAPER_LANTERN,
            TwigsBlocks.DANDELION_PAPER_LANTERN,
            TwigsBlocks.CRIMSON_ROOTS_PAPER_LANTERN,

            TwigsBlocks.BAMBOO_LEAVES
        );
    }
}
