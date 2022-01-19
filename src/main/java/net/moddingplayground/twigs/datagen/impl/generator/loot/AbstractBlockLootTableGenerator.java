package net.moddingplayground.twigs.datagen.impl.generator.loot;

import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionConsumingBuilder;
import net.minecraft.loot.condition.SurvivesExplosionLootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.DynamicEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.CopyNameLootFunction;
import net.minecraft.loot.function.CopyNbtLootFunction;
import net.minecraft.loot.function.CopyStateFunction;
import net.minecraft.loot.function.ExplosionDecayLootFunction;
import net.minecraft.loot.function.LimitCountLootFunction;
import net.minecraft.loot.function.LootFunctionConsumingBuilder;
import net.minecraft.loot.function.SetContentsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.nbt.ContextLootNbtProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.datagen.impl.mixin.BlockLootTableGeneratorAccessor;
import net.moddingplayground.twigs.datagen.impl.mixin.BlockLootTableGeneratorInvoker;

@SuppressWarnings("unused")
public abstract class AbstractBlockLootTableGenerator extends AbstractLootTableGenerator<Block> {
    public AbstractBlockLootTableGenerator(String modId) {
        super(modId);
    }

    @Override
    public Registry<Block> getRegistry() {
        return Registry.BLOCK;
    }

    public static <T> T explosionFunc(ItemConvertible drop, LootFunctionConsumingBuilder<T> builder) {
        return !BlockLootTableGeneratorAccessor.getExplosionImmune().contains(drop.asItem())
            ? builder.apply(ExplosionDecayLootFunction.builder())
            : builder.getThis();
    }

    public static <T> T explosionCond(ItemConvertible drop, LootConditionConsumingBuilder<T> builder) {
        return !BlockLootTableGeneratorAccessor.getExplosionImmune().contains(drop.asItem())
            ? builder.conditionally(SurvivesExplosionLootCondition.builder())
            : builder.getThis();
    }

    public LootTable.Builder drops(ItemConvertible drop) {
        return LootTable.builder().pool(
            explosionCond(
                drop,
                LootPool.builder().rolls(count(1))
                        .with(ItemEntry.builder(drop))
            )
        );
    }

    public LootTable.Builder drops(ItemConvertible drop, LootNumberProvider count) {
        return LootTable.builder().pool(
            pool().with(explosionFunc(drop, ItemEntry.builder(drop).apply(setCount(count))))
        );
    }

    public LootTable.Builder dropsWithSilkTouch(ItemConvertible drop, LootPoolEntry.Builder<?> orElse) {
        return dropsConditionally(drop, BlockLootTableGeneratorAccessor.getWithSilkTouch(), orElse);
    }

    public LootTable.Builder dropsWithShears(ItemConvertible drop, LootPoolEntry.Builder<?> orElse) {
        return dropsConditionally(drop, BlockLootTableGeneratorAccessor.getWithShears(), orElse);
    }

    public LootTable.Builder dropsWithSilkTouchOrShears(ItemConvertible drop, LootPoolEntry.Builder<?> orElse) {
        return dropsConditionally(drop, BlockLootTableGeneratorAccessor.getWithSilkTouchOrShears(), orElse);
    }

    public LootTable.Builder dropsWithSilkTouch(ItemConvertible drop, ItemConvertible orElse) {
        return dropsWithSilkTouch(drop, explosionCond(drop, ItemEntry.builder(orElse)));
    }

    public LootTable.Builder dropsWithSilkTouch(ItemConvertible drop, ItemConvertible orElse, LootNumberProvider elseCount) {
        return dropsWithSilkTouch(drop, explosionFunc(drop, ItemEntry.builder(orElse).apply(setCount(elseCount))));
    }

    public LootTable.Builder dropsWithSilkTouch(ItemConvertible drop) {
        return LootTable.builder().pool(
            pool().conditionally(BlockLootTableGeneratorAccessor.getWithSilkTouch())
                  .with(ItemEntry.builder(drop))
        );
    }

    public LootTable.Builder dropsFlowerPotWithPlant(ItemConvertible plant) {
        return LootTable.builder().pool(
            explosionCond(
                Blocks.FLOWER_POT,
                pool().with(ItemEntry.builder(Blocks.FLOWER_POT))
            )
        ).pool(
            explosionCond(
                plant,
                pool().with(ItemEntry.builder(plant))
            )
        );
    }

    public LootTable.Builder dropsSlab(Block slab) {
        return LootTable.builder().pool(
            pool().with(explosionFunc(
                slab,
                ItemEntry.builder(slab).apply(
                    setCount(count(2)).conditionally(
                        stateCond(slab, SlabBlock.TYPE, SlabType.DOUBLE)
                    )
                )
            )));
    }

    public static <T extends Comparable<T>> LootTable.Builder dropsWithProperty(Block drop, Property<T> prop, T val) {
        return LootTable.builder().pool(
            explosionCond(
                drop,
                pool().with(ItemEntry.builder(drop).conditionally(stateCond(drop, prop, val)))
            )
        );
    }

    public LootTable.Builder dropsNamedContainer(Block drop) {
        return LootTable.builder().pool(explosionCond(
            drop, pool().with(
                ItemEntry.builder(drop)
                         .apply(CopyNameLootFunction.builder(CopyNameLootFunction.Source.BLOCK_ENTITY))
            )
        ));
    }

    public LootTable.Builder dropsShulkerBox(Block drop) {
        return LootTable.builder().pool(explosionCond(
            drop, pool().with(
                ItemEntry.builder(drop)
                         .apply(CopyNameLootFunction.builder(CopyNameLootFunction.Source.BLOCK_ENTITY))
                         .apply(CopyNbtLootFunction.builder(ContextLootNbtProvider.BLOCK_ENTITY)
                                                   .withOperation("Lock", "BlockEntityTag.Lock")
                                                   .withOperation("LootTable", "BlockEntityTag.LootTable")
                                                   .withOperation("LootTableSeed", "BlockEntityTag.LootTableSeed")
                         )
                         .apply(SetContentsLootFunction.builder(BlockEntityType.SHULKER_BOX)
                                                       .withEntry(DynamicEntry.builder(ShulkerBoxBlock.CONTENTS))
                         )
            )
        ));
    }

    public LootTable.Builder dropsBanner(Block drop) {
        return LootTable.builder().pool(explosionCond(
            drop, pool().with(
                ItemEntry.builder(drop)
                         .apply(CopyNameLootFunction.builder(CopyNameLootFunction.Source.BLOCK_ENTITY))
                         .apply(CopyNbtLootFunction.builder(ContextLootNbtProvider.BLOCK_ENTITY)
                                                   .withOperation("Patterns", "BlockEntityTag.Patterns")
                         )
            )
        ));
    }

    public LootTable.Builder oreDrops(Block dropWithSilkTouch, Item drop) {
        return BlockLootTableGeneratorInvoker.invoke_oreDrops(dropWithSilkTouch, drop);
    }
    public LootTable.Builder dropsCopperOre(Block ore) {
        return BlockLootTableGeneratorInvoker.invoke_copperOreDrops(ore);
    }
    public LootTable.Builder dropsRedstoneOre(Block ore) {
        return BlockLootTableGeneratorInvoker.invoke_redstoneOreDrops(ore);
    }
    public LootTable.Builder dropsLapisOre(Block ore) {
        return BlockLootTableGeneratorInvoker.invoke_lapisOreDrops(ore);
    }

    public LootTable.Builder dropsBeeNest(Block drop) {
        return LootTable.builder().pool(
            pool().conditionally(BlockLootTableGeneratorAccessor.getWithSilkTouch()).with(
                ItemEntry.builder(drop)
                         .apply(CopyNbtLootFunction.builder(ContextLootNbtProvider.BLOCK_ENTITY)
                                                   .withOperation("Bees", "BlockEntityTag.Bees")
                         )
                         .apply(CopyStateFunction.builder(drop).addProperty(BeehiveBlock.HONEY_LEVEL))
            )
        );
    }

    public LootTable.Builder dropsBeehive(Block drop) {
        return LootTable.builder().pool(
            pool().with(
                ItemEntry.builder(drop)
                         .conditionally(BlockLootTableGeneratorAccessor.getWithSilkTouch())
                         .apply(CopyNbtLootFunction.builder(ContextLootNbtProvider.BLOCK_ENTITY)
                                                   .withOperation("Bees", "BlockEntityTag.Bees")
                         )
                         .apply(CopyStateFunction.builder(drop).addProperty(BeehiveBlock.HONEY_LEVEL))
                         .alternatively(ItemEntry.builder(drop))
            )
        );
    }

    public LootTable.Builder dropsMineral(ItemConvertible ore, ItemConvertible mineral) {
        return dropsWithSilkTouch(
            ore,
            explosionFunc(
                ore,
                ItemEntry.builder(mineral)
                         .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
            )
        );
    }

    public LootTable.Builder dropsMushroomBlock(Block block, ItemConvertible mushroom) {
        return dropsWithSilkTouch(
            block,
            explosionFunc(
                block,
                ItemEntry.builder(mushroom)
                         .apply(setCount(countRandom(-6, 2)))
                         .apply(LimitCountLootFunction.builder(atLeast(0)))
            )
        );
    }

    public LootTable.Builder dropsGrass(Block grass) {
        return dropsWithShears(
            grass,
            explosionFunc(
                grass,
                ItemEntry.builder(Items.WHEAT_SEEDS)
                         .conditionally(chance(0.125f))
                         .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE, 2))
            )
        );
    }

    public LootTable.Builder dropsSeagrass(Block seagrass) {
        return LootTable.builder()
                        .pool(
                            LootPool.builder()
                                    .conditionally(BlockLootTableGeneratorAccessor.getWithShears())
                                    .with(
                                        ItemEntry.builder(seagrass)
                                                 .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))
                                    )
                        );
    }

    public LootTable.Builder dropsCropStem(Block stem, ItemConvertible seed) {
        return LootTable.builder().pool(explosionFunc(
            stem, pool().with(
                ItemEntry.builder(seed)
                         .apply(setCount(countBiased(3, 1 / 15f)).conditionally(stateCond(stem, StemBlock.AGE, 0)))
                         .apply(setCount(countBiased(3, 1 / 7.5f)).conditionally(stateCond(stem, StemBlock.AGE, 1)))
                         .apply(setCount(countBiased(3, 1 / 5f)).conditionally(stateCond(stem, StemBlock.AGE, 2)))
                         .apply(setCount(countBiased(3, 1 / 3.75f)).conditionally(stateCond(stem, StemBlock.AGE, 3)))
                         .apply(setCount(countBiased(3, 1 / 3f)).conditionally(stateCond(stem, StemBlock.AGE, 4)))
                         .apply(setCount(countBiased(3, 1 / 2.5f)).conditionally(stateCond(stem, StemBlock.AGE, 5)))
                         .apply(setCount(countBiased(3, 1 / 2.142857f)).conditionally(stateCond(stem, StemBlock.AGE, 6)))
                         .apply(setCount(countBiased(3, 1 / 1.875f)).conditionally(stateCond(stem, StemBlock.AGE, 7)))
            )
        ));
    }

    public LootTable.Builder dropsCropStemAttached(Block stem, ItemConvertible seed) {
        return LootTable.builder().pool(explosionFunc(
            stem, pool().with(
                ItemEntry.builder(seed).apply(setCount(countBiased(3, 1 / 1.875f)))
            )
        ));
    }

    public LootTable.Builder dropsWithShears(ItemConvertible drop) {
        return LootTable.builder().pool(
            pool().conditionally(BlockLootTableGeneratorAccessor.getWithShears())
                  .with(ItemEntry.builder(drop))
        );
    }

    public LootTable.Builder dropLeaves(Block leaves, ItemConvertible sapling, float... chance) {
        return dropsWithSilkTouchOrShears(
            leaves,
            explosionCond(leaves, ItemEntry.builder(sapling))
                .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, chance))
        ).pool(
            pool().conditionally(BlockLootTableGeneratorAccessor.getWithoutSilkTouchNorShears()).with(
                explosionFunc(
                    leaves,
                    ItemEntry.builder(Items.STICK)
                             .apply(setCount(countRandom(1, 2)))
                ).conditionally(
                    TableBonusLootCondition.builder(
                        Enchantments.FORTUNE,
                        1 / 50f, 1 / 45f, 1 / 40f, 1 / 30f, 1 / 10f
                    )
                )
            )
        );
    }

    public LootTable.Builder dropLeavesExtra(Block leaves, ItemConvertible sapling, ItemConvertible extra, float... chance) {
        return dropLeaves(leaves, sapling, chance).pool(
            pool().conditionally(BlockLootTableGeneratorAccessor.getWithoutSilkTouchNorShears()).with(
                explosionCond(leaves, ItemEntry.builder(extra)).conditionally(
                    TableBonusLootCondition.builder(
                        Enchantments.FORTUNE,
                        1 / 200f, 1 / 180f, 1 / 160f, 1 / 120f, 1 / 40f
                    )
                )
            )
        );
    }

    public LootTable.Builder dropsCrop(Block crop, ItemConvertible product, ItemConvertible seeds, LootCondition.Builder condition) {
        return explosionFunc(crop, LootTable.builder().pool(
            pool().with(
                ItemEntry.builder(product)
                         .conditionally(condition)
                         .alternatively(ItemEntry.builder(seeds))
            )
        ).pool(
            LootPool.builder()
                    .conditionally(condition)
                    .with(ItemEntry.builder(seeds).apply(
                        ApplyBonusLootFunction.binomialWithBonusCount(Enchantments.FORTUNE, 1 / 1.75f, 3)
                    ))
        ));
    }

    public LootTable.Builder dropsDoublePlant(ItemConvertible drop) {
        return LootTable.builder().pool(
            pool().conditionally(BlockLootTableGeneratorAccessor.getWithShears())
                  .with(ItemEntry.builder(drop).apply(setCount(count(2))))
        );
    }

    public LootTable.Builder dropsDoubleGrass(Block plant, ItemConvertible shearDrops) {
        LootPoolEntry.Builder<?> seeds
            = ItemEntry.builder(shearDrops)
                       .apply(setCount(count(2)))
                       .conditionally(BlockLootTableGeneratorAccessor.getWithShears())
                       .alternatively(
                           explosionCond(plant, ItemEntry.builder(Items.WHEAT_SEEDS))
                               .conditionally(chance(1 / 8f))
                       );

        return LootTable.builder().pool(
            pool().with(seeds)
                  .conditionally(stateCond(plant, TallPlantBlock.HALF, DoubleBlockHalf.LOWER))
                  .conditionally(checkDoublePlantHalf(plant, DoubleBlockHalf.UPPER))
        ).pool(
            pool().with(seeds)
                  .conditionally(stateCond(plant, TallPlantBlock.HALF, DoubleBlockHalf.UPPER))
                  .conditionally(checkDoublePlantHalf(plant, DoubleBlockHalf.LOWER))
        );
    }

    public static LocationCheckLootCondition.Builder checkDoublePlantHalf(Block plant, DoubleBlockHalf half) {
        int d = half == DoubleBlockHalf.UPPER ? 1 : -1;
        StatePredicate expectState = stateProp(TallPlantBlock.HALF, half).build();

        return LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create().block(
                BlockPredicate.Builder.create().blocks(plant).state(expectState).build()
            ),
            new BlockPos(0, d, 0)
        );
    }

    public LootTable.Builder dropsDoor(Block block) {
        return dropsWithProperty(block, DoorBlock.HALF, DoubleBlockHalf.LOWER);
    }

    public void addPottedPlantDrop(Block block) {
        this.add(block, blockx -> dropsFlowerPotWithPlant(((FlowerPotBlock) blockx).getContent()));
    }

    public void addDropWithSilkTouch(Block block, Block drop) {
        this.add(block, dropsWithSilkTouch(drop));
    }

    public void add(Block block, ItemConvertible drop) {
        this.add(block, drops(drop));
    }

    public void add(Block block) {
        this.add(block, block);
    }

    public void addDropWithSilkTouch(Block block) {
        this.addDropWithSilkTouch(block, block);
    }

    public void addSlabDrop(Block block) {
        this.add(block, this::dropsSlab);
    }
}
