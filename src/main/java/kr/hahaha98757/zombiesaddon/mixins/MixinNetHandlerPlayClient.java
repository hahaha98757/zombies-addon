// Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.events.SoundEvent;
import kr.hahaha98757.zombiesaddon.events.TitleEvent;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {

    @Inject(method = "handleSoundEffect", at = @At(value = "HEAD"))
    private void handleSoundEffect(S29PacketSoundEffect packetIn, CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new SoundEvent(packetIn));
    }

    @Inject(method = "handleTitle", at = @At(value = "HEAD"))
    private void handleTitle(S45PacketTitle packetIn, CallbackInfo ci) {
        if (packetIn.getType() == S45PacketTitle.Type.TITLE) {
            MinecraftForge.EVENT_BUS.post(new TitleEvent(packetIn.getMessage().getUnformattedText()));
        }
    }
}