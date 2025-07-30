@file:JvmName("Tools")

package kr.hahaha98757.zombiesaddon.utils

import kr.hahaha98757.zombiesaddon.MODID
import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.commands.CommandZaDebug
import kr.hahaha98757.zombiesaddon.data.ServerNumber
import kr.hahaha98757.zombiesaddon.enums.Status
import kr.hahaha98757.zombiesaddon.enums.ZombiesMap
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.util.*
import net.minecraftforge.fml.common.Loader
import java.io.File
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.*

private val serverNumberPattern = Regex(".*([mLM][0-9A-Z]+)")
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

fun isNotZombies() = if (ZombiesAddon.instance.debug) CommandZaDebug.debugIsNotZombies else Scoreboard.title != "ZOMBIES"

fun isNotPlayZombies(): Boolean {
    if (isNotZombies()) return true
    return ZombiesAddon.instance.gameManager.game == null
}

fun getServerNumber(): ServerNumber? {
    if (ZombiesAddon.instance.debug) return CommandZaDebug.debugServerNumber
    if (isNotZombies()) return null
    return runCatching { ServerNumber(Scoreboard.lines[0].replace(serverNumberPattern, "$1")) }.getOrNull()
}

fun getMap(): ZombiesMap? {
    if (ZombiesAddon.instance.debug) return ZombiesMap.DEAD_END
    val world = mc.theWorld ?: return null
    if (isNotZombies()) return null
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
    val playerState = arrayOfNulls<Status>(4)

    val lines = Scoreboard.lines

    for (i in 5..8) {
        val status = lines[i].runCatching { split(":")[1].trim() }.getOrNull() ?: run { playerState[i] = Status.QUIT }

        playerState[i] = when (status) {
            "REVIVE", "부활" -> Status.REVIVE
            "DEAD", "사망" ->  Status.DEAD
            "QUIT", "떠남" ->  Status.QUIT
            else -> Status.SURVIVE
        }
    }
    @Suppress("UNCHECKED_CAST")
    return playerState as Array<Status>
}

internal fun runBatchFileAndQuit(file: File, commands: String) {
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