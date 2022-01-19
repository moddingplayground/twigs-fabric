package net.moddingplayground.twigs.datagen.impl.provider;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.datagen.impl.DataType;
import net.moddingplayground.twigs.datagen.impl.generator.advancement.AbstractAdvancementGenerator;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class AdvancementProvider extends AbstractDataProvider<Supplier<AbstractAdvancementGenerator>> {
    public AdvancementProvider(DataGenerator root) {
        super(root);
    }

    @Override
    public String getName() {
        return "Advancement";
    }

    @Override
    public String getFolder() {
        return "advancements";
    }

    @Override
    public DataType getDataType() {
        return DataType.DATA;
    }

    @Override
    public List<Supplier<AbstractAdvancementGenerator>> getGenerators() {
        return List.of();
    }

    @Override
    public Map<Identifier, JsonElement> createFileMap() {
        Map<Identifier, JsonElement> map = Maps.newHashMap();
        for (Supplier<AbstractAdvancementGenerator> generator : this.getGenerators()) {
            generator.get().accept((id, task) -> {
                if (map.put(id, task.toJson()) != null) {
                    throw new IllegalStateException("Duplicate advancement " + id);
                }
            });
        }
        return map;
    }
}
