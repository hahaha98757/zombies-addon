package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.modules.PvUtils;
import kr.hahaha98757.zombiesaddon.modules.PlayerVisibility;
import kr.hahaha98757.zombiesaddon.utils.ToolsKt;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Render.class)
public class MixinRender {
    @Inject(method = "renderEntityOnFire", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;color(FFFF)V", shift = At.Shift.AFTER))
    private void renderEntityOnFire(Entity entity, double x, double y, double z, float partialTicks, CallbackInfo ci) {
        if (!ToolsKt.isDisable() && !ToolsKt.isNotPlayZombies() && PlayerVisibility.INSTANCE.isEnable()
                && entity instanceof EntityPlayer && entity != ToolsKt.getMc().thePlayer) {
            final boolean b = !PvUtils.INSTANCE.withoutRange((EntityPlayer) entity);
            GlStateManager.color(1.0F, 1.0F, 1.0F, b ? 0 : 1.0F);
        }
    }
}