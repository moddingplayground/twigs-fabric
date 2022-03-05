package net.moddingplayground.twigs.api.tag;

import net.minecraft.entity.EntityType;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.twigs.api.Twigs;

public interface TwigsEntityTypeTags {
    TagKey<EntityType<?>> BAMBOO_LEAVES_SLOW_IMMUNE = register("bamboo_leaves_slow_immune");

    private static TagKey<EntityType<?>> register(String id) {
        return TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(Twigs.MOD_ID, id));
    }
}
