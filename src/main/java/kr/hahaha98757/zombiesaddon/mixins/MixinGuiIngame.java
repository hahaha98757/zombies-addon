package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.modules.BetterZombiesLeft;
import kr.hahaha98757.zombiesaddon.modules.LastWeapons;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

@Mixin(GuiIngame.class)
public abstract class MixinGuiIngame {

    @Inject(method = "renderHotbarItem", at = @At("HEAD"), cancellable = true)
    private void renderHotbarItem(int index, int xPos, int yPos, float partialTicks, EntityPlayer player, CallbackInfo ci) {
        if (!LastWeapons.INSTANCE.isWork()) return;
        ci.cancel();
    }

    @Unique
    private Score zombiesaddon$score;

    @Redirect(method = "renderScoreboard", at = @At(value = "INVOKE", target = "Ljava/util/Iterator;next()Ljava/lang/Object;"))
    private <E> E redirectFormatPlayerName(Iterator<E> iterator) {
        E next = iterator.next();
        zombiesaddon$score = (Score) next;
        return next;
    }

    @Redirect(method = "renderScoreboard", at = @At(value = "INVOKE", target = "Lnet/minecraft/scoreboard/ScorePlayerTeam;formatPlayerName(Lnet/minecraft/scoreboard/Team;Ljava/lang/String;)Ljava/lang/String;"))
    private String redirectFormatPlayerName(Team team, String playerName) {
        return BetterZombiesLeft.INSTANCE.getName((ScorePlayerTeam) team, zombiesaddon$score);
    }
}