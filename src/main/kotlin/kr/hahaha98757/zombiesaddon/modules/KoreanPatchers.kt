package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.events.ClientChatPrintedEvent
import kr.hahaha98757.zombiesaddon.utils.addChat
import kr.hahaha98757.zombiesaddon.utils.getText
import kr.hahaha98757.zombiesaddon.utils.isNotZombies
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.Event

class KoreanPatchers: Module("Korean Patchers", true) {
    companion object {
        val instance = KoreanPatchers()
    }

    override fun onChat(event: ClientChatReceivedEvent) {
        ingame(event)
        zombiesOverlay(event)
    }

    private fun ingame(event: ClientChatReceivedEvent) {
        if (!ZombiesAddon.instance.config.koreanPatchersIngame) return
        if (isNotZombies()) return
        val message = event.message.unformattedText
        if ("<" in message) return

        when (message) {
            "This weapon is out of ammo! You can refill ammo at the place that you purchased the weapon or through collecting the Max Ammo Power Up." ->
                addChat("§c§l무기는 탄약이 없습니다! 무기를 구입한 곳에서 탄약을 채우거나§r§9§l 탄약 충전§c§l 파워업을 획득하세요.")
            "이 행운의 상자는 지금 사용할 수 없습니다! 묘지에서 다른 활성화된 행운의 상자를 찾으세요!" ->
                addChat("§c이 행운의 상자는 지금 사용할 수 없습니다! 지하실에서 다른 활성화된 행운의 상자를 찾으세요!")
            else -> return
        }
        event.isCanceled = true
    }

    private fun zombiesOverlay(event: ClientChatReceivedEvent) {
        if (!ZombiesAddon.instance.config.koreanPatchersZombiesOverlay) return
        val message = event.message.unformattedText
        if ("<" in message) return

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
        if (!ZombiesAddon.instance.config.koreanPatchersSST) return
        if (isNotZombies()) return
        val message = getText(event.message.unformattedText) // 왜인지 모르겠지만 색깔 코드가 제거가 안된다.
        if ("<" in message) return

        if (" seconds to clean up after the last wave." in message) {
            val time = message.replace(Regex("[^0-9.]"), "")
            event.isCanceled = true
            addChat("§e마지막 웨이브 이후 넘어가는데 §c${time.substring(0, time.length - 1)}§e초가 걸렸습니다.")
        }
        if ("You completed Round " in message) {
            event.isCanceled = true
            addChat("                  §c라운드 ${message.split("in")[0].replace(Regex("[^0-9]"), "")}§e을(를) §a${message.split("in")[1].replace(Regex("[^0-9:]"), "")}§e에 완료했습니다!")
        }
    }
}