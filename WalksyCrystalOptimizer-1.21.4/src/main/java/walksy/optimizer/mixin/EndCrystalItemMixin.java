package walksy.optimizer.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EndCrystalItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import walksy.optimizer.WalksyCrystalOptimizerMod;
import walksy.optimizer.command.EnableOptimizerCommand;

@Mixin(EndCrystalItem.class)
public abstract class EndCrystalItemMixin {

    @Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
    private void onUseOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        if (!EnableOptimizerCommand.fastCrystal) return;

        PlayerEntity player = context.getPlayer();
        if (player == null) return;

        // Increment hit count each use attempt
        WalksyCrystalOptimizerMod.hitCount++;
        if (WalksyCrystalOptimizerMod.hitCount > WalksyCrystalOptimizerMod.limitPackets()) {
            WalksyCrystalOptimizerMod.hitCount = 0;
        }
    }
}
