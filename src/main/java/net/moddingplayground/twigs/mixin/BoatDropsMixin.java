package net.moddingplayground.twigs.mixin;

import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.moddingplayground.twigs.entity.CustomBoatItem;
import net.moddingplayground.twigs.entity.CustomBoatType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatEntity.class)
public class BoatDropsMixin {
    @Inject(method = "asItem", at = @At("HEAD"), cancellable = true)
    public void asItem(CallbackInfoReturnable<Item> cir) {
        if (((BoatEntity)(Object)this).getBoatType() == CustomBoatType.STRIPPED_BAMBOO) {
            cir.setReturnValue(CustomBoatItem.STRIPPED_BAMBOO_BOAT);
        }
    }
}
