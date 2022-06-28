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
        BlockRenderLayerMap.INSTANCE.putBlocks(
            RenderLayer.getCutout(),

            TwigsBlocks.AZALEA_FLOWERS,
            TwigsBlocks.PEBBLE,
            TwigsBlocks.SEA_SHELL,
            TwigsBlocks.TWIG,
            TwigsBlocks.POTTED_AZALEA_FLOWERS,

            TwigsBlocks.PAPER_LANTERN,
            TwigsBlocks.ALLIUM_PAPER_LANTERN,
            TwigsBlocks.BLUE_ORCHID_PAPER_LANTERN,
            TwigsBlocks.DANDELION_PAPER_LANTERN,
            TwigsBlocks.CRIMSON_ROOTS_PAPER_LANTERN,

            TwigsBlocks.BAMBOO_LEAVES,

            TwigsBlocks.OAK_TABLE,
            TwigsBlocks.SPRUCE_TABLE,
            TwigsBlocks.BIRCH_TABLE,
            TwigsBlocks.JUNGLE_TABLE,
            TwigsBlocks.ACACIA_TABLE,
            TwigsBlocks.DARK_OAK_TABLE,
            TwigsBlocks.CRIMSON_TABLE,
            TwigsBlocks.WARPED_TABLE
        );
    }
}
