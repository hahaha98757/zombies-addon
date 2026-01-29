package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.modules.LastWeapons;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public class MixinGuiIngame {

    @Inject(method = "renderHotbarItem", at = @At("HEAD"), cancellable = true)
    private void renderHotbarItem(int index, int xPos, int yPos, float partialTicks, EntityPlayer player, CallbackInfo ci) {
        if (!LastWeapons.INSTANCE.isWork()) return;

        ci.cancel();
    }
}