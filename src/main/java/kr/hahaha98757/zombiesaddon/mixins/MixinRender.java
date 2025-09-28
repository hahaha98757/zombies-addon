package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.modules.PVUtils;
import kr.hahaha98757.zombiesaddon.modules.PlayerVisibility;
import kr.hahaha98757.zombiesaddon.utils.Tools;
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
    @Inject(method = "renderEntityOnFire", at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;color(FFFF)V", shift = At.Shift.AFTER)})
    private void renderEntityOnFire(Entity entity, double x, double y, double z, float partialTicks, CallbackInfo ci) {
        if (!Tools.isDisable() && !Tools.isNotPlayZombies() && PlayerVisibility.INSTANCE.isEnable()
                && entity instanceof EntityPlayer && entity != Tools.getMc().thePlayer) {
            final boolean b = !PVUtils.INSTANCE.withoutRange((EntityPlayer) entity);
            GlStateManager.color(1.0F, 1.0F, 1.0F, b ? 0 : 1.0F);
        }
    }
}