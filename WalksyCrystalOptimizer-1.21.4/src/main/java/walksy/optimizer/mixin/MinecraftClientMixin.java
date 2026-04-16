package walksy.optimizer.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import walksy.optimizer.WalksyCrystalOptimizerMod;
import walksy.optimizer.command.EnableOptimizerCommand;

import static walksy.optimizer.WalksyCrystalOptimizerMod.limitPackets;
import static walksy.optimizer.WalksyCrystalOptimizerMod.mc;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Inject(at = @At("HEAD"), method = "doItemUse", cancellable = true)
    private void onDoItemUse(CallbackInfo ci) {
        if (EnableOptimizerCommand.fastCrystal) {
            if (mc.player == null) return;
            ItemStack mainHand = mc.player.getMainHandStack();
            if (mainHand.isOf(Items.END_CRYSTAL)) {
                // Only let every Nth packet through for speed optimization
                if (WalksyCrystalOptimizerMod.hitCount != limitPackets()) {
                    ci.cancel();
                }
            }
        }
    }
}
