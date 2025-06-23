//Code in Show Spawn Time by Seosean

package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.modules.PlayerVisibility;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("SpellCheckingInspection")
@Mixin(LayerCape.class)
public class MixinLayerCape {

    @Inject(method = "doRenderLayer*", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;pushMatrix()V", shift = At.Shift.AFTER))
    private void doRenderLayerPre(AbstractClientPlayer entitylivingbaseIn, float f, float g, float partialTicks, float h, float i, float j, float scale, CallbackInfo ci) {
        if (PlayerVisibility.Companion.isSemiPV(entitylivingbaseIn)) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, PlayerVisibility.Companion.getAlpha(entitylivingbaseIn));
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            GlStateManager.alphaFunc(516, 0.003921569F);
        }

    }

    @SuppressWarnings("DiscouragedShift")
    @Inject(method = "doRenderLayer*", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;popMatrix()V", shift = At.Shift.BEFORE))
    private void doRenderLayerPost (AbstractClientPlayer entitylivingbaseIn, float f, float g, float partialTicks, float h, float i, float j, float scale, CallbackInfo ci) {
        if(PlayerVisibility.Companion.isSemiPV(entitylivingbaseIn)) {
            GlStateManager.disableBlend();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.depthMask(true);
        }
    }
}