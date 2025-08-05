package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.utils.MixinToolsKt;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S45PacketTitle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {
    @Inject(method = "handleSoundEffect", at = @At("HEAD"))
    private void handleSoundEffect(S29PacketSoundEffect packetIn, CallbackInfo ci) {
        MixinToolsKt.onSound(packetIn);
    }

    @Inject(method = "handleTitle" , at = @At("HEAD"))
    private void handleTitle(S45PacketTitle packetIn, CallbackInfo ci) {
        MixinToolsKt.onTitle(packetIn);
    }
}