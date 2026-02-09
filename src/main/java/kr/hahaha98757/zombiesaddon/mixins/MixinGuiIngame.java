package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.modules.BetterZombiesLeft;
import kr.hahaha98757.zombiesaddon.modules.LastWeapons;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public abstract class MixinGuiIngame {

    @Inject(method = "renderHotbarItem", at = @At("HEAD"), cancellable = true)
    private void renderHotbarItem(int index, int xPos, int yPos, float partialTicks, EntityPlayer player, CallbackInfo ci) {
        if (!LastWeapons.INSTANCE.isWork()) return;
        ci.cancel();
    }

    // 원래라면 이게 맞지만 이걸 쓰면 SST가 예외를 던짐.
    // Better Zombies Left가 아직 모든 데이터를 가지고 있지 않아서 SST를 사용할 수 밖에 없으므로 아래 코드를 사용함.
//    @Unique
//    private Score zombiesaddon$score;
//
//    @Redirect(method = "renderScoreboard", at = @At(value = "INVOKE", target = "Ljava/util/Iterator;next()Ljava/lang/Object;"))
//    private <E> E redirectFormatPlayerName(Iterator<E> iterator) {
//        E next = iterator.next();
//        zombiesaddon$score = (Score) next;
//        return next;
//    }
//
//    @Redirect(method = "renderScoreboard", at = @At(value = "INVOKE", target = "Lnet/minecraft/scoreboard/ScorePlayerTeam;formatPlayerName(Lnet/minecraft/scoreboard/Team;Ljava/lang/String;)Ljava/lang/String;"))
//    private String redirectFormatPlayerName(Team team, String playerName) {
//        return BetterZombiesLeft.INSTANCE.getName((ScorePlayerTeam) team, zombiesaddon$score);
//    }

    @Inject(method = "renderScoreboard", at = @At("HEAD"), cancellable = true)
    private void renderScoreboard(ScoreObjective objective, ScaledResolution scaledRes, CallbackInfo ci) {
        if (!BetterZombiesLeft.INSTANCE.isEnable()) return;
        if (!BetterZombiesLeft.INSTANCE.isWork()) return;
        BetterZombiesLeft.INSTANCE.customRenderScoreboard(objective, scaledRes);
        ci.cancel();
    }
}