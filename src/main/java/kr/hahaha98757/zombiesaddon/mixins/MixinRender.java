//Code in Show Spawn Time v1.15.0 by Seosean

package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.features.PlayerVisibility;
import net.minecraft.client.Minecraft;
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

    @SuppressWarnings("SpellCheckingInspection")
    @Inject(method = "renderEntityOnFire", at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;color(FFFF)V", shift = At.Shift.AFTER)})
    private void renderEntityOnFire(Entity entity, double x, double y, double z, float partialTicks, CallbackInfo ci) {
        if(ZombiesAddonConfig.isEnableMod() && PlayerVisibility.playerVisibility && entity instanceof EntityPlayer && entity != Minecraft.getMinecraft().thePlayer) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) < ZombiesAddonConfig.getPVRange() ? 0 : 1.0F);
        }
    }
}