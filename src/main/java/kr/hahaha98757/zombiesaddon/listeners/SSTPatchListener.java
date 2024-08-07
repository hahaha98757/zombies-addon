package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SSTPatchListener {

    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        if (!ZombiesAddonConfig.enableMod) return;

        if (!ZombiesAddonConfig.sstPatch || Utils.getLang() != 1) return;

        if (!Utils.isZombies()) return;

        String message = event.message.getUnformattedText();

        if (message.contains("<")) return;

        if (message.contains(" seconds to clean up after the last wave.")) {
            event.setCanceled(true);
            String time = message.replaceAll("[^0-9.]", "");
            Utils.addChat("\u00A7e마지막 웨이브 이후 넘어가는데 \u00A7c" + time.substring(0, time.length() - 1) +"\u00A7e초가 걸렸습니다.");
        }

        if (message.contains("You completed Round ")) {
            event.setCanceled(true);
            Utils.addChat("                  \u00A7c라운드 " + message.split("in")[0].replaceAll("[^0-9]", "") + "\u00A7e을(를) \u00A7a" + message.split("in")[1].replaceAll("[^0-9:]", "") +"\u00A7e에 완료했습니다!");
        }
    }
}