package net.moddingplayground.twigs.impl.block;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BambooBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.BambooLeaves;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.InvertedLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.frame.api.contentregistries.v0.StateRegistry;
import net.moddingplayground.twigs.api.block.StrippedBambooBlock;
import net.moddingplayground.twigs.api.block.TwigsBlocks;
import net.moddingplayground.twigs.api.item.TwigsItems;
import net.moddingplayground.twigs.api.sound.TwigsSoundEvents;
import net.moddingplayground.twigs.mixin.ItemEntryAccessor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import static net.minecraft.block.Blocks.*;

public final class TwigsBlocksImpl implements TwigsBlocks, ModInitializer {
    @Override
    public void onInitialize() {
        /* State Registries */

        StateRegistry.BLOCK_ENTITY_SUPPORTS.apply(BlockEntityType.SIGN).add(
            STRIPPED_BAMBOO_SIGN,
            STRIPPED_BAMBOO_WALL_SIGN
        );

        /* Block Usage */

        UseBlockCallback.EVENT.register((player, world, hand, hit) -> {
            ItemStack stack = player.getStackInHand(hand);
            BlockPos pos = hit.getBlockPos();
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            return Util.<Optional<BlockState>>make(() -> {
                if (stack.getItem() instanceof ShearsItem) {
                    if (MAP_AZALEA_SHEARING.containsKey(block)) { // azalea shearing
                        Block.dropStack(world, pos.up(), new ItemStack(AZALEA_FLOWERS, world.random.nextInt(2) + 1));
                        world.playSound(player, pos, TwigsSoundEvents.BLOCK_AZALEA_FLOWERS_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        return Optional.of(MAP_AZALEA_SHEARING.get(block).getDefaultState());
                    }
                } else if (stack.getItem() instanceof AxeItem) {
                    if (state.isOf(BAMBOO) && !world.getBlockState(pos.up()).isOf(BAMBOO)) { // bamboo stripping
                        int leaves = state.get(Properties.BAMBOO_LEAVES).ordinal();
                        if (leaves > 0) {
                            int drop = world.random.nextInt(leaves * (leaves + 1));
                            if (drop > 0) {
                                Block.dropStack(world, pos, new ItemStack(BAMBOO_LEAVES, drop));
                                world.playSound(player, pos, TwigsSoundEvents.BLOCK_BAMBOO_STRIP_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            }
                        }

                        world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        return Optional.of(STRIPPED_BAMBOO.getDefaultState().with(StrippedBambooBlock.FROM_BAMBOO, true));
                    }
                }

                return Optional.empty();
            }).map(nu -> { // set state and damage item
                if (player instanceof ServerPlayerEntity serverPlayer) Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
                world.setBlockState(pos, nu, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
                return ActionResult.success(world.isClient);
            }).orElse(ActionResult.PASS);
        });

        /* Loot Tables */

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (this.equals(id, BAMBOO)) { // bamboo leaves with bamboo
                tableBuilder.pool(
                    LootPool.builder()
                            .with(
                                ItemEntry.builder(BAMBOO_LEAVES)
                                         .conditionally(
                                             InvertedLootCondition.builder(
                                                 BlockStatePropertyLootCondition.builder(BAMBOO)
                                                                                .properties(StatePredicate.Builder.create().exactMatch(BambooBlock.LEAVES, BambooLeaves.NONE))
                                             )
                                         ).build()
                            )
                );
            } else if (this.equals(id, GRAVEL)) { // pebbles with gravel
                tableBuilder.pool(
                    LootPool.builder()
                            .with(
                                ItemEntry.builder(PEBBLE)
                                         .conditionally(InvertedLootCondition.builder(
                                             MatchToolLootCondition.builder(
                                                 ItemPredicate.Builder.create()
                                                                      .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.ANY)))
                                         ))
                                         .conditionally(RandomChanceLootCondition.builder(0.2F))
                                         .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F)))
                            ).build()
                );
            } else {
                // twigs replace sticks in leaves
                List<Identifier> leafTables = Registry.BLOCK.stream()
                                                            .filter(LeavesBlock.class::isInstance)
                                                            .map(Block::getLootTableId)
                                                            .toList();
                if (leafTables.contains(id)) {
                    tableBuilder.modifyPools(original -> {
                        LootPool pool = original.build();
                        LootPool.Builder builder = LootPool.builder();

                        for (LootCondition condition : pool.conditions) builder.conditionally(condition);
                        for (LootFunction function : pool.functions) builder.apply(function);
                        builder.rolls(pool.rolls);
                        builder.bonusRolls(pool.bonusRolls);

                        for (LootPoolEntry entry : pool.entries) {
                            if (entry instanceof ItemEntryAccessor itemEntry && itemEntry.getItem() == Items.STICK) itemEntry.setItem(TwigsItems.TWIG);
                            builder.with(entry);
                        }
                    });
                }
            }
        });

        /* Fire */

        Util.make(FlammableBlockRegistry.getDefaultInstance(), registry -> {
            registry.add(AZALEA_FLOWERS,30, 60);
            registry.add(TWIG,30, 60);
            registry.add(BAMBOO_LEAVES,30, 60);
            registry.add(BAMBOO_THATCH,30, 60);
            registry.add(BAMBOO_THATCH_SLAB, 30, 60);
            registry.add(BAMBOO_THATCH_STAIRS, 30, 60);
            registry.add(STRIPPED_BAMBOO, 5, 20);
            registry.add(STRIPPED_BAMBOO_PLANKS, 5, 20);
            registry.add(STRIPPED_BAMBOO_SLAB, 5, 20);
            registry.add(STRIPPED_BAMBOO_FENCE_GATE, 5, 20);
            registry.add(STRIPPED_BAMBOO_FENCE, 5, 20);
            registry.add(STRIPPED_BAMBOO_STAIRS, 5, 20);
        });

        Util.make(FuelRegistry.INSTANCE, registry -> {
            registry.add(STRIPPED_BAMBOO_FENCE, 300);
            registry.add(STRIPPED_BAMBOO_FENCE_GATE, 300);
        });

        /* Tilling/Stripping */

        TillableBlockRegistry.register(ROCKY_DIRT, ctx -> true, COARSE_DIRT.getDefaultState(), PEBBLE);
        StrippableBlockRegistry.register(BUNDLED_BAMBOO, STRIPPED_BUNDLED_BAMBOO);

        /* Copper */

        Util.make(new LinkedHashMap<Block, Block>(), pairs -> {
            pairs.put(COPPER_PILLAR, WAXED_COPPER_PILLAR);
            pairs.put(EXPOSED_COPPER_PILLAR, WAXED_EXPOSED_COPPER_PILLAR);
            pairs.put(WEATHERED_COPPER_PILLAR, WAXED_WEATHERED_COPPER_PILLAR);
            pairs.put(OXIDIZED_COPPER_PILLAR, WAXED_OXIDIZED_COPPER_PILLAR);

            pairs.forEach(OxidizableBlocksRegistry::registerWaxableBlockPair);

            List<Block> unwaxed = List.copyOf(pairs.keySet());
            for (int i = 0, l = unwaxed.size(); i < l - 1; i++) {
                OxidizableBlocksRegistry.registerOxidizableBlockPair(unwaxed.get(i), unwaxed.get(i + 1));
            }
        });
    }

    public boolean equals(Identifier id, Block block) {
        return id.equals(block.getLootTableId());
    }
}
