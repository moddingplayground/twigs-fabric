package net.moddingplayground.twigs.impl.data;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.moddingplayground.frame.api.toymaker.v0.generator.model.InheritingModelGen;
import net.moddingplayground.frame.api.toymaker.v0.generator.model.ModelGen;
import net.moddingplayground.frame.api.toymaker.v0.generator.model.StateGen;
import net.moddingplayground.frame.api.toymaker.v0.generator.model.StateModelInfo;
import net.moddingplayground.frame.api.toymaker.v0.generator.model.block.AbstractStateModelGenerator;
import net.moddingplayground.frame.api.toymaker.v0.generator.model.block.VariantsStateGen;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.impl.block.wood.TwigsWoodSet;
import net.moddingplayground.twigs.impl.block.wood.WoodBlock;
import net.moddingplayground.twigs.impl.block.wood.WoodSet;

import java.util.ArrayList;
import java.util.function.Function;

import static net.moddingplayground.frame.api.toymaker.v0.generator.model.InheritingModelGen.leaves;
import static net.moddingplayground.frame.api.toymaker.v0.generator.model.InheritingModelGen.*;
import static net.moddingplayground.frame.api.toymaker.v0.generator.model.block.BuildingBlocks.fence;
import static net.moddingplayground.frame.api.toymaker.v0.generator.model.block.BuildingBlocks.slabAll;
import static net.moddingplayground.frame.api.toymaker.v0.generator.model.block.BuildingBlocks.stairsAll;
import static net.moddingplayground.frame.api.toymaker.v0.generator.model.block.BuildingBlocks.*;
import static net.moddingplayground.frame.api.toymaker.v0.generator.model.block.InteractiveBlocks.button;
import static net.moddingplayground.frame.api.toymaker.v0.generator.model.block.InteractiveBlocks.door;
import static net.moddingplayground.frame.api.toymaker.v0.generator.model.block.InteractiveBlocks.fenceGate;
import static net.moddingplayground.frame.api.toymaker.v0.generator.model.block.InteractiveBlocks.pressurePlate;
import static net.moddingplayground.frame.api.toymaker.v0.generator.model.block.InteractiveBlocks.trapdoor;
import static net.moddingplayground.frame.api.toymaker.v0.generator.model.block.ParticleOnlyModelGen.*;
import static net.moddingplayground.twigs.api.block.TwigsBlocks.*;
import static net.moddingplayground.twigs.impl.block.wood.WoodBlock.*;

public class StateModelGenerator extends AbstractStateModelGenerator {
    public StateModelGenerator(String modId) {
        super(modId);
    }

