package net.moddingplayground.twigs.data;

import net.minecraft.loot.context.LootContextTypes;
import net.moddingplayground.frame.api.toymaker.v0.ToymakerEntrypoint;
import net.moddingplayground.frame.api.toymaker.v0.registry.generator.ItemModelGeneratorStore;
import net.moddingplayground.frame.api.toymaker.v0.registry.generator.LootGeneratorStore;
import net.moddingplayground.frame.api.toymaker.v0.registry.generator.RecipeGeneratorStore;
import net.moddingplayground.frame.api.toymaker.v0.registry.generator.StateModelGeneratorStore;
import net.moddingplayground.frame.api.toymaker.v0.registry.generator.TagGeneratorStore;

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
