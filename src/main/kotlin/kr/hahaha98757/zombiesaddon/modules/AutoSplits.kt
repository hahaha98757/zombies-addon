package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.utils.addTranslationChat
import kr.hahaha98757.zombiesaddon.utils.logger
import kr.hahaha98757.zombiesaddon.utils.mc
import java.io.OutputStreamWriter
import java.net.Socket


object AutoSplits {
    fun startOrSplit() {
        if (!ZombiesAddon.instance.config.autoSplitsToggle) return
        sendCommand("startorsplit")
    }

    fun endGame(isWin: Boolean) {
        if (!ZombiesAddon.instance.config.autoSplitsToggle) return
        if (isWin) sendCommand("startorsplit")
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
            logger.warn("LiveSplit에 신호를 전송하는데 실패했습니다: $signal", e)
            mc.addScheduledTask { addTranslationChat("zombiesaddon.modules.autoSplits.failed", "§a$signal") }
        }
    }.start()
}