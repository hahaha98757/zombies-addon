package kr.hahaha98757.zombiesaddon.mixins;

import com.seosean.showspawntime.features.spawntimes.SpawnTimeRenderer;
import kr.hahaha98757.zombiesaddon.config.ZAConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SpawnTimeRenderer.class, remap = false)
public class MixinSpawnTimeRenderer {
    @Inject(method = "onRender", at = @At("HEAD"), cancellable = true)
    private void onRender(CallbackInfo ci) {
        if (ZAConfig.INSTANCE.getEnableMod() && ZAConfig.INSTANCE.getDisableSpawnTimeOfSst()) ci.cancel();
    }
}
