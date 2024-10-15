package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class KoreanPatcher {

    @SuppressWarnings("SpellCheckingInspection")
    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        if (Utils.isModDisable()) return;

        if (!ZombiesAddonConfig.isKoreanPatcher() || Utils.getLang() != 1) return;

        if (Utils.isNotZombies()) return;

        String message = event.message.getUnformattedText();

        if (message.contains("<")) return;

        if (message.equals("This weapon is out of ammo! You can refill ammo at the place that you purchased the weapon or through collecting the Max Ammo Power Up.")) {
            event.setCanceled(true);
            Utils.addChat("§c§l무기는 탄약이 없습니다! 무기를 구입한 곳에서 탄약을 채우거나 §r§9§l탄약 충전 §c§l파워업을 획득하세요.");
        }

        if (message.equals("Clarence의 벼락에 맞았습니다!")) {
            event.setCanceled(true);
            Utils.addChat("§6클라렌스의 벼락에 맞았습니다!");
        }

        if (message.equals("[언데드] Clarence: 거투루드는 이 집을 사지 말아야 했어.")) {
            event.setCanceled(true);
            Utils.addChat("§c[언데드] §6클라렌스§f: 거트루드는 이 집을 사지 말아야 했어.");
        }

        if (message.equals("이 행운의 상자는 지금 사용할 수 없습니다! 묘지에서 다른 활성화된 행운의 상자를 찾으세요!")) {
            event.setCanceled(true);
            Utils.addChat("§c이 행운의 상자는 지금 사용할 수 없습니다! 지하실에서 다른 활성화된 행운의 상자를 찾으세요!");
        }

        if (message.equals("I'm telling you man, the Earth is flat.")) {
            event.setCanceled(true);
            Utils.addChat("§c내가 말하는데, 지구는 평평해.");
        }

        if (message.equals("Please get me away from this eternal misery.")) {
            event.setCanceled(true);
            Utils.addChat("§c이 영원한 비참함에서 나를 구해 줘.");
        }

        if (message.equals("Dude, it's a cube.")) {
            event.setCanceled(true);
            Utils.addChat("§c이봐, 이건 큐브야.");
        }

        if (message.equals("[언데드] 거트루드: I told them 10 times!! Don't go near the dungeon! They never listen!")) {
            event.setCanceled(true);
            Utils.addChat("§c[언데드] §a거트루드§f: 던전에 가지 말라고 10번이나 말했어! 그 놈들은 끝까지 듣지 않았고!");
        }

        if (message.equals("[언데드] Clarence: Alright I'll admit buying this house was a mistake.")) {
            event.setCanceled(true);
            Utils.addChat("§c[언데드] §6클라렌스§f: 그래 그래, 내가 잘못했어. 이 저택을 산 거말야.");
        }

        if (message.equals("[언데드] 아담: I just wanted to play video game but Steve was hogging the OblongGameShape.")) {
            event.setCanceled(true);
            Utils.addChat("§c[언데드] §9아담§f: 난 그냥 비디오 게임이나 하고 싶었을 뿐인데 스티브가 자기 혼자 OblongGameShape를 쓰고 있었어.");
        }

        if (message.equals("[언데드] 스티브: Grumble grumble grumble")) {
            event.setCanceled(true);
            Utils.addChat("§c[언데드] 스티브§f: 궁시렁 궁시렁 궁시렁");
        }

        if (message.equals("[언데드] 릴리: Why'd they have to bury me next to these two losers?")) {
            event.setCanceled(true);
            Utils.addChat("§c[언데드] 릴리§f: 왜 나를 이 찌질이 둘 옆에 묻은 거지?");
        }

        if (message.equals("[언데드] 엘리: Woof.")) {
            event.setCanceled(true);
            Utils.addChat("§c[언데드] §b엘리§f: 멍.");
        }

        if (message.equals("[언데드] 엘리: Is nobody finding it weird that the undead family that that one lived here is able to type in chat?")) {
            event.setCanceled(true);
            Utils.addChat("§c[언데드] §b엘리§f: 여기 살던 언데드 가족이 채팅창에 채팅하는 거에 이상하다고 생각하는 사람 없나?");
        }

        if (message.equals("[언데드] Lily: 됐어...")) {
            event.setCanceled(true);
            Utils.addChat("§c[언데드] 릴리§f: 됐어...");
        }
    }
}