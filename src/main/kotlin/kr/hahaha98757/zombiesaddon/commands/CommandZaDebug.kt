package kr.hahaha98757.zombiesaddon.commands

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.data.ServerNumber
import kr.hahaha98757.zombiesaddon.enums.GameMode
import kr.hahaha98757.zombiesaddon.enums.ZombiesMap
import kr.hahaha98757.zombiesaddon.utils.addChat
import kr.hahaha98757.zombiesaddon.utils.addChatLine
import net.minecraft.command.ICommandSender
import net.minecraft.command.NumberInvalidException
import net.minecraft.command.WrongUsageException
import net.minecraft.util.BlockPos

class CommandZaDebug: CustomCommandBase() {
    private var addMessage = false
    override fun getCommandName() = "za_debug"

    override fun getCommandUsage(sender: ICommandSender?) = "/za_debug [isNotZombies|serverNumber|new|map|pass|helicopter|end|remove]"
    override fun runCommand(sender: ICommandSender, args: Array<String>) {
        if (args.isEmpty()) {
            ZombiesAddon.instance.debug = !ZombiesAddon.instance.debug
            addChat("디버그 모드를 ${if (ZombiesAddon.instance.debug) "활성화" else "비활성화"}했습니다.")
            if (!addMessage) addChatLine("디버그 모드 관련 메시지는 번역되어있지 않습니다.\n게임이 종료되는 등 문제가 발생할 수 있습니다.")
            addMessage = true
            return
        }
        if (!ZombiesAddon.instance.debug) {
            addChat("디버그 모드가 비활성화되어있습니다.")
            return
        }
        when (args[0]) {
            "isNotZombies" -> {
                if (args.size < 2) {
                    addChat("isNotZombies: $debugIsNotZombies")
                    return
                }
                debugIsNotZombies = when (args[1]) {
                    "true" -> true
                    "false" -> false
                    else -> throw WrongUsageException("/za_debug isNotZombies [true|false]")
                }
                addChat("isNotZombies를 $debugIsNotZombies(으)로 설정했습니다.")
            }
            "serverNumber" -> {
                if (args.size < 2) {
                    addChat("서버 번호: $debugServerNumber")
                    return
                }
                debugServerNumber = if (args[1] == "null") null else ServerNumber(args[1])
                addChat("서버 번호를 $debugServerNumber(으)로 설정했습니다.")
            }
            "new" -> {
                runCatching { ZombiesAddon.instance.gameManager.splitOrNew(0) }.onFailure {
                    addChat("게임을 시작하는데 실패했습니다: ${it.message ?: "알 수 없음"}")
                }.onSuccess {
                    addChat("새 게임을 시작했습니다.")
                    addChat("서버 번호: $debugServerNumber, 게임모드: Dead End Normal")
                }
            }
            "map" -> {
                val game = ZombiesAddon.instance.gameManager.game ?: run {
                    addChat("게임이 시작되지 않았습니다.")
                    return
                }
                if (args.size == 1) {
                    addChat("게임모드: ${game.gameMode}")
                    return
                }
                when (args[1]) {
                    "de" -> ZombiesAddon.instance.gameManager.game?.gameMode = GameMode.DEAD_END_NORMAL
                    "bb" -> ZombiesAddon.instance.gameManager.game?.gameMode = GameMode.BAD_BLOOD_NORMAL
                    "aa" -> ZombiesAddon.instance.gameManager.game?.gameMode = GameMode.ALIEN_ARCADIUM
                    "pr" -> ZombiesAddon.instance.gameManager.game?.gameMode = GameMode.PRISON_NORMAL
                    else -> throw WrongUsageException("/za_debug map [de|bb|aa|pr]")
                }
                addChat("게임모드를 ${game.gameMode}(으)로 변경했습니다.")
            }
            "pass" -> {
                val game = ZombiesAddon.instance.gameManager.game ?: run {
                    addChat("게임이 시작되지 않았습니다.")
                    return
                }
                if (args.size < 2) throw WrongUsageException("/za_debug pass <라운드>")
                val round = args[1].toIntOrNull() ?: throw NumberInvalidException("commands.generic.num.invalid", args[1])
                if (round < 0) throw NumberInvalidException("commands.generic.num.tooSmall", args[1], 0)
                game.pass(round)
                addChat("라운드 $round(을)를 통과했습니다.")
            }
            "helicopter" -> {
                val game = ZombiesAddon.instance.gameManager.game ?: run {
                    addChat("게임이 시작되지 않았습니다.")
                    return
                }
                if (game.gameMode.map != ZombiesMap.PRISON) {
                    addChat("헬리콥터 탈출은 Prison 맵에서만 가능합니다.")
                    return
                }
                game.helicopter()
                addChat("헬리콥터 탈출을 시작합니다.")
            }
            "end" -> {
                val game = ZombiesAddon.instance.gameManager.game ?: run {
                    addChat("게임이 시작되지 않았습니다.")
                    return
                }
                if (args.size < 2) throw WrongUsageException("/za_debug end <win|lose>")
                when (args[1]) {
                    "win" -> {
                        ZombiesAddon.instance.gameManager.endGame(game.serverNumber, true)
                        addChat("게임을 승리로 종료했습니다.")
                    }
                    "lose" -> {
                        ZombiesAddon.instance.gameManager.endGame(game.serverNumber, false)
                        addChat("게임을 패배로 종료했습니다.")
                    }
                    else -> throw WrongUsageException("/za_debug end <win|lose>")
                }
            }
            "remove" -> {
                ZombiesAddon.instance.gameManager.removeGame()
                addChat("종료된 게임을 제거했습니다.")
            }
            else -> throw WrongUsageException(getCommandUsage(null))
        }
    }
    override fun addTabCompletionOptions(sender: ICommandSender?, args: Array<String?>, pos: BlockPos?): List<String>? {
        return when (args.size) {
            1 -> getListOfStringsMatchingLastWord(args, "isNotZombies", "serverNumber", "new", "map", "pass", "helicopter", "end", "remove")
            2 -> when (args[0]) {
                "isNotZombies" -> getListOfStringsMatchingLastWord(args, "false", "true")
                "serverNumber" -> getListOfStringsMatchingLastWord(args, "null")
                "map" -> getListOfStringsMatchingLastWord(args, "de", "bb", "aa", "pr")
                "end" -> getListOfStringsMatchingLastWord(args, "win", "lose")
                else -> null
            }
            else -> null
        }
    }

    companion object {
        var debugIsNotZombies = true
        var debugServerNumber: ServerNumber? = null
    }
}
