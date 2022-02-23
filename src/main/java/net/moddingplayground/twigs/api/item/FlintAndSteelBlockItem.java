package net.moddingplayground.twigs.api.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;

public class FlintAndSteelBlockItem extends BlockItem {
    public FlintAndSteelBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext ctx) {
        PlayerEntity player = ctx.getPlayer();
        if (player != null && ctx.shouldCancelInteraction()) {
            ItemStack sm = player.getMainHandStack();
            ItemStack so = player.getOffHandStack();
            if (so.isItemEqual(sm)) {
                ActionResult action = Items.FLINT_AND_STEEL.useOnBlock(ctx);
                if (action.isAccepted()) {
                    if (!player.getAbilities().creativeMode) {
                        sm.decrement(1);
                        so.decrement(1);
                    }
                    return action;
                }
            }
        }

        return super.useOnBlock(ctx);
    }
}
