package kr.hahaha98757.zombiesaddon.commands

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.enums.Difficulty
import kr.hahaha98757.zombiesaddon.modules.WaveDelays
import kr.hahaha98757.zombiesaddon.utils.addTranslationChat
import net.minecraft.command.ICommandSender
import net.minecraft.command.WrongUsageException
import net.minecraft.util.BlockPos

class CommandWaveDelays: CustomCommandBase() {
    override fun getCommandName() = "wavedelays"
    override fun getCommandUsage(sender: ICommandSender?) = "/wavedelays <difficulty|offset> <normal|hard|rip>"
    override fun runCommand(sender: ICommandSender, args: Array<String>) {
        if (args.isEmpty() || args.size < 2) throw WrongUsageException(getCommandUsage(null))

        when (args[0]) {
            "difficulty" -> {
                val difficulty = when (args[1].lowercase()) {
                    "normal" -> {
                        WaveDelays.instance.difficulty = Difficulty.NORMAL
                        "§aNormal"
                    }
                    "hard" -> {
                        WaveDelays.instance.difficulty = Difficulty.HARD
                        "§cHard"
                    }
                    "rip" -> {
                        WaveDelays.instance.difficulty = Difficulty.RIP
                        "§4RIP"
                    }
                    else -> throw WrongUsageException(getCommandUsage(null))
                }
                addTranslationChat("zombiesaddon.commands.wavedelays.difficulty", difficulty)
            }
            "offset" -> {
                val offset = parseInt(args[1], -1000, 1000)
                ZombiesAddon.instance.config.waveDelaysRLModeOffset = offset
                addTranslationChat("zombiesaddon.commands.wavedelays.offset", "§a$offset")
            }
        }
    }

    override fun addTabCompletionOptions(sender: ICommandSender?, args: Array<String?>, pos: BlockPos?): List<String>? {
        if (args.isEmpty()) return null
        if (args.size == 1) return getListOfStringsMatchingLastWord(args, "difficulty", "offset")
        if (args.size != 2) return null
        return when (args[0]) {
            "difficulty" -> getListOfStringsMatchingLastWord(args, "normal", "hard", "rip")
            "offset" -> getListOfStringsMatchingLastWord(args, "-28")
            else -> null
        }
    }
}