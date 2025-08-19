package kr.hahaha98757.zombiesaddon.data

import kr.hahaha98757.zombiesaddon.utils.JsonLoader
import kr.hahaha98757.zombiesaddon.utils.logger
import java.io.File

data class CustomPlaySound(val name: String, val pitch: Float, val timing: Int, val playType: Int)

object CustomPlaySoundLoader {
    var cps: Array<CustomPlaySound>? = null
        private set

    fun loadFile() {
        runCatching { logger.info("Custom Play Sound 로드 시작...") }
        val file = File("config/zombiesaddon/CustomPlaySound.json")

        if (!file.exists()) cps = null

        cps = runCatching { JsonLoader.loadJsonFromFile(file, Array<CustomPlaySound>::class.java) }.getOrNull()
        runCatching { cps?.let { logger.info("Custom Play Sound 로드를 완료했습니다.") } ?: logger.warn("Custom Play Sound 로드를 실패했습니다.") }
    }
}