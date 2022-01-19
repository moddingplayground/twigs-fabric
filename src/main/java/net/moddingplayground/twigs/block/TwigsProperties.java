package net.moddingplayground.twigs.block;

import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;

public class TwigsProperties {
    public static final IntProperty LAYERS_1_4 = IntProperty.of("layers", 1, 4);
    public static final BooleanProperty FROM_BAMBOO = BooleanProperty.of("from_bamboo");
}