    @Override
    public void generate() {
        this.cubeAlls(
            BAMBOO_THATCH,
            CRACKED_BRICKS,
            MOSSY_BRICKS,
            SMOOTH_BASALT_BRICKS,
            COBBLESTONE_BRICKS,
            CRACKED_COBBLESTONE_BRICKS,
            MOSSY_COBBLESTONE_BRICKS,
            POLISHED_AMETHYST_BRICKS,
            CRACKED_POLISHED_AMETHYST_BRICKS,
            POLISHED_AMETHYST,
            CHISELED_POLISHED_AMETHYST,
            TWISTING_POLISHED_BLACKSTONE_BRICKS,
            WEEPING_POLISHED_BLACKSTONE_BRICKS,
            ROCKY_DIRT,
            POLISHED_TUFF,
            POLISHED_TUFF_BRICKS,
            CRACKED_POLISHED_TUFF_BRICKS,
            POLISHED_CALCITE,
            POLISHED_CALCITE_BRICKS,
            CRACKED_POLISHED_CALCITE_BRICKS,
            POLISHED_RHYOLITE,
            POLISHED_RHYOLITE_BRICKS,
            CRACKED_POLISHED_RHYOLITE_BRICKS,
            SCHIST,
            POLISHED_SCHIST,
            POLISHED_SCHIST_BRICKS,
            CRACKED_POLISHED_SCHIST_BRICKS,
            BLOODSTONE,
            POLISHED_BLOODSTONE,
            POLISHED_BLOODSTONE_BRICKS,
            CRACKED_POLISHED_BLOODSTONE_BRICKS
        );

        this.paperLanterns(
            PAPER_LANTERN,
            ALLIUM_PAPER_LANTERN,
            BLUE_ORCHID_PAPER_LANTERN,
            CRIMSON_ROOTS_PAPER_LANTERN,
            DANDELION_PAPER_LANTERN
        );

        this.pillars(
            BUNDLED_BAMBOO,
            STRIPPED_BUNDLED_BAMBOO,
            POLISHED_BASALT_BRICKS,
            RHYOLITE,
            COPPER_PILLAR,
            EXPOSED_COPPER_PILLAR,
            WEATHERED_COPPER_PILLAR,
            OXIDIZED_COPPER_PILLAR
        );

        this.waxedPillars(
            WAXED_COPPER_PILLAR,
            WAXED_EXPOSED_COPPER_PILLAR,
            WAXED_OXIDIZED_COPPER_PILLAR,
            WAXED_WEATHERED_COPPER_PILLAR
        );

        this.wallAlls(BLOODSTONE_WALL, SCHIST_WALL);
        this.wallColumns(RHYOLITE_WALL);
        this.wallAllsVanilla(TUFF_WALL, CALCITE_WALL);

        this.wallAllPlurals(
            MOSSY_BRICK_WALL,
            COBBLESTONE_BRICK_WALL,
            MOSSY_COBBLESTONE_BRICK_WALL,
            SMOOTH_BASALT_BRICK_WALL,
            POLISHED_AMETHYST_BRICK_WALL,
            TWISTING_POLISHED_BLACKSTONE_BRICK_WALL,
            WEEPING_POLISHED_BLACKSTONE_BRICK_WALL,
            POLISHED_TUFF_BRICK_WALL,
            POLISHED_CALCITE_BRICK_WALL,
            POLISHED_RHYOLITE_BRICK_WALL,
            POLISHED_SCHIST_BRICK_WALL,
            POLISHED_BLOODSTONE_BRICK_WALL
        );

        this.cubeColumns(CRIMSON_SHROOMLAMP, WARPED_SHROOMLAMP);

        this.stairsAlls(
            BAMBOO_THATCH_STAIRS,
            POLISHED_AMETHYST_STAIRS,
            POLISHED_TUFF_STAIRS,
            POLISHED_CALCITE_STAIRS,
            POLISHED_RHYOLITE_STAIRS,
            SCHIST_STAIRS,
            POLISHED_SCHIST_STAIRS,
            BLOODSTONE_STAIRS,
            POLISHED_BLOODSTONE_STAIRS
        );

        this.stairsAllsPlural(
            MOSSY_BRICK_STAIRS,
            SMOOTH_BASALT_BRICK_STAIRS,
            COBBLESTONE_BRICK_STAIRS,
            MOSSY_COBBLESTONE_BRICK_STAIRS,
            POLISHED_AMETHYST_BRICK_STAIRS,
            TWISTING_POLISHED_BLACKSTONE_BRICK_STAIRS,
            WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS,
            POLISHED_TUFF_BRICK_STAIRS,
            POLISHED_CALCITE_BRICK_STAIRS,
            POLISHED_RHYOLITE_BRICK_STAIRS,
            POLISHED_SCHIST_BRICK_STAIRS,
            POLISHED_BLOODSTONE_BRICK_STAIRS
        );

        this.stairsColumns(RHYOLITE_STAIRS);
        this.stairsAllsVanilla(TUFF_STAIRS, CALCITE_STAIRS);

        this.slabAlls(
            BAMBOO_THATCH_SLAB,
            POLISHED_AMETHYST_SLAB,
            POLISHED_TUFF_SLAB,
            POLISHED_CALCITE_SLAB,
            POLISHED_RHYOLITE_SLAB,
            SCHIST_SLAB,
            POLISHED_SCHIST_SLAB,
            BLOODSTONE_SLAB,
            POLISHED_BLOODSTONE_SLAB
        );


        this.slabAllsPlural(
            MOSSY_BRICK_SLAB,
            SMOOTH_BASALT_BRICK_SLAB,
            COBBLESTONE_BRICK_SLAB,
            MOSSY_COBBLESTONE_BRICK_SLAB,
            POLISHED_AMETHYST_BRICK_SLAB,
            TWISTING_POLISHED_BLACKSTONE_BRICK_SLAB,
            WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB,
            POLISHED_TUFF_BRICK_SLAB,
            POLISHED_CALCITE_BRICK_SLAB,
            POLISHED_RHYOLITE_BRICK_SLAB,
            POLISHED_SCHIST_BRICK_SLAB,
            POLISHED_BLOODSTONE_BRICK_SLAB
        );

        this.slabColumns(RHYOLITE_SLAB);
        this.slabAllsVanilla(TUFF_SLAB, CALCITE_SLAB);

        this.add(STRIPPED_BAMBOO, b -> axisRotated(name(b), quadPole(Identifier.tryParse(name(b) + "_stalk"))));
        this.woods(WOOD_SETS.toArray(TwigsWoodSet[]::new));

        this.add(AZALEA_FLOWERS, b -> wallPlant(name(b), name(b)));
        this.add(POTTED_AZALEA_FLOWERS, this::flowerPot);

        this.add(STRIPPED_BAMBOO_MAT, b -> simple(name(b), mat(name(b))));

        this.add(CHISELED_BRICKS, b -> simple(name(b), cubeColumn(Identifier.tryParse(name(b) + "_top"), name(b))));
        this.lamps(LAMP, SOUL_LAMP);
        this.varyingFloorLayer(TWIG, PEBBLE);
    }

    public void woods(WoodSet... sets) {
        for (WoodSet set : sets) wood(set);
    }

    public void wood(WoodSet set) {
        if (!set.isVanilla()) {
            wood(set, PLANKS, this::cubeAll);
            wood(set, SAPLING, b -> simple(name(b), cross(name(b))));
            wood(set, POTTED_SAPLING, this::flowerPotCross);
            wood(set, LOG, block -> axisRotated(name(block), cubeColumn(name(block, "block/%s_top"), name(block))));
            wood(set, STRIPPED_LOG, block -> axisRotated(name(block), cubeColumn(name(block, "block/%s_top"), name(block))));
            wood(set, WOOD, block -> axisRotated(name(block), InheritingModelGen.cubeAll(name(block, "block/%s_log", "_wood"))));
            wood(set, STRIPPED_WOOD, block -> axisRotated(name(block), InheritingModelGen.cubeAll(name(block, "block/%s_log", "_wood"))));
            wood(set, LEAVES, block -> simple(name(block), leaves(name(block))));
            wood(set, SLAB, block -> slabAll(name(block), name(block, "block/%s_planks", "_slab"), name(block, "block/%s_planks", "_slab")));
            wood(set, STAIRS, block -> stairsAll(name(block), name(block, "block/%s_planks", "_stairs")));
            wood(set, FENCE, block -> fence(name(block), name(block, "block/%s_planks", "_fence")));
            wood(set, DOOR, block -> door(name(block)));
            wood(set, TRAPDOOR, block -> trapdoor(name(block)));
            wood(set, FENCE_GATE, block -> fenceGate(name(block), name(block, "block/%s_planks", "_fence_gate")));
            wood(set, BUTTON, block -> button(name(block), name(block, "block/%s_planks", "_button")));
            wood(set, PRESSURE_PLATE, block -> pressurePlate(name(block), name(block, "block/%s_planks", "_pressure_plate")));
            wood(set, SIGN, block -> simple(name(block), particles(name(block, "block/%s_planks", "_sign"))));
            wood(set, WALL_SIGN, block -> simple(name(block, "block/%s_sign", "_wall_sign"), null)); // same particle-only model as floor sign, avoid double generation
        }
        if (set instanceof TwigsWoodSet twigs) this.tables(twigs.getTable());
    }

    public void wood(WoodSet set, WoodBlock wood, Function<Block, StateGen> factory) {
        if (set.contains(wood)) this.add(set.get(wood), factory);
    }

    public static InheritingModelGen quadPole(Identifier texture) {
        return new InheritingModelGen(new Identifier(Twigs.MOD_ID, "block/template_quad_pole"))
            .texture("texture", texture);
    }

    public static InheritingModelGen mat(Identifier texture) {
        return new InheritingModelGen(new Identifier(Twigs.MOD_ID, "block/template_mat"))
            .texture("texture", texture);
    }

    private StateGen flowerPot(Block block) {
        return simple(name(block), InheritingModelGen.flowerPotCross(name(block, "block/%s_pot", "(^potted_)", "")));
    }

    private StateGen flowerPotCross(Block block) {
        return simple(name(block), InheritingModelGen.flowerPotCross(name(block, "block/%s", "(^potted_)", "")));
    }

    private void paperLanterns(Block... blocks) {
        Identifier top = Identifier.tryParse(name(PAPER_LANTERN) + "_top");
        Identifier bottom = Identifier.tryParse(name(PAPER_LANTERN) + "_bottom");
        for (Block block : blocks) {
            this.add(block, b -> {
                Identifier n = name(b);
                Identifier side = Identifier.tryParse(n + "_side");
                return VariantsStateGen.variants()
                                       .variant("hanging=true", StateModelInfo.create(Identifier.tryParse(n + "_hanging"), paperLanternHanging(bottom, top, side)))
                                       .variant("hanging=false", StateModelInfo.create(n, paperLantern(bottom, top, side)));
            });
        }
    }

    public static InheritingModelGen paperLantern(Identifier bottom, Identifier top, Identifier side) {
        return new InheritingModelGen(new Identifier(Twigs.MOD_ID, "block/template_paper_lantern"))
            .texture("bottom", bottom)
            .texture("top", top)
            .texture("side", side);
    }

    public static InheritingModelGen paperLanternHanging(Identifier bottom, Identifier top, Identifier side) {
        return new InheritingModelGen(new Identifier(Twigs.MOD_ID, "block/template_paper_lantern_hanging"))
            .texture("bottom", bottom)
            .texture("top", top)
            .texture("side", side);
    }

    public void varyingFloorLayer(Block... blocks) {
        for (Block block : blocks) {
            this.add(block, b -> {
                ArrayList<StateModelInfo> list = new ArrayList<>();
                for (int i = 0; i <= 2; i++) {
                    Identifier n = Identifier.tryParse(name(b) + String.valueOf(i));
                    ModelGen model = floorLayer(n);
                    list.add(StateModelInfo.create(n, model));
                    list.add(StateModelInfo.create(n, model).rotate(0, 90));
                    list.add(StateModelInfo.create(n, model).rotate(0, 180));
                    list.add(StateModelInfo.create(n, model).rotate(0, 270));
                }
                return VariantsStateGen.variants(list.toArray(StateModelInfo[]::new));
            });
        }
    }

    public static InheritingModelGen floorLayer(Identifier texture) {
        return new InheritingModelGen(new Identifier(Twigs.MOD_ID, "block/template_floor_layer"))
            .texture("texture", texture);
    }

    public void tables(Block... blocks) {
        for (Block block : blocks) {
            this.add(block, b -> {
                Identifier n = name(b);
                return facingHorizontalRotated(n, table(Identifier.tryParse(n + "_bottom"), Identifier.tryParse(n + "_top"), n));
            });
        }
    }

    public static InheritingModelGen table(Identifier bottom, Identifier top, Identifier side) {
        return new InheritingModelGen(new Identifier(Twigs.MOD_ID, "block/template_table"))
            .texture("bottom", bottom)
            .texture("top", top)
            .texture("side", side);
    }

    public void lamps(Block... blocks) {
        for (Block block : blocks) {
            this.add(block, b -> {
                Identifier n = name(b);
                return lamp(n, Identifier.tryParse(n + "_bottom"), Identifier.tryParse(n + "_top"), Identifier.tryParse(n + "_side"));
            });
        }
    }

    public static StateGen lamp(Identifier name, Identifier bottom, Identifier top, Identifier side) {
        return VariantsStateGen.variants()
            .variant("lit=true", StateModelInfo.create(name, cubeBottomTop(bottom, top, side)))
            .variant("lit=false", StateModelInfo.create(Identifier.tryParse(name + "_off"), cubeBottomTop(bottom, top, Identifier.tryParse(side + "_off"))));
    }
}
