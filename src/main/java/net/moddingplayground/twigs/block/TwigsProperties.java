package net.moddingplayground.twigs.block;

import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;

public interface TwigsProperties {
    IntProperty LAYERS_1_4 = IntProperty.of("layers", 1, 4);
    BooleanProperty FROM_BAMBOO = BooleanProperty.of("from_bamboo");
}
