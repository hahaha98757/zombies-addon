package kr.hahaha98757.zombiesaddon.mixins;

import com.github.stachelbeere1248.zombiesutils.ZombiesUtils;
import com.github.stachelbeere1248.zombiesutils.timer.RecordMessageSender;
import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RecordMessageSender.class, remap = false)
public abstract class MixinRecordMessageSender {
    @Shadow @Final @Mutable
    private StringBuilder recordMessage;
    @Shadow @Final
    private int newTime;
    @Shadow @Final
    private int oldTime;
    @Shadow @Final
    private int round;
    @Shadow @Final
    private String deltaString;
    @Shadow @Final
    private String timeString;
    @Shadow
    private String copyString;

    @Shadow
    protected abstract String formattedTime(int time);
    @Shadow
    protected abstract String formattedDelta(int newTime, int oldTime);


    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(String categoryName, int round, int newTime, int oldTime, CallbackInfo ci) {
        if (ZombiesAddon.getInstance().getConfig().getKoreanPatchersZombiesUtils())
            this.recordMessage = new StringBuilder(
                    "§l§a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n§e 카테고리: §d" + categoryName
            );
    }

    @Inject(method = "gameSplit", at = @At("HEAD"), cancellable = true)
    private void gameSplit(CallbackInfo ci) {
        if (!ZombiesAddon.getInstance().getConfig().getKoreanPatchersZombiesUtils()) return;

        final String announcement = newTime < oldTime && ZombiesUtils.getInstance().getConfig().getAnnouncePB() ?
                "\n§e§l***§6§l 새로운 개인 최고 기록! §e§l***" : "";
        this.recordMessage.append(announcement)
                .append("\n§c라운드 ")
                .append(round)
                .append("§e을(를)§a ")
                .append(timeString)
                .append("§9")
                .append(deltaString)
                .append("§e에 완료했습니다!");
        this.copyString = deltaString.isEmpty() ?
                String.format("라운드 %d을(를) %s에 완료했습니다!", round, timeString) :
                String.format("라운드 %d을(를) %s (%s)에 완료했습니다!", round, timeString, deltaString);

        ci.cancel();
    }

    @Inject(method = "roundSplit", at = @At("HEAD"), cancellable = true)
    private void roundSplit(CallbackInfo ci) {
        if (!ZombiesAddon.getInstance().getConfig().getKoreanPatchersZombiesUtils()) return;

        final String announcement = newTime < oldTime && ZombiesUtils.getInstance().getConfig().getAnnouncePB() ?
                "\n§e§l***§6§l 새로운 구간 최고 기록! §e§l***" : "";
        this.recordMessage.append(announcement)
                .append("\n§c라운드 ")
                .append(round)
                .append("§e을(를) 넘기는 데 §a")
                .append(timeString)
                .append("§9")
                .append(deltaString)
                .append("§e가 걸렸습니다!");
        this.copyString = deltaString.isEmpty() ?
                String.format("라운드 %d을(를) 넘기는 데 %s가 걸렸습니다!", round, timeString) :
                String.format("라운드 %d을(를) 넘기는 데 %s (%s)가 걸렸습니다!", round, timeString, deltaString);

        ci.cancel();
    }

    @Inject(method = "helicopterSplit", at = @At("HEAD"), cancellable = true)
    private void helicopterSplit(CallbackInfo ci) {
        if (!ZombiesAddon.getInstance().getConfig().getKoreanPatchersZombiesUtils()) return;

        final String announcement = newTime < oldTime && ZombiesUtils.getInstance().getConfig().getAnnouncePB() ?
                "\n§e§l***§6§l 새로운 개인 최고 기록! §e§l***" : "";
        this.recordMessage.append(announcement)
                .append("\n§8§l헬리콥터§r§e가 §a")
                .append(timeString)
                .append("§9")
                .append(deltaString)
                .append("§e에 호출되었습니다!");
        this.copyString = deltaString.isEmpty() ?
                String.format("헬리콥터가 %s에 호출되었습니다!", timeString) :
                String.format("헬리콥터가 %s (%s)에 호출되었습니다!", timeString, deltaString);

        ci.cancel();
    }
}
