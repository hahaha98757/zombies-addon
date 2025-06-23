package kr.hahaha98757.zombiesaddon.data

import kr.hahaha98757.zombiesaddon.utils.JsonLoader
import java.io.File

data class CustomPlaySound(val name: String, val pitch: Float, val timing: Int, val playType: Int)

object CustomPlaySoundLoader {
    var cps: Array<CustomPlaySound>? = null

    fun loadFile() {
        val file = File("config/zombiesaddon/CustomPlaySound.json")

        if (!file.exists()) cps = null

        cps = try {
            JsonLoader.loadJsonFromFile(file, Array<CustomPlaySound>::class.java)
        } catch (e: Exception) {
            null
        }
    }
}