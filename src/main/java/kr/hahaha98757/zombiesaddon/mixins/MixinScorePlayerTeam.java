package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.modules.BetterZombiesLeft;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ScorePlayerTeam.class)
public class MixinScorePlayerTeam {
    @Inject(method = "formatPlayerName", at = @At("RETURN"), cancellable = true)
    private static void formatPlayerName(Team team, String string, CallbackInfoReturnable<String> cir) {
        String original = cir.getReturnValue();
        String modified = BetterZombiesLeft.INSTANCE.modifyFormattedName(cir.getReturnValue());

        if (!original.equals(modified)) cir.setReturnValue(modified);
    }
}