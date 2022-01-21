package net.moddingplayground.twigs.datagen;

import net.minecraft.loot.context.LootContextTypes;
import net.moddingplayground.toymaker.api.ToymakerEntrypoint;
import net.moddingplayground.toymaker.api.registry.generator.ItemModelGeneratorStore;
import net.moddingplayground.toymaker.api.registry.generator.LootGeneratorStore;
import net.moddingplayground.toymaker.api.registry.generator.RecipeGeneratorStore;
import net.moddingplayground.toymaker.api.registry.generator.StateModelGeneratorStore;
import net.moddingplayground.toymaker.api.registry.generator.TagGeneratorStore;

public class TwigsToymaker implements ToymakerEntrypoint {
    @Override
    public void onInitializeToymaker() {
        TagGeneratorStore.register(BiomeTagGenerator::new);
        TagGeneratorStore.register(BlockTagGenerator::new);
        TagGeneratorStore.register(ItemTagGenerator::new);
        TagGeneratorStore.register(EntityTypeTagGenerator::new);
        ItemModelGeneratorStore.register(ItemModelGenerator::new);
        StateModelGeneratorStore.register(StateModelGenerator::new);
        RecipeGeneratorStore.register(RecipeGenerator::new);
        LootGeneratorStore.register(BlockLootTableGenerator::new, LootContextTypes.BLOCK);
    }
}
