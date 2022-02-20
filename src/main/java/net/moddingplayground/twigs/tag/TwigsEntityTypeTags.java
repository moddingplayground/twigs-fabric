package net.moddingplayground.twigs.tag;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.Twigs;

public interface TwigsEntityTypeTags {
    Tag.Identified<EntityType<?>> BAMBOO_LEAVES_SLOW_IMMUNE = register("bamboo_leaves_slow_immune");

    private static Tag.Identified<EntityType<?>> register(String id) {
        return TagFactory.ENTITY_TYPE.create(new Identifier(Twigs.MOD_ID, id));
    }
}
