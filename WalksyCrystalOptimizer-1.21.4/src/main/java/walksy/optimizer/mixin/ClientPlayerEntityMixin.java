package walksy.optimizer.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import walksy.optimizer.command.EnableOptimizerCommand;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {

    /**
     * When the optimizer is active and the player is holding a crystal,
     * skip the item use tick so placement isn't gated by the swing timer.
     */
    @Inject(method = "tickItemStackUsage", at = @At("HEAD"), cancellable = true)
    private void onTickItemStackUsage(CallbackInfo ci) {
        ClientPlayerEntity self = (ClientPlayerEntity) (Object) this;
        if (EnableOptimizerCommand.fastCrystal
                && self.getMainHandStack().isOf(Items.END_CRYSTAL)) {
            ci.cancel();
        }
    }
}
