package net.moddingplayground.twigs.datagen.impl.generator.model.block;

import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.moddingplayground.twigs.datagen.impl.generator.model.InheritingModelGen;
import net.moddingplayground.twigs.datagen.impl.generator.model.ModelGen;
import net.moddingplayground.twigs.datagen.impl.generator.model.StateGen;
import net.moddingplayground.twigs.datagen.impl.generator.model.StateModelInfo;

@SuppressWarnings("unused")
public final class InteractiveBlocks {
    public static StateGen door(Identifier name) {
        VariantsStateGen gen = VariantsStateGen.variants();
        Identifier bottomTexture = Identifier.tryParse(name + "_bottom");
        Identifier topTexture = Identifier.tryParse(name + "_top");

        ModelGen[] gens = {
            InheritingModelGen.doorBottom(bottomTexture, false),
            InheritingModelGen.doorTop(topTexture, bottomTexture, false),
            InheritingModelGen.doorBottom(bottomTexture, true),
            InheritingModelGen.doorTop(topTexture, bottomTexture, true)
        };

        for (Direction facing : Direction.Type.HORIZONTAL) {
            int baseRot = 0;

            if (facing == Direction.SOUTH) baseRot = 90;
            if (facing == Direction.WEST) baseRot = 180;
            if (facing == Direction.NORTH) baseRot = 270;

            for (DoubleBlockHalf half : DoubleBlockHalf.values()) {
                for (DoorHinge hinge : DoorHinge.values()) {
                    boolean right = hinge == DoorHinge.RIGHT;

                    for (int i = 0; i < 2; i++) {
                        boolean open = (i & 1) == 0;

                        int rotDelta = 0;
                        if (open) rotDelta = right ? -90 : 90;

                        int rotation = (baseRot + rotDelta) % 360;
                        if (rotation < 0) rotation += 360;

                        String variant = "facing=" + facing.asString() + ","
                            + "half=" + half.asString() + ","
                            + "hinge=" + hinge.asString() + ","
                            + "open=" + open;

                        boolean bottom = half == DoubleBlockHalf.LOWER;

                        boolean shouldHinge = right ^ open;

                        Identifier model = Identifier.tryParse(name + (bottom ? "_bottom" : "_top") + (shouldHinge ? "_hinge" : ""));

                        int gi = (shouldHinge ? 2 : 0) + (bottom ? 0 : 1);
                        ModelGen modelGen = gens[gi];
                        gens[gi] = null;

                        gen.variant(variant, StateModelInfo.create(model, modelGen).rotate(0, rotation));
                    }
                }
            }
        }

        return gen;
    }

    @SuppressWarnings("ConstantConditions")
    public static StateGen trapdoor(Identifier name) {
        VariantsStateGen gen = VariantsStateGen.variants();

        ModelGen[] gens = {
            InheritingModelGen.trapdoorOpen(name),
            InheritingModelGen.trapdoorTop(name),
            InheritingModelGen.trapdoorBottom(name)
        };

        String[] names = {
            name + "_open",
            name + "_top",
            name + "_bottom"
        };

        for (Direction facing : Direction.Type.HORIZONTAL) {
            int baseRot = 0;

            if (facing == Direction.EAST) baseRot = 90;
            if (facing == Direction.SOUTH) baseRot = 180;
            if (facing == Direction.WEST) baseRot = 270;

            for (BlockHalf half : BlockHalf.values()) {
                for (int i = 0; i < 2; i++) {
                    boolean open = (i & 1) == 0;
                    boolean top = half == BlockHalf.TOP;

                    int xr = open && top ? 180 : 0;
                    int yr = open && top ? 180 : 0;

                    yr = (baseRot + yr) % 360;
                    if (yr < 0) yr += 360;


                    String variant = "facing=" + facing.asString() + ","
                        + "half=" + half.asString() + ","
                        + "open=" + open;

                    int gi = open ? 0 : top ? 1 : 2;
                    ModelGen modelGen = gens[gi];
                    gens[gi] = null;

                    gen.variant(variant, StateModelInfo.create(Identifier.tryParse(names[gi]), modelGen).rotate(xr, yr));
                }
            }
        }

        return gen;
    }

    public static StateGen fenceGate(Identifier name, Identifier texName) {
        VariantsStateGen gen = VariantsStateGen.variants();

        ModelGen[] gens = {
            InheritingModelGen.fenceGate(texName, false),
            InheritingModelGen.fenceGate(texName, true),
            InheritingModelGen.fenceGateWall(texName, false),
            InheritingModelGen.fenceGateWall(texName, true)
        };

        String[] names = {
            name.toString(),
            name + "_open",
            name + "_wall",
            name + "_wall_open"
        };

        for (Direction facing : Direction.Type.HORIZONTAL) {
            int baseRot = 0;

            if (facing == Direction.WEST) baseRot = 90;
            if (facing == Direction.NORTH) baseRot = 180;
            if (facing == Direction.EAST) baseRot = 270;

            for (int i = 0; i < 4; i++) {
                boolean open = (i & 1) != 0;
                boolean inWall = (i & 2) != 0;

                String variant = "facing=" + facing.asString() + ","
                    + "in_wall=" + inWall + ","
                    + "open=" + open;

                ModelGen modelGen = gens[i];
                gens[i] = null;

                gen.variant(variant, StateModelInfo.create(Identifier.tryParse(names[i]), modelGen).rotate(0, baseRot).uvlock(true));
            }
        }

        return gen;
    }

    public static StateGen button(Identifier name, Identifier texName) {
        VariantsStateGen gen = VariantsStateGen.variants();

        ModelGen[] gens = {
            InheritingModelGen.button(texName, false),
            InheritingModelGen.button(texName, true)
        };

        String[] names = {
            name.toString(),
            name + "_pressed"
        };

        for (Direction facing : Direction.Type.HORIZONTAL) {
            for (WallMountLocation face : WallMountLocation.values()) {
                int yr = 0;
                int xr = 0;

                if (face == WallMountLocation.CEILING) {
                    xr = 180;
                    if (facing == Direction.WEST) yr = 90;
                    if (facing == Direction.NORTH) yr = 180;
                    if (facing == Direction.EAST) yr = 270;
                } else {
                    if (face == WallMountLocation.WALL) xr = 90;
                    if (facing == Direction.EAST) yr = 90;
                    if (facing == Direction.SOUTH) yr = 180;
                    if (facing == Direction.WEST) yr = 270;
                }

                for (int i = 0; i < 2; i++) {
                    boolean powered = (i & 1) != 0;

                    String variant = "face=" + face.asString() + ","
                        + "facing=" + facing.asString() + ","
                        + "powered=" + powered;

                    ModelGen modelGen = gens[i];
                    gens[i] = null;

                    gen.variant(variant, StateModelInfo.create(Identifier.tryParse(names[i]), modelGen).rotate(xr, yr).uvlock(xr == 90));
                }
            }
        }

        return gen;
    }

    public static StateGen pressurePlate(Identifier name, Identifier texName) {
        ModelGen up = InheritingModelGen.pressurePlate(texName, false);
        ModelGen down = InheritingModelGen.pressurePlate(texName, true);
        return VariantsStateGen.variants("powered=false", StateModelInfo.create(name, up))
                               .variant("powered=true", StateModelInfo.create(Identifier.tryParse(name + "_down"), down));
    }
}
