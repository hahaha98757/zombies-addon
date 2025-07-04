@file:JvmName("Tools")

package kr.hahaha98757.zombiesaddon.utils

import kr.hahaha98757.zombiesaddon.MODID
import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.enums.Map
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.scoreboard.ScorePlayerTeam
import net.minecraft.util.*
import net.minecraftforge.fml.common.Loader
import java.io.File
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.*

const val LINE = "§e-----------------------------------------------------"
val mc by lazy { Minecraft.getMinecraft()!! }
val fr by lazy { mc.fontRendererObj!! }
val modFile: File by lazy {
    var file = File("")
    for (mod in Loader.instance().modList) if (mod.modId == MODID) file = mod.source
    file
}
val unlegitMods = arrayOf("zombiesatellite", "zombiesexplorer", "TeammatesOutline", "zombieshelper")

fun isDisable() = !ZombiesAddon.instance.config.enableMod

fun getText(text: String): String = EnumChatFormatting.getTextWithoutFormattingCodes(text)

fun addLine() = mc.thePlayer?.addChatMessage(ChatComponentText(LINE)) ?: println(LINE)

fun addChat(text: String) = mc.thePlayer?.addChatMessage(ChatComponentText(text)) ?: println(text)
fun addChat(chatComponent: IChatComponent) = mc.thePlayer?.addChatMessage(chatComponent) ?: println(chatComponent.formattedText)

fun addChatLine(text: String) = mc.thePlayer?.addChatMessage(ChatComponentText("$LINE\n$text\n$LINE")) ?: println("$LINE\n$text\n$LINE")

fun addTranslationChat(key: String, vararg any: Any) = addChat(getTranslatedString(key, any = any))

fun addTranslationChatLine(key: String, vararg any: Any) = addChatLine(getTranslatedString(key, any = any))

fun sendChat(text: String) = mc.thePlayer?.sendChatMessage(text) ?: println("sendChat: $text")

@JvmOverloads
fun getTranslatedString(key: String, withColor: Boolean = true, vararg any: Any): String {
    val chatComponentTranslation = ChatComponentTranslation(getTranslateKey(key), *any)
    return if (withColor) chatComponentTranslation.formattedText else chatComponentTranslation.unformattedText // 맨날 까먹네 formattedText는 색상 유지, 다른거는 색상 제거
}

fun getX(): Float = ScaledResolution(mc).scaledWidth.toFloat()
fun getX(text: String): Float = getX() - fr.getStringWidth(text) - 1

fun getY(): Float = ScaledResolution(mc).scaledHeight.toFloat()
fun getYFont(): Float = getY() - fr.FONT_HEIGHT - 1

fun isRoundText(text: String) = "Round" in text || "라운드" in text

fun isNotZombies(): Boolean {
    if (mc.theWorld == null || mc.thePlayer == null) return true

    val scoreboard = mc.theWorld.scoreboard
    val scoreObject = scoreboard.getObjectiveInDisplaySlot(1) ?: return true

    return getText(scoreObject.displayName) != "ZOMBIES"
}

fun isNotPlayZombies(): Boolean {
    val scoreboard = getScoreboard() ?: return true

    return !(scoreboard[10]?.contains(mc.thePlayer.name) ?: false)
}

fun getMap(): Map? {
    val world = mc.theWorld ?: return null
    if (isNotZombies()) return null
    val pos = BlockPos(44, 71, 0)
    if (!world.isBlockLoaded(pos)) return null

    return when (world.getBlockState(pos).block.unlocalizedName) {
        "tile.air" -> Map.DEAD_END
        "tile.cloth" -> Map.BAD_BLOOD
        "tile.stoneSlab" -> Map.ALIEN_ARCADIUM
        "tile.woodSlab" -> Map.PRISON
        else -> null
    }
}

fun getScoreboard(): kotlin.collections.Map<Int, String>? {
    if (mc.theWorld == null || mc.thePlayer == null) return null

    val scoreboard = mc.theWorld.scoreboard
    val scoreObjective = scoreboard.getObjectiveInDisplaySlot(1) ?: return null
    if (EnumChatFormatting.getTextWithoutFormattingCodes(scoreObjective.displayName) != "ZOMBIES") return null

    val scoreboards = mutableMapOf<Int, String>()
    for (score in scoreboard.getSortedScores(scoreObjective))
        scoreboards[score.scorePoints] =
            getText(ScorePlayerTeam.formatPlayerName(scoreboard.getPlayersTeam(score.playerName), score.playerName)).replace(Regex("[^A-Za-z0-9가-힣:_.,/\\s]"), "").trim()
    return scoreboards
}

fun getRound(): Int {
    val scoreboard = getScoreboard() ?: return 0

    val str = scoreboard[13] ?: return 0
    return str.runCatching { replace(Regex("[^0-9]"), "").toInt() }.getOrElse { 0 }
}

/** 인덱스 3은 thePlayer의 상태(0: 살아있음, 1: 부활, 2: 사망, 3: 떠남) */
fun getPlayerState(): IntArray {
    val playerState = IntArray(4)
    val scoreboard = getScoreboard() ?: return playerState

    for (i in 7..10) {
        var str = scoreboard[i] ?: continue


        str = str.runCatching { split(":")[1].trim() }.getOrNull() ?: continue

        when (str) {
            "REVIVE", "부활" -> playerState[0]++
            "DEAD", "사망" -> playerState[1]++
            "QUIT", "떠남" -> playerState[2]++
        }
        if (i == 10) when (str) {
            "REVIVE", "부활" -> playerState[3] = 1
            "DEAD", "사망" -> playerState[3] = 2
            "QUIT", "떠남" -> playerState[3] = 3
            else -> playerState[3] = 0
        }
    }
    return playerState
}

fun runBatchFileAndQuit(file: File, commands: String) {
    file.writeText(commands.replace("\n", "\r\n"))
    Runtime.getRuntime().exec("cmd /c start ${file.absolutePath}")
    mc.shutdown()
}

private fun getTranslateKey(key: String): String {
    val lang = ZombiesAddon.instance.config.language
    var langCode = "en_US"
    when (lang) {
        "Auto" -> return key
        "English (US)" -> langCode = "en_US"
        "한국어 (한국)" -> langCode = "ko_KR"
    }
    val langFile = Properties()
    val resourceLocation = ResourceLocation(MODID, "lang/$langCode.lang")
    try {
        InputStreamReader(mc.resourceManager.getResource(resourceLocation).inputStream, StandardCharsets.UTF_8).use { langFile.load(it) }
    } catch (_: Exception) {
        return key
    }
    return langFile.getProperty(key, key)
}