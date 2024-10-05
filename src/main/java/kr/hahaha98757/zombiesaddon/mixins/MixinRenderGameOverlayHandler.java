package kr.hahaha98757.zombiesaddon.mixins;

import com.github.stachelbeere1248.zombiesutils.handlers.RenderGameOverlayHandler;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderGameOverlayHandler.class)
public class MixinRenderGameOverlayHandler {

    @Inject(method = "renderTime", at = @At("HEAD"), cancellable = true, remap = false)
    private void renderTime(short timerTicks, CallbackInfo ci) {
        if (ZombiesAddonConfig.isEnableMod() && ZombiesAddonConfig.isZombiesUtilsSetting()) ci.cancel();
    }
}