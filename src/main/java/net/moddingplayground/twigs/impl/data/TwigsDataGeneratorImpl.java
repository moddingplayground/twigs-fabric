package net.moddingplayground.twigs.impl.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.moddingplayground.twigs.api.Twigs;

public final class TwigsDataGeneratorImpl implements Twigs, DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator gen) {
        gen.addProvider(ModelProvider::new);
        gen.addProvider(RecipeProvider::new);

        BlockTagProvider blockTagProvider = gen.addProvider(BlockTagProvider::new);
        gen.addProvider(g -> new ItemTagProvider(g, blockTagProvider));
        gen.addProvider(EntityTypeTagProvider::new);
        gen.addProvider(BiomeTagProvider::new);
    }
}
