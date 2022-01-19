package net.moddingplayground.twigs.datagen.impl.provider;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.datagen.BlockLootTableGenerator;
import net.moddingplayground.twigs.datagen.impl.DataType;
import net.moddingplayground.twigs.datagen.impl.generator.loot.AbstractLootTableGenerator;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class LootTableProvider extends AbstractDataProvider<Pair<Supplier<AbstractLootTableGenerator<?>>, LootContextType>> {
    public LootTableProvider(DataGenerator root) {
        super(root);
    }

    @Override
    public String getName() {
        return "Loot Table";
    }

    @Override
    public String getFolder() {
        return "loot_tables";
    }

    @Override
    public DataType getDataType() {
        return DataType.DATA;
    }

    @Override
    public List<Pair<Supplier<AbstractLootTableGenerator<?>>, LootContextType>> getGenerators() {
        return List.of(Pair.of(BlockLootTableGenerator::new, LootContextTypes.BLOCK));
    }

    @Override
    public Map<Identifier, JsonElement> createFileMap() {
        Map<Identifier, JsonElement> map = Maps.newHashMap();
        for (Pair<Supplier<AbstractLootTableGenerator<?>>, LootContextType> pair : this.getGenerators()) {
            pair.getFirst().get().accept((id, builder) -> {
                LootTable lootTable = builder.type(pair.getSecond()).build();
                if (map.put(id, LootManager.toJson(lootTable)) != null) {
                    throw new IllegalStateException("Duplicate loot table " + id);
                }
            });
        }
        return map;
    }
}
