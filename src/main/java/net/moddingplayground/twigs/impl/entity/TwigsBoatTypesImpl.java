package net.moddingplayground.twigs.impl.entity;

import net.minecraft.util.Identifier;
import net.moddingplayground.frame.api.woods.v0.FrameWoodsEntrypoint;
import net.moddingplayground.frame.api.woods.v0.boat.FrameBoatTypeData;
import net.moddingplayground.frame.api.woods.v0.boat.FrameBoatTypeManager;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.api.block.TwigsBlocks;
import net.moddingplayground.twigs.api.entity.TwigsBoatTypes;
import net.moddingplayground.twigs.api.item.TwigsItems;

public class TwigsBoatTypesImpl implements FrameWoodsEntrypoint {
    @Override
    public void registerBoatTypes(FrameBoatTypeManager manager) {
        manager.register(new FrameBoatTypeData(
            new Identifier(Twigs.MOD_ID, "stripped_bamboo"), TwigsBlocks.STRIPPED_BAMBOO,
            () -> TwigsItems.STRIPPED_BAMBOO_BOAT, () -> TwigsItems.STRIPPED_BAMBOO_CHEST_BOAT,
            type -> TwigsBoatTypes.STRIPPED_BAMBOO = type
        ));
    }
}
