package kr.hahaha98757.zombiesaddon.mixins;

import com.github.stachelbeere1248.zombiesutils.timer.Game;
import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Game.class)
public class MixinGame {
    @Inject(method = "record", at = @At("HEAD"), cancellable = true)
    private void record(CallbackInfo ci) {
        if (ZombiesAddon.getInstance().getConfig().getRecorderToggle()) ci.cancel();
    }

}