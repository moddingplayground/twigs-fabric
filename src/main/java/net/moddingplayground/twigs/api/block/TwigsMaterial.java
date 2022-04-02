package net.moddingplayground.twigs.api.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.util.Util;

import java.util.function.Function;

public interface TwigsMaterial {
    Function<MapColor, Material> FLOOR_LAYER = Util.memoize(
        color -> new FabricMaterialBuilder(color).allowsMovement()
                                                 .lightPassesThrough()
                                                 .notSolid()
                                                 .destroyedByPiston()
                                                 .burnable()
                                                 .build()
    );
}
