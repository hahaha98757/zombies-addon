package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.utils.MixinToolsKt;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S45PacketTitle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = NetHandlerPlayClient.class, priority = 900)
public class MixinNetHandlerPlayClient {
    @Inject(method = "handleSoundEffect", at = @At("HEAD"))
    private void handleSoundEffect(S29PacketSoundEffect packetIn, CallbackInfo ci) {
        MixinToolsKt.onSound(packetIn);
    }

    @Inject(method = "handleTitle" , at = @At("HEAD"))
    private void handleTitle(S45PacketTitle packetIn, CallbackInfo ci) {
        MixinToolsKt.onTitle(packetIn);
    }

    @Inject(method = "handleSetExperience", at = @At("HEAD"))
    private void handleSetExperience(S1FPacketSetExperience packetIn, CallbackInfo ci) {
        MixinToolsKt.onXpPacket();
    }

    @Inject(method = "handleTimeUpdate", at = @At("HEAD"))
    private void handleTimeUpdate(S03PacketTimeUpdate packetIn, CallbackInfo ci) {
        MixinToolsKt.onTimeUpdate(packetIn);
    }
}