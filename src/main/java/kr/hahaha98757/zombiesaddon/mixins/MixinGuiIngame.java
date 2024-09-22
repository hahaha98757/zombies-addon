package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.events.TitleEvent;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public class MixinGuiIngame {

    @Inject(method = "displayTitle", at = @At("HEAD"))
    private void displayTitle(String title, String subTitle, int timeFadeIn, int displayTime, int timeFadeOut, CallbackInfo ci) {
        if (title != null) MinecraftForge.EVENT_BUS.post(new TitleEvent(title));
    }
}