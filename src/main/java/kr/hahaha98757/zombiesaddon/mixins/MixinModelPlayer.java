package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.utils.MixinToolsKt;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("DiscouragedShift")
@Mixin(value = ModelPlayer.class, priority = 1100)
public class MixinModelPlayer {
    @Inject(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/model/ModelPlayer;isChild:Z", shift = At.Shift.BEFORE))
    private void renderPre(Entity entityIn, float f, float g, float h, float i, float j, float scale, CallbackInfo ci) {
        MixinToolsKt.renderPre(entityIn);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;popMatrix()V", shift = At.Shift.BEFORE))
    private void renderPost(Entity entityIn, float f, float g, float h, float i, float j, float scale, CallbackInfo ci) {
        MixinToolsKt.renderPost(entityIn);
    }
}