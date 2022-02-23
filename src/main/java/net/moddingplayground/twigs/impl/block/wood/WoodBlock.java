package net.moddingplayground.twigs.impl.block.wood;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SignBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Direction;
import net.moddingplayground.twigs.api.block.TwigsBlocks;
import net.moddingplayground.twigs.impl.block.vanilla.PublicDoorBlock;
import net.moddingplayground.twigs.impl.block.vanilla.PublicPressurePlateBlock;
import net.moddingplayground.twigs.impl.block.vanilla.PublicSaplingBlock;
import net.moddingplayground.twigs.impl.block.vanilla.PublicStairsBlock;
import net.moddingplayground.twigs.impl.block.vanilla.PublicTrapdoorBlock;
import net.moddingplayground.twigs.impl.block.vanilla.PublicWoodenButtonBlock;
import net.moddingplayground.twigs.mixin.BlocksInvoker;

import java.util.EnumSet;

import static net.minecraft.block.PressurePlateBlock.*;

public enum WoodBlock {
    PLANKS(WoodBlock::planks),
    SAPLING(WoodBlock::sapling),
    POTTED_SAPLING("potted_%s_sapling", BlockItemFactory.NONE, WoodBlock::pottedSapling),
    LOG(WoodBlock::log),
    STRIPPED_LOG("stripped_%s_log", WoodBlock::strippedLog),
    WOOD(WoodBlock::wood),
    STRIPPED_WOOD("stripped_%s_wood", WoodBlock::wood),
    LEAVES(WoodBlock::leaves),
    SLAB(WoodBlock::slab),
    FENCE(WoodBlock::fence),
    STAIRS(WoodBlock::stairs),
    BUTTON(WoodBlock::button),
    PRESSURE_PLATE(WoodBlock::pressurePlate),
    DOOR(WoodBlock::door),
    TRAPDOOR(WoodBlock::trapdoor),
    FENCE_GATE(WoodBlock::fenceGate),
    SIGN(WoodBlock::signItem, WoodBlock::sign),
    WALL_SIGN(BlockItemFactory.NONE, WoodBlock::wallSign);

    public final String id;
    public final String format;
    public final BlockItemFactory itemFactory;
    public final BlockFactory blockFactory;

    WoodBlock(String format, BlockItemFactory itemFactory, BlockFactory blockFactory) {
        this.id = this.name().toLowerCase();
        this.format = format;
        this.itemFactory = itemFactory;
        this.blockFactory = blockFactory;
    }

    WoodBlock(String format, BlockFactory blockFactory) {
        this(format, BlockItemFactory.DEFAULT, blockFactory);
    }

    WoodBlock(BlockItemFactory itemFactory, BlockFactory blockFactory) {
        this("%s_%s", itemFactory, blockFactory);
    }

    WoodBlock(BlockFactory blockFactory) {
        this(BlockItemFactory.DEFAULT, blockFactory);
    }

    public String getId() {
        return this.id;
    }

    public boolean isArtificial() {
        return !EnumSet.of(
            WoodBlock.LOG, WoodBlock.STRIPPED_LOG, WoodBlock.WOOD, WoodBlock.STRIPPED_WOOD,
            WoodBlock.SAPLING, WoodBlock.POTTED_SAPLING, WoodBlock.LEAVES
        ).contains(this);
    }

    public static Block planks(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        return new Block(
            FabricBlockSettings.of(materialSet.wood(), colorSet.lighter())
                               .strength(2.0f, 3.0f).sounds(soundSet.wood())
        );
    }

    public static Block sapling(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        return new PublicSaplingBlock(
            saplingGenerator,
            FabricBlockSettings.of(materialSet.sapling())
                               .noCollision().ticksRandomly()
                               .breakInstantly().sounds(soundSet.sapling())
        );
    }

    private static Block pottedSapling(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        Block sapling = ctx.get(SAPLING);
        return new FlowerPotBlock(
            sapling,
            FabricBlockSettings.of(materialSet.decoration())
                .breakInstantly().nonOpaque()
        );
    }

    public static Block log(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        return new PillarBlock(
            FabricBlockSettings.of(materialSet.wood(), state -> logColor(state, colorSet, false))
                               .strength(2.0f).sounds(soundSet.wood())
        );
    }

    public static Block strippedLog(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        return new PillarBlock(
            FabricBlockSettings.of(materialSet.wood(), state -> logColor(state, colorSet, true))
                               .strength(2.0f).sounds(soundSet.wood())
        );
    }

    public static Block wood(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        return new PillarBlock(
            FabricBlockSettings.of(materialSet.wood(), colorSet.darker())
                .strength(2.0f).sounds(soundSet.wood())
        );
    }

    public static Block leaves(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        return new LeavesBlock(
            FabricBlockSettings.of(materialSet.leaves())
                .strength(0.2f).ticksRandomly()
                .sounds(soundSet.leaves()).nonOpaque()
                .allowsSpawning(BlocksInvoker::invokeCanSpawnOnLeaves)
                .suffocates(TwigsBlocks::never)
                .blockVision(TwigsBlocks::never)
        );
    }

    public static Block slab(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        return new SlabBlock(
            FabricBlockSettings.of(materialSet.wood(), colorSet.lighter())
                .strength(2.0f, 3.0f).sounds(soundSet.wood())
        );
    }

    public static Block fence(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        return new FenceBlock(
            FabricBlockSettings.of(materialSet.wood(), colorSet.lighter())
                .strength(2.0f, 3.0f).sounds(soundSet.wood())
        );
    }

    public static Block stairs(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        Block planks = ctx.blocks.get(PLANKS);
        return new PublicStairsBlock(planks.getDefaultState(), FabricBlockSettings.copyOf(planks));
    }

    public static Block button(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        return new PublicWoodenButtonBlock(
            FabricBlockSettings.of(materialSet.decoration())
                .noCollision().strength(0.5f).sounds(soundSet.wood())
        );
    }

    public static Block pressurePlate(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        return new PublicPressurePlateBlock(
            activationRule,
            FabricBlockSettings.of(materialSet.wood(), colorSet.lighter())
                .noCollision().strength(0.5f).sounds(soundSet.wood())
        );
    }

    public static Block door(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        return new PublicDoorBlock(
            FabricBlockSettings.of(materialSet.wood(), colorSet.lighter())
                .strength(3.0f).sounds(soundSet.wood())
                .nonOpaque()
        );
    }

    public static Block trapdoor(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        return new PublicTrapdoorBlock(
            FabricBlockSettings.of(materialSet.wood(), colorSet.lighter())
                .strength(3.0f).sounds(soundSet.wood())
                .nonOpaque().allowsSpawning(TwigsBlocks::never)
        );
    }

    public static Block fenceGate(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        return new FenceGateBlock(
            FabricBlockSettings.of(materialSet.wood(), colorSet.lighter())
                .strength(2.0f, 3.0f).sounds(soundSet.wood())
        );
    }

    public static Item signItem(WoodSet ctx, Block block, Item.Settings settings) {
        Block sign = ctx.blocks.get(SIGN);
        Block wallSign = ctx.blocks.get(WALL_SIGN);
        return new SignItem(settings, sign, wallSign);
    }

    public static Block sign(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        return new SignBlock(
            FabricBlockSettings.of(materialSet.wood())
                .noCollision().strength(1.0f).sounds(soundSet.wood()),
            ctx.getOrCreateSignType()
        );
    }

    public static Block wallSign(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule) {
        Block sign = ctx.blocks.get(SIGN);
        return new WallSignBlock(FabricBlockSettings.copyOf(sign), ctx.getOrCreateSignType());
    }

    public static MapColor logColor(BlockState state, ColorSet colorSet, boolean stripped) {
        return !stripped && state.get(PillarBlock.AXIS) == Direction.Axis.Y ? colorSet.lighter() : colorSet.darker();
    }

    public record MaterialSet(Material wood, Material sapling, Material leaves, Material decoration) {}
    public record ColorSet(MapColor lighter, MapColor darker) {}
    public record SoundSet(BlockSoundGroup wood, BlockSoundGroup sapling, BlockSoundGroup leaves) {}

    @FunctionalInterface
    public interface BlockFactory {
        Block create(WoodSet ctx, MaterialSet materialSet, ColorSet colorSet, SoundSet soundSet, SaplingGenerator saplingGenerator, ActivationRule activationRule);
    }

    @FunctionalInterface
    public interface BlockItemFactory {
        BlockItemFactory NONE = (ctx, block, settings) -> null;
        BlockItemFactory DEFAULT = (ctx, block, settings) -> new BlockItem(block, settings);
        Item create(WoodSet ctx, Block block, Item.Settings settings);
    }
}
