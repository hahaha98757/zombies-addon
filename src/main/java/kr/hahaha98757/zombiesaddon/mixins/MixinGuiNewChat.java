package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiNewChat.class)
public class MixinGuiNewChat {

    @Inject(method = "printChatMessage", at = @At("HEAD"), cancellable = true)
    public void printChatMessage(IChatComponent chatComponent, CallbackInfo ci) {
        if (!Utils.isModDisable() && ZombiesAddonConfig.isSSTPatch() && Utils.getLang() == 1 && !Utils.isNotZombies()) {
            String message = EnumChatFormatting.getTextWithoutFormattingCodes(chatComponent.getUnformattedText());

            if (!message.contains("<")) {
                if (message.contains(" seconds to clean up after the last wave.")) {
                    String time = message.replaceAll("[^0-9.]", "");
                    Utils.addChat("§e마지막 웨이브 이후 넘어가는데 §c" + time.substring(0, time.length() - 1) +"§e초가 걸렸습니다.");
                    ci.cancel();
                }

                if (message.contains("You completed Round ")) {
                    Utils.addChat("                  §c라운드 " + message.split("in")[0].replaceAll("[^0-9]", "") + "§e을(를) §a" + message.split("in")[1].replaceAll("[^0-9:]", "") + "§e에 완료했습니다!");
                    ci.cancel();
                }
            }
        }
    }
}