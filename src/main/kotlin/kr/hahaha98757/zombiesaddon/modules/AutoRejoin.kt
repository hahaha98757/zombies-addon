package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.events.RoundStartEvent
import kr.hahaha98757.zombiesaddon.utils.RepeatedTask
import kr.hahaha98757.zombiesaddon.utils.Scoreboard
import kr.hahaha98757.zombiesaddon.utils.addTranslatedChat
import kr.hahaha98757.zombiesaddon.utils.sendChat

object AutoRejoin: ToggleableModule("Auto Rejoin", ZombiesAddon.instance.config.autoRejoinDefault) {
    override fun getKeyBinding() = ZombiesAddon.instance.keyBindings.toggleAutoRejoin
    override fun addToggleText(enabled: Boolean) =
        addTranslatedChat("zombiesaddon.modules.general.toggled", "Auto Rejoin", if (enabled) "§aon" else "§coff")

    override fun onRoundStart(event: RoundStartEvent) {
        addTranslatedChat("zombiesaddon.modules.autoRejoin.rejoining")
        sendChat("/l")
        RepeatedTask(endTask = { sendChat("/rejoin") }) {
            if (Scoreboard.isNotZombies) {
                sendChat("/rejoin")
                RepeatedTask.stop()
            }
        }
    }
}