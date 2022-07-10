package net.moddingplayground.twigs.api.config;

import net.minecraft.util.Identifier;
import net.moddingplayground.frame.api.config.v0.Config;
import net.moddingplayground.frame.api.config.v0.option.BooleanOption;
import net.moddingplayground.frame.api.config.v0.option.Option;
import net.moddingplayground.twigs.api.Twigs;

import java.io.File;

public class TwigsWorldGenReplacementsConfig extends Config {
    public static final TwigsWorldGenReplacementsConfig INSTANCE = new TwigsWorldGenReplacementsConfig(createFile("%1$s/%1$s_world_gen_replacements".formatted(Twigs.MOD_ID))).load();

    public final BooleanOption petrifiedLichenAtDeepslate = add("petrified_lichen_at_deepslate", BooleanOption.of(true));

    public TwigsWorldGenReplacementsConfig(File file) {
        super(file);
    }

    private <T, O extends Option<T>> O add(String id, O option) {
        return this.add(new Identifier(Twigs.MOD_ID, id), option);
    }
}
