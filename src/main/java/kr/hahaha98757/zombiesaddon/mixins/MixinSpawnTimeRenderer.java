package kr.hahaha98757.zombiesaddon.mixins;

import com.seosean.showspawntime.features.spawntimes.SpawnTimeRenderer;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpawnTimeRenderer.class)
public class MixinSpawnTimeRenderer {

    @Inject(method = "onRender", at = @At("HEAD"), cancellable = true, remap = false)
    private void onRender(RenderGameOverlayEvent.Post event, CallbackInfo ci) {
        if (ZombiesAddonConfig.isEnableMod() && ZombiesAddonConfig.isSSTSetting()) ci.cancel();
    }
}
