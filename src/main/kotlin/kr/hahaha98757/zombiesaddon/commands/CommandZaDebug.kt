package kr.hahaha98757.zombiesaddon.commands

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.data.ServerNumber
import kr.hahaha98757.zombiesaddon.enums.Difficulty
import kr.hahaha98757.zombiesaddon.enums.GameMode
import kr.hahaha98757.zombiesaddon.enums.ZombiesMap
import kr.hahaha98757.zombiesaddon.utils.addChat
import kr.hahaha98757.zombiesaddon.utils.addTranslatedChat
import kr.hahaha98757.zombiesaddon.utils.addTranslatedChatLine
import kr.hahaha98757.zombiesaddon.utils.getTranslatedString
import net.minecraft.command.ICommandSender
import net.minecraft.command.NumberInvalidException
import net.minecraft.command.WrongUsageException
import net.minecraft.util.BlockPos

object CommandZaDebug: CustomCommandBase() {
    var isNotZombies = true
    var serverNumber: ServerNumber? = null
    var gameMode = GameMode.DEAD_END_NORMAL


    private var addMessage = false
    override fun getCommandName() = "za_debug"

    override fun getCommandUsage(sender: ICommandSender?) = "/za_debug [isNotZombies|serverNumber|gameMode|new|pass|helicopter|end|remove]"
    override fun runCommand(sender: ICommandSender, args: Array<String>) {
        if (args.isEmpty()) {
            ZombiesAddon.instance.debug = !ZombiesAddon.instance.debug
            addTranslatedChat("zombiesaddon.debug.toggle", if (ZombiesAddon.instance.debug) "§aon" else "§coff")
            if (!addMessage) addTranslatedChatLine("zombiesaddon.debug.info")
            addMessage = true
            return
        }
        if (!ZombiesAddon.instance.debug) {
            addTranslatedChat("zombiesaddon.debug.disabled")
            return
        }
        when (args[0]) {
            "isNotZombies" -> {
                if (args.size < 2) {
                    addTranslatedChatLine("zombiesaddon.debug.isNotZombies.info")
                    addChat("isNotZombies: ${isNotZombies.withColor()}")
                    return
                }
                isNotZombies = when (args[1]) {
                    "false" -> false
                    "true" -> true
                    else -> throw WrongUsageException("/za_debug isNotZombies [false|true]")
                }
                addTranslatedChat("zombiesaddon.debug.setFlag", "isNotZombies", isNotZombies.withColor())
            }
            "serverNumber" -> {
                if (args.size < 2) {
                    addTranslatedChatLine("zombiesaddon.debug.serverNumber.info")
                    addChat("${getTranslatedString("zombiesaddon.debug.serverNumber")}: ${serverNumber.withColor()}")
                    return
                }
                serverNumber = if (args[1] == "null") null else ServerNumber(args[1])
                addTranslatedChat("zombiesaddon.debug.setFlag", getTranslatedString("zombiesaddon.debug.serverNumber"), serverNumber.withColor())
            }
            "gameMode" -> {
                if (args.size < 2) {
                    addChat("${getTranslatedString("zombiesaddon.debug.gameMode")}: $gameMode")
                    return
                }
                val map = when (args[1]) {
                    "de" -> ZombiesMap.DEAD_END
                    "bb" -> ZombiesMap.BAD_BLOOD
                    "aa" -> ZombiesMap.ALIEN_ARCADIUM
                    "pr" -> ZombiesMap.PRISON
                    else -> throw WrongUsageException("/za_debug gameMode [de|bb|aa|pr] [normal|hard|rip]")
                }
                val difficulty = if (args.size < 3) Difficulty.NORMAL else when (args[2]) {
                    "normal" -> Difficulty.NORMAL
                    "hard" -> Difficulty.HARD
                    "rip" -> Difficulty.RIP
                    else -> throw WrongUsageException("/za_debug gameMode [de|bb|aa|pr] [normal|hard|rip]")
                }
                gameMode = map.getNormalGameMode().appliedDifficulty(difficulty)
                addTranslatedChat("zombiesaddon.debug.setFlag", getTranslatedString("zombiesaddon.debug.gameMode"), "§a${gameMode}")
                val game = ZombiesAddon.instance.gameManager.game ?: return
                game.gameMode = gameMode
            }
            "new" -> {
                runCatching { ZombiesAddon.instance.gameManager.splitOrNew(0, true) }.onSuccess {
                    addTranslatedChat("zombiesaddon.debug.new", gameMode, serverNumber!!)
                }.onFailure { addTranslatedChat("zombiesaddon.message.failed.splitOrNew", it.message ?: "알 수 없음(Unknown)") }
            }
            "pass" -> {
                val game = ZombiesAddon.instance.gameManager.game ?: run {
                    addTranslatedChat("zombiesaddon.debug.noGame")
                    return
                }
                if (args.size < 2) throw WrongUsageException("zombiesaddon.commands.za_debug.usage.pass")
                val round = args[1].toIntOrNull() ?: throw NumberInvalidException("commands.generic.num.invalid", args[1])
                if (round <= 0) throw NumberInvalidException("commands.generic.num.tooSmall", args[1], 1)
                game.pass(round, true)
                addTranslatedChat("zombiesaddon.debug.pass", round)
            }
            "helicopter" -> {
                val game = ZombiesAddon.instance.gameManager.game ?: run {
                    addTranslatedChat("zombiesaddon.debug.noGame")
                    return
                }
                if (game.gameMode.map != ZombiesMap.PRISON) {
                    addTranslatedChat("zombiesaddon.debug.helicopter.notPrison")
                    return
                }
                game.helicopter()
                addTranslatedChat("zombiesaddon.debug.helicopter.called")
            }
            "end" -> {
                val game = ZombiesAddon.instance.gameManager.game ?: run {
                    addTranslatedChat("zombiesaddon.debug.noGame")
                    return
                }
                if (args.size < 2) throw WrongUsageException("/za_debug end <win|lose>")
                val isWin = when (args[1]) {
                    "win" -> true
                    "lose" -> false
                    else -> throw WrongUsageException("/za_debug end <win|lose>")
                }
                ZombiesAddon.instance.gameManager.endGame(game.serverNumber, isWin)
                addTranslatedChat("zombiesaddon.debug.end", if (isWin) "§awin" else "§close")
            }
            "remove" -> {
                ZombiesAddon.instance.gameManager.removeGame()
                addTranslatedChat("zombiesaddon.debug.remove")
            }
            else -> throw WrongUsageException(getCommandUsage(null))
        }
    }
    override fun addTabCompletionOptions(sender: ICommandSender?, args: Array<String?>, pos: BlockPos?): List<String>? {
        if (!ZombiesAddon.instance.debug) return null
        return when (args.size) {
            1 -> getListOfStringsMatchingLastWord(args, "isNotZombies", "serverNumber", "gameMode", "new", "pass", "helicopter", "end", "remove")
            2 -> when (args[0]) {
                "isNotZombies" -> getListOfStringsMatchingLastWord(args, "false", "true")
                "serverNumber" -> getListOfStringsMatchingLastWord(args, "null")
                "gameMode" -> getListOfStringsMatchingLastWord(args, "de", "bb", "aa", "pr")
                "end" -> getListOfStringsMatchingLastWord(args, "win", "lose")
                else -> null
            }
            3 -> when (args[0]) {
                "gameMode" -> getListOfStringsMatchingLastWord(args, "normal", "hard", "rip")
                else -> null
            }
            else -> null
        }
    }
}

private fun Boolean.withColor() = if (this) "§atrue" else "§cfalse"

private fun ServerNumber?.withColor() = this?.let { return "§a$it" } ?: "§cnull"