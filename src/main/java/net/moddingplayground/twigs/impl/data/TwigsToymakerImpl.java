package net.moddingplayground.twigs.impl.data;

import net.minecraft.loot.context.LootContextTypes;
import net.moddingplayground.frame.api.toymaker.v0.ToymakerEntrypoint;
import net.moddingplayground.frame.api.toymaker.v0.registry.generator.ItemModelGeneratorStore;
import net.moddingplayground.frame.api.toymaker.v0.registry.generator.LootGeneratorStore;
import net.moddingplayground.frame.api.toymaker.v0.registry.generator.RecipeGeneratorStore;
import net.moddingplayground.frame.api.toymaker.v0.registry.generator.StateModelGeneratorStore;
import net.moddingplayground.frame.api.toymaker.v0.registry.generator.TagGeneratorStore;
import net.moddingplayground.twigs.api.Twigs;

public class TwigsToymakerImpl implements Twigs, ToymakerEntrypoint {
    @Override
    public void onInitializeToymaker() {
        ItemModelGeneratorStore.register(() -> new ItemModelGenerator(MOD_ID));
        RecipeGeneratorStore.register(() -> new RecipeGenerator(MOD_ID));
        StateModelGeneratorStore.register(() -> new StateModelGenerator(MOD_ID));
        LootGeneratorStore.register(() -> new BlockLootTableGenerator(MOD_ID), LootContextTypes.BLOCK);
        TagGeneratorStore.register(() -> new BlockTagGenerator(MOD_ID));
        TagGeneratorStore.register(() -> new ItemTagGenerator(MOD_ID));
        TagGeneratorStore.register(() -> new EntityTypeTagGenerator(MOD_ID));
        TagGeneratorStore.register(() -> new BiomeTagGenerator(MOD_ID));
    }
}
