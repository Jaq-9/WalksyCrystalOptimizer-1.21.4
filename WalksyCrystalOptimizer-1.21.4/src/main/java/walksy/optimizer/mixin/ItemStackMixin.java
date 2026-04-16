package walksy.optimizer.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import walksy.optimizer.command.EnableOptimizerCommand;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    /**
     * When the optimizer is active and holding a crystal,
     * report max use time of 0 so there is no use-tick delay.
     */
    @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
    private void onGetMaxUseTime(CallbackInfoReturnable<Integer> cir) {
        ItemStack self = (ItemStack) (Object) this;
        if (EnableOptimizerCommand.fastCrystal && self.isOf(Items.END_CRYSTAL)) {
            cir.setReturnValue(0);
        }
    }
}
