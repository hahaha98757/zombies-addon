package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ZombiesOverlayPatchListener {

    @SubscribeEvent
    public void overlayKorean(ClientChatReceivedEvent event) {
        if (!ZombiesAddonConfig.enableMod) return;

        if (!ZombiesAddonConfig.zombiesOverlayPatch) return;

        String message = event.message.getUnformattedText();

        if (message.contains(">")) return;

        if (message.startsWith("온라인: ")) {// who
            String playerList = message.split(":")[1].trim();

            Utils.addChat("ONLINE: " + playerList);

        }

        if (message.contains("님이 참여했습니다!")) {// join
            String playerName = message.split(" ")[0];
            String number = message.split(" ")[3].split("/")[0].replaceAll("[^0-9]", "");

            Utils.addChat(playerName + " has joined (" + number + "/4)!");
        }

        if (message.contains("님이 나갔습니다!")) {// quit
            String playerName = message.split(" ")[0];

            Utils.addChat(playerName + " has quit!");
        }

        if (message.contains("서버로 이동합니다!")) {// join the zombies
            String text = message.split(" ")[0];

            Utils.addChat("Sending you to " + text + "!");
        }
    }
}
