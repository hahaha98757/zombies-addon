package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.events.GameEndEvent
import kr.hahaha98757.zombiesaddon.events.RoundStartEvent
import kr.hahaha98757.zombiesaddon.utils.addTranslationChat
import kr.hahaha98757.zombiesaddon.utils.mc
import java.io.OutputStreamWriter
import java.net.Socket


class AutoSplits: Module("Auto Splits") {
    companion object {
        val instance = AutoSplits()
    }

    override fun onRoundStart(event: RoundStartEvent) {
        sendCommand("startorsplit")
    }

    override fun onGameEnd(event: GameEndEvent) {
        if (event.isWin) sendCommand("startorsplit")
        else sendCommand("pause")
    }

    fun sendCommand(signal: String) = Thread {
        try {
            Socket(ZombiesAddon.instance.config.autoSplitsHost, ZombiesAddon.instance.config.autoSplitsPort).use { socket ->
                OutputStreamWriter(socket.getOutputStream()).use {
                    it.write("$signal\r\n")
                    it.flush()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mc.addScheduledTask { addTranslationChat("zombiesaddon.features.autoSplits.failed", "§a$signal") }
        }
    }.start()

    override fun isEnable() = ZombiesAddon.instance.config.autoSplitsToggle
}