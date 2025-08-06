package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.events.ClientChatPrintedEvent
import kr.hahaha98757.zombiesaddon.utils.addChat
import kr.hahaha98757.zombiesaddon.utils.getText
import kr.hahaha98757.zombiesaddon.utils.isNotPlayZombies
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.Event

class KoreanPatchers: AlwaysEnableModule("Korean Patchers") {
    override fun onChat(event: ClientChatReceivedEvent) {
        val message = event.message.unformattedText
        if ("<" in message) return
        ingame(message, event)
        zombiesOverlay(message)
    }

    private fun ingame(message: String, event: ClientChatReceivedEvent) {
        if (!ZombiesAddon.instance.config.koreanPatchersIngame) return
        if (isNotPlayZombies()) return

        when (message) {
            "This weapon is out of ammo! You can refill ammo at the place that you purchased the weapon or through collecting the Max Ammo Power Up." ->
                addChat("§c§l무기는 탄약이 없습니다! 무기를 구입한 곳에서 탄약을 채우거나§r§9§l 탄약 충전§c§l 파워업을 획득하세요.")
            "이 행운의 상자는 지금 사용할 수 없습니다! 묘지에서 다른 활성화된 행운의 상자를 찾으세요!" ->
                addChat("§c이 행운의 상자는 지금 사용할 수 없습니다! 지하실에서 다른 활성화된 행운의 상자를 찾으세요!")
            else -> return
        }
        event.isCanceled = true
    }

    private fun zombiesOverlay(message: String) {
        if (!ZombiesAddon.instance.config.koreanPatchersZombiesOverlay) return

        if (message.startsWith("온라인: ")) addChat("ONLINE: ${message.split(":")[1].trim()}")
        if ("님이 참여했습니다!" in message) {
            val name = message.split(" ")[0]
            val number = message.split(" ")[3].split("/")[0].replace(Regex("[^0-9]"), "")
            addChat("$name has joined ($number/4)!")
        }
        if ("님이 나갔습니다!" in message) {
            val name = message.split(" ")[0]
            addChat("$name has quit!")
        }
        if ("서버로 이동합니다" in message) {
            val text = message.split(" ")[0]
            addChat("Sending you to $text!")
        }
    }

    override fun onEvent(event: Event) {
        if (event !is ClientChatPrintedEvent) return
        if (isNotPlayZombies()) return
        val message = getText(event.message.unformattedText) // 왜인지 모르겠지만 색깔 코드가 제거가 안된다.
        if ("<" in message) return
        if (ZombiesAddon.instance.config.koreanPatchersSst) sst(event, message)
//        if (ZombiesAddon.instance.config.koreanPatchersZombiesUtils) zombiesutils(event, message)
    }

    private fun sst(event: ClientChatPrintedEvent, message: String) {
        if (" seconds to clean up after the last wave." in message) {
            val time = message.replace(Regex("[^0-9.]"), "")
            addChat("§e마지막 웨이브 이후 넘어가는데 §c${time.substring(0, time.length - 1)}§e초가 걸렸습니다.")
            event.isCanceled = true
        }
        if ("You completed Round " in message) {
            addChat("                  §c라운드 ${message.split("in")[0].replace(Regex("[^0-9]"), "")}§e을(를) §a${message.split("in")[1].replace(Regex("[^0-9:]"), "")}§e에 완료했습니다!")
            event.isCanceled = true
        }
    }

//    private fun zombiesutils(event: ClientChatPrintedEvent, message: String) {
//
//    }

    companion object {
        val instance = KoreanPatchers()
    }
}
