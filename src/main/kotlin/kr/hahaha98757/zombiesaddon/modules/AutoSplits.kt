package kr.hahaha98757.zombiesaddon.modules

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.utils.addTranslationChat
import kr.hahaha98757.zombiesaddon.utils.logger
import kr.hahaha98757.zombiesaddon.utils.mc
import java.net.Socket


object AutoSplits {
    fun startOrSplit() {
        if (!ZombiesAddon.instance.config.autoSplitsToggle) return
        sendCommand("startorsplit")
    }

    fun pause() {
        if (!ZombiesAddon.instance.config.autoSplitsToggle) return
        sendCommand("pause")
    }

    fun sendCommand(command: String) = Thread {
        try {
            Socket(ZombiesAddon.instance.config.autoSplitsHost, ZombiesAddon.instance.config.autoSplitsPort).use {
                val output = it.getOutputStream().bufferedWriter()

                output.write("$command\r\n")
                output.flush()
            }
        } catch (e: Exception) {
            logger.warn("LiveSplit에 명령어를 전달하는데 실패했습니다: $command", e)
            mc.addScheduledTask { addTranslationChat("zombiesaddon.modules.autoSplits.failed", "§a$command") }
        }
    }.start()
}