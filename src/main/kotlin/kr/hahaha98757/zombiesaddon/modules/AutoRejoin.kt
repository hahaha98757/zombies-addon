package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.events.RoundStartEvent
import kr.hahaha98757.zombiesaddon.utils.RepeatedTask
import kr.hahaha98757.zombiesaddon.utils.Scoreboard
import kr.hahaha98757.zombiesaddon.utils.addTranslationChat
import kr.hahaha98757.zombiesaddon.utils.sendChat

object AutoRejoin: ToggleableModule("Auto Rejoin", ZombiesAddon.instance.config.autoRejoinDefault) {
    override fun getKeyBinding() = ZombiesAddon.instance.keyBindings.toggleAutoRejoin
    override fun addToggleText(enabled: Boolean) =
        addTranslationChat("zombiesaddon.modules.general.toggled", "§eAuto Rejoin", if (enabled) "§aon" else "§coff")

    override fun onRoundStart(event: RoundStartEvent) {
        addTranslationChat("zombiesaddon.modules.autoRejoin.rejoining")
        sendChat("/l")
        RepeatedTask(endTask = {
            sendChat("/rejoin")
        }) {
            if (Scoreboard.isNotZombies) {
                sendChat("/rejoin")
                RepeatedTask.stop()
            }
        }
    }
}