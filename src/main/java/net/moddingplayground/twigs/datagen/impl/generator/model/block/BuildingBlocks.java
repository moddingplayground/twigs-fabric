package net.moddingplayground.twigs.datagen.impl.generator.model.block;

import net.minecraft.block.enums.BlockHalf;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.Direction;
import net.moddingplayground.twigs.datagen.impl.generator.model.InheritingModelGen;
import net.moddingplayground.twigs.datagen.impl.generator.model.ModelGen;
import net.moddingplayground.twigs.datagen.impl.generator.model.StateGen;
import net.moddingplayground.twigs.datagen.impl.generator.model.StateModelInfo;

import java.util.function.BiFunction;

@SuppressWarnings("unused")
public final class BuildingBlocks {
    public static final BiFunction<Direction, StateModelInfo, StateModelInfo> DIRECTION_MODEL = Util.memoize((dir, root) -> {
        root.uvlock(true);
        switch (dir) {
            case DOWN -> root.rotate(90, 0);
            case UP -> root.rotate(270, 0);
            case NORTH -> root.rotate(0, 0).uvlock(false);
            case SOUTH -> root.rotate(0, 180);
            case WEST -> root.rotate(0, 270);
            case EAST -> root.rotate(0, 90);
        }
        return root;
    });

    public static StateGen wallPlant(Identifier name, Identifier texName) {
        MultipartStateGen gen = MultipartStateGen.multipart();
        for (Direction direction : Direction.values()) {
            StateModelInfo root = StateModelInfo.create(name, InheritingModelGen.wallPlantThin(texName));
            StateModelInfo model = DIRECTION_MODEL.apply(direction, root);
            gen.part(
                StateSelector.and().condition(direction.getName(), "true"),
                   model
               )
               .part(
                   StateSelector.and()
                                .condition("up", "false")
                                .condition("north", "false")
                                .condition("west", "false")
                                .condition("south", "false")
                                .condition("east", "false")
                                .condition("down", "false"),
                   model
               );
        }
        return gen;
    }

    public static StateGen fence(Identifier name, Identifier texName) {
        return MultipartStateGen.multipart()
                                .part(StateModelInfo.create(Identifier.tryParse(name + "_post"), InheritingModelGen.fencePost(texName)))
                                .part(
                                    StateSelector.and().condition("north", "true"),
                                    StateModelInfo.create(Identifier.tryParse(name + "_side"), InheritingModelGen.fenceSide(texName))
                                )
                                .part(
                                    StateSelector.and().condition("east", "true"),
                                    StateModelInfo.create(Identifier.tryParse(name + "_side"), InheritingModelGen.fenceSide(texName))
                                                  .rotate(0, 90)
                                                  .uvlock(true)
                                )
                                .part(
                                    StateSelector.and().condition("south", "true"),
                                    StateModelInfo.create(Identifier.tryParse(name + "_side"), InheritingModelGen.fenceSide(texName))
                                                  .rotate(0, 180)
                                                  .uvlock(true)
                                )
                                .part(
                                    StateSelector.and().condition("west", "true"),
                                    StateModelInfo.create(Identifier.tryParse(name + "_side"), InheritingModelGen.fenceSide(texName))
                                                  .rotate(0, 270)
                                                  .uvlock(true)
                                );
    }

    private static StateGen wallGeneric(Identifier name, ModelGen post, ModelGen side, ModelGen tall) {
        StateModelInfo postC = StateModelInfo.create(Identifier.tryParse(name + "_post"), post);
        StateModelInfo sideN = StateModelInfo.create(Identifier.tryParse(name + "_side"), side).uvlock(true).rotate(0, 0);
        StateModelInfo sideE = StateModelInfo.create(Identifier.tryParse(name + "_side"), side).uvlock(true).rotate(0, 90);
        StateModelInfo sideS = StateModelInfo.create(Identifier.tryParse(name + "_side"), side).uvlock(true).rotate(0, 180);
        StateModelInfo sideW = StateModelInfo.create(Identifier.tryParse(name + "_side"), side).uvlock(true).rotate(0, 270);
        StateModelInfo sideTallN = StateModelInfo.create(Identifier.tryParse(name + "_side_tall"), tall).uvlock(true).rotate(0, 0);
        StateModelInfo sideTallE = StateModelInfo.create(Identifier.tryParse(name + "_side_tall"), tall).uvlock(true).rotate(0, 90);
        StateModelInfo sideTallS = StateModelInfo.create(Identifier.tryParse(name + "_side_tall"), tall).uvlock(true).rotate(0, 180);
        StateModelInfo sideTallW = StateModelInfo.create(Identifier.tryParse(name + "_side_tall"), tall).uvlock(true).rotate(0, 270);

        return MultipartStateGen.multipart()
                                .part(StateSelector.and().condition("up", "true"), postC)
                                .part(StateSelector.and().condition("north", "low"), sideN)
                                .part(StateSelector.and().condition("east", "low"), sideE)
                                .part(StateSelector.and().condition("south", "low"), sideS)
                                .part(StateSelector.and().condition("west", "low"), sideW)
                                .part(StateSelector.and().condition("north", "tall"), sideTallN)
                                .part(StateSelector.and().condition("east", "tall"), sideTallE)
                                .part(StateSelector.and().condition("south", "tall"), sideTallS)
                                .part(StateSelector.and().condition("west", "tall"), sideTallW);
    }

    public static StateGen wallAll(Identifier name, Identifier texName) {
        return wallGeneric(
            name,
            InheritingModelGen.wallPost(texName),
            InheritingModelGen.wallSide(texName),
            InheritingModelGen.wallSideTall(texName)
        );
    }

    public static StateGen wallSided(Identifier name, Identifier bottom, Identifier top, Identifier side) {
        return wallGeneric(
            name,
            InheritingModelGen.wallSidedPost(bottom, top, side),
            InheritingModelGen.wallSidedSide(bottom, top, side),
            InheritingModelGen.wallSidedSideTall(bottom, top, side)
        );
    }

    public static StateGen wallColumn(Identifier name, Identifier end, Identifier side) {
        return wallSided(name, end, end, side);
    }

    private static StateGen slabGeneric(Identifier name, Identifier doubleName, ModelGen bottom, ModelGen top, ModelGen full) {
        // No overload because we use cubeAll here instead of cubeBottomTop - this makes the difference
        if (doubleName == null) {
            // Generate custom double model
            return VariantsStateGen
                .variants("type=bottom", StateModelInfo.create(name, bottom))
                .variant("type=top", StateModelInfo.create(Identifier.tryParse(name + "_top"), top))
                .variant("type=double", StateModelInfo.create(Identifier.tryParse(name + "_double"), full));
        } else {
            // Use existing model
            return VariantsStateGen
                .variants("type=bottom", StateModelInfo.create(name, bottom))
                .variant("type=top", StateModelInfo.create(Identifier.tryParse(name + "_top"), top))
                .variant("type=double", StateModelInfo.create(doubleName));
        }
    }

    public static StateGen slabAll(Identifier name, Identifier doubleName, Identifier texName) {
        return slabGeneric(
            name, doubleName,
            InheritingModelGen.slabAll(texName),
            InheritingModelGen.slabAllTop(texName),
            doubleName == null ? InheritingModelGen.cubeAll(texName) : null
        );
    }

    public static StateGen slabSided(Identifier name, Identifier doubleName, Identifier bottom, Identifier top, Identifier side) {
        return slabGeneric(
            name, doubleName,
            InheritingModelGen.slab(bottom, top, side),
            InheritingModelGen.slabTop(bottom, top, side),
            doubleName == null ? InheritingModelGen.cubeBottomTop(bottom, top, side) : null
        );
    }

    public static StateGen slabColumn(Identifier name, Identifier doubleName, Identifier end, Identifier side) {
        return slabSided(name, doubleName, end, end, side);
    }

    private static StateGen stairsGeneric(Identifier name, ModelGen inner, ModelGen outer, ModelGen straight) {
        boolean innerM = false; // Set to true after we supplied a modelgen, so we don't generate it twice
        boolean outerM = false;
        boolean stairsM = false;

        VariantsStateGen gen = VariantsStateGen.variants();
        int y = 270;
        for (Direction dir : Direction.Type.HORIZONTAL) {
            for (BlockHalf half : BlockHalf.values()) {
                int x = half == BlockHalf.TOP ? 180 : 0;
                String state = String.format("facing=%s,half=%s", dir.asString(), half.asString());

                Identifier iname = Identifier.tryParse(name + "_inner");
                Identifier oname = Identifier.tryParse(name + "_outer");

                int yp = y == 0 ? 270 : y - 90;
                int yn = y == 270 ? 0 : y + 90;

                StateModelInfo innerL = StateModelInfo.create(iname, innerM ? null : inner).rotate(x, x == 180 ? y : yp).uvlock(true);
                StateModelInfo outerL = StateModelInfo.create(oname, outerM ? null : outer).rotate(x, x == 180 ? y : yp).uvlock(true);
                StateModelInfo innerR = StateModelInfo.create(iname).rotate(x, x == 180 ? yn : y).uvlock(true);
                StateModelInfo outerR = StateModelInfo.create(oname).rotate(x, x == 180 ? yn : y).uvlock(true);
                StateModelInfo stairs = StateModelInfo.create(name, stairsM ? null : straight).rotate(x, y).uvlock(true);

                innerM = true;
                outerM = true;
                stairsM = true;

                gen.variant(state + ",shape=straight", stairs);
                gen.variant(state + ",shape=inner_left", innerL);
                gen.variant(state + ",shape=inner_right", innerR);
                gen.variant(state + ",shape=outer_left", outerL);
                gen.variant(state + ",shape=outer_right", outerR);
            }
            if (y == 270) y = 0;
            else y += 90;
        }
        return gen;
    }

    public static StateGen stairsAll(Identifier name, Identifier texName) {
        return stairsGeneric(
            name,
            InheritingModelGen.stairsAllInner(texName),
            InheritingModelGen.stairsAllOuter(texName),
            InheritingModelGen.stairsAll(texName)
        );
    }

    public static StateGen stairsSided(Identifier name, Identifier bottom, Identifier top, Identifier side) {
        return stairsGeneric(
            name,
            InheritingModelGen.stairsInner(bottom, top, side),
            InheritingModelGen.stairsOuter(bottom, top, side),
            InheritingModelGen.stairs(bottom, top, side)
        );
    }

    public static StateGen stairsColumn(Identifier name, Identifier end, Identifier side) {
        return stairsSided(name, end, end, side);
    }
}
