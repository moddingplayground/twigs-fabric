package net.moddingplayground.twigs.api.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;

public interface TwigsMaterial {
    Material FLOOR_LAYER = new FabricMaterialBuilder(MapColor.SPRUCE_BROWN).allowsMovement()
                                                                           .lightPassesThrough()
                                                                           .notSolid()
                                                                           .destroyedByPiston()
                                                                           .burnable()
                                                                           .build();
}
