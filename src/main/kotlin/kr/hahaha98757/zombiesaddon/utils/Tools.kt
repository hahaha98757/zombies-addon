package kr.hahaha98757.zombiesaddon.utils

import kr.hahaha98757.zombiesaddon.MODID
import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.commands.CommandZaDebug
import kr.hahaha98757.zombiesaddon.data.ServerNumber
import kr.hahaha98757.zombiesaddon.enums.Language
import kr.hahaha98757.zombiesaddon.enums.Status
import kr.hahaha98757.zombiesaddon.enums.ZombiesMap
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.resources.I18n
import net.minecraft.scoreboard.ScorePlayerTeam
import net.minecraft.util.*
import net.minecraftforge.fml.common.Loader
import java.io.File
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.*

// private 필드
private val serverNumberPattern = Regex(".*([mLM][0-9A-Z]+)")

// 상수
const val LINE = "§e-----------------------------------------------------"

// public 필드
val mc get() = Minecraft.getMinecraft()!!
val fr get() = mc.fontRendererObj!!
val modFile: File by lazy {
    var file = File("")
    for (mod in Loader.instance().modList) if (mod.modId == MODID) file = mod.source
    file
}
val unlegitMods = arrayOf("zombiesatellite", "zombiesexplorer", "TeammatesOutline", "zombieshelper")
val logger get() = ZombiesAddon.instance.logger

// 출력 관련 함수
fun addLine() = mc.thePlayer?.addChatMessage(ChatComponentText(LINE)) ?: logger.info("[addLine] $LINE")

@Suppress("LoggingSimilarMessage")
fun addChat(text: String) = mc.thePlayer?.addChatMessage(ChatComponentText(text)) ?: logger.info("[addChat] $text")
fun addChat(chatComponent: IChatComponent) = mc.thePlayer?.addChatMessage(chatComponent) ?: logger.info("[addChat] ${chatComponent.formattedText}")

fun addChatLine(text: String) = mc.thePlayer?.addChatMessage(ChatComponentText("$LINE\n$text\n$LINE")) ?: logger.info("[addChatLine] $LINE\n$text\n$LINE")

fun addTranslatedChat(key: String, vararg any: Any) = addChat(getTranslatedString(key, any = any))

fun addTranslatedChatLine(key: String, vararg any: Any) = addChatLine(getTranslatedString(key, any = any))

fun sendChat(text: String) = mc.thePlayer?.sendChatMessage(text) ?: logger.info("[sendChat] $text")

// 문자열 관련 함수
fun String.withoutColor() = EnumChatFormatting.getTextWithoutFormattingCodes(this)!!

@JvmOverloads
fun getTranslatedString(key: String, withColor: Boolean = true, vararg any: Any): String {
    val text = key.translate(*any)
    return if (withColor) text else text.withoutColor()
}

// 화면 관련 함수
fun getX(): Float = ScaledResolution(mc).scaledWidth.toFloat()
fun getX(text: String): Float = getX() - fr.getStringWidth(text) - 1

fun getY(): Float = ScaledResolution(mc).scaledHeight.toFloat()
fun getYFont(): Float = getY() - fr.FONT_HEIGHT - 1

// 상태 관련 함수
fun isDisable() = !ZombiesAddon.instance.config.enableMod

fun isHypixel(): Boolean {
    val ip = mc.currentServerData?.serverIP ?: return false
    return ip.matches(Regex("(.+\\.)?(hypixel\\.net)(:25565)?"))
}

fun isNotPlayZombies(): Boolean {
    if (mc.thePlayer == null || mc.theWorld == null) return true
    if (Scoreboard.isNotZombies) return true
    return ZombiesAddon.instance.gameManager.game == null
}

fun getServerNumber(): ServerNumber? {
    if (ZombiesAddon.instance.debug) return CommandZaDebug.debugServerNumber
    if (Scoreboard.isNotZombies) return null
    return runCatching { ServerNumber(Scoreboard.lines[0].replace(serverNumberPattern, "$1")) }.getOrNull()
}

fun getMap(): ZombiesMap? {
    if (ZombiesAddon.instance.debug) return ZombiesMap.DEAD_END
    val world = mc.theWorld ?: return null
    if (Scoreboard.isNotZombies) return null
    val pos = BlockPos(44, 71, 0)
    if (!world.isBlockLoaded(pos)) return null

    return when (world.getBlockState(pos).block.unlocalizedName) {
        "tile.air" -> ZombiesMap.DEAD_END
        "tile.cloth" -> ZombiesMap.BAD_BLOOD
        "tile.stoneSlab" -> ZombiesMap.ALIEN_ARCADIUM
        "tile.woodSlab" -> ZombiesMap.PRISON
        else -> null
    }
}

fun getPlayerStatus(): Array<Status> {
    val playerState = arrayOf(Status.SURVIVE, Status.SURVIVE, Status.SURVIVE, Status.SURVIVE)

    val lines = Scoreboard.lines

    for (i in 5..8) {
        val status = runCatching { lines[i].split(":")[1].trim() }.getOrNull() ?: run { playerState[i - 5] = Status.QUIT }

        playerState[i - 5] = when (status) {
            "REVIVE", "부활" -> Status.REVIVE
            "DEAD", "사망" ->  Status.DEAD
            "QUIT", "떠남" ->  Status.QUIT
            else -> Status.SURVIVE
        }
    }
    return playerState
}

// 기타 함수
fun String.withNameColor(): String {
    val default = "§f$this"
    if (isNotPlayZombies()) return default
    val scoreboard = mc.theWorld.scoreboard
    val objective = scoreboard.getObjectiveInDisplaySlot(1) ?: return default

    (scoreboard.getSortedScores(objective) ?: return default).forEach {
        val team = scoreboard.getPlayersTeam(it.playerName)
        val line = ScorePlayerTeam.formatPlayerName(team, it.playerName).trim().replace(Regex("[^§A-Za-z0-9_:]"), "")
        if (this in line) {
            val color = line.substring(0..1)
            return "$color$this"
        }
    }
    return default
}

internal fun runBatchFileAndQuit(file: File, commands: String) {
    file.writeText(commands.replace(Regex("\r?\n"), "\r\n"))
    Runtime.getRuntime().exec("cmd /c start ${file.absolutePath}")
    logger.info("게임을 종료합니다.")
    mc.shutdown()
}

// private 함수
private fun String.translate(vararg any: Any): String {
    val lang = ZombiesAddon.instance.config.language
    val langCode = when (lang) {
        Language.AUTO -> return I18n.format(this, *any)
        Language.KO_KR -> "ko_KR"
        Language.EN_US -> "en_US"
    }
    val langFile = Properties()
    val resourceLocation = ResourceLocation(MODID, "lang/$langCode.lang")
    mc.resourceManager.runCatching {
        InputStreamReader(getResource(resourceLocation).inputStream, StandardCharsets.UTF_8).use { langFile.load(it) }
    }.getOrElse { return I18n.format(this) }
    return langFile.getProperty(this, this)
}