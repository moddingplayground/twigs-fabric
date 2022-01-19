package net.moddingplayground.twigs.datagen.impl.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.util.Identifier;
import net.moddingplayground.twigs.datagen.impl.ObjectLootTableAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin implements ObjectLootTableAccess {
    @Shadow public abstract Identifier getLootTableId();

    @Override
    public Identifier access_getLootTableId() {
        return this.getLootTableId();
    }
}
