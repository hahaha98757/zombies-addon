package kr.hahaha98757.zombiesaddon.commands

import kr.hahaha98757.zombiesaddon.MODID
import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.enums.Difficulty
import kr.hahaha98757.zombiesaddon.gui.hudposition.ConfigGui
import kr.hahaha98757.zombiesaddon.utils.DelayedTask
import kr.hahaha98757.zombiesaddon.utils.addTranslationChat
import kr.hahaha98757.zombiesaddon.utils.isNotPlayZombies
import kr.hahaha98757.zombiesaddon.utils.mc
import net.minecraft.command.ICommandSender
import net.minecraft.command.WrongUsageException
import net.minecraft.util.BlockPos

class CommandZombiesAddon: CustomCommandBase() {
    override fun getCommandName() = MODID
    override fun getCommandUsage(sender: ICommandSender?) = "/zombiesaddon <hud|difficulty>"
    override fun runCommand(sender: ICommandSender, args: Array<String>) {
        if (args.isEmpty()) throw WrongUsageException(getCommandUsage(null))
        when (args[0]) {
            "hud" -> DelayedTask { mc.displayGuiScreen(ConfigGui()) }
            "difficulty" -> {
                if (args.size < 2) throw WrongUsageException("/zombiesaddon difficulty <normal|hard|rip>")
                if (isNotPlayZombies()) {
                    addTranslationChat("zombiesaddon.commands.zombiesaddon.difficulty.failed")
                    return
                }
                val gameManager = ZombiesAddon.instance.gameManager
                val difficulty = when (args[1]) {
                    "normal" -> {
                        gameManager.setDifficulty(Difficulty.NORMAL)
                        "§aNormal"
                    }
                    "hard" -> {
                        gameManager.setDifficulty(Difficulty.HARD)
                        "§cHard"
                    }
                    "rip" -> {
                        gameManager.setDifficulty(Difficulty.RIP)
                        "§4RIP"
                    }
                    else -> throw WrongUsageException("/zombiesaddon difficulty <normal|hard|rip>")
                }
                addTranslationChat("zombiesaddon.commands.zombiesaddon.difficulty", difficulty)
            }
            else -> throw WrongUsageException(getCommandUsage(null))
        }
    }

    override fun addTabCompletionOptions(sender: ICommandSender?, args: Array<String?>, pos: BlockPos?): List<String>? =
        if (args.size == 1) getListOfStringsMatchingLastWord(args, "hud", "difficulty")
        else if (args.size == 2 && args[0] == "difficulty") getListOfStringsMatchingLastWord(args, "normal", "hard", "rip")
        else null
}