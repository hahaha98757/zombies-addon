package kr.hahaha98757.zombiesaddon.commands

import kr.hahaha98757.zombiesaddon.MODID
import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.enums.Difficulty
import kr.hahaha98757.zombiesaddon.gui.hudposition.ConfigGui
import kr.hahaha98757.zombiesaddon.update.UpdateChecker
import kr.hahaha98757.zombiesaddon.utils.DelayedTask
import kr.hahaha98757.zombiesaddon.utils.addTranslatedChat
import kr.hahaha98757.zombiesaddon.utils.isNotPlayZombies
import kr.hahaha98757.zombiesaddon.utils.mc
import net.minecraft.command.ICommandSender
import net.minecraft.command.WrongUsageException
import net.minecraft.util.BlockPos

object CommandZombiesAddon: CustomCommandBase() {
    override fun getCommandName() = MODID
    override fun getCommandUsage(sender: ICommandSender?) = "/zombiesaddon <difficulty|hud|checkUpdate>"
    override fun runCommand(sender: ICommandSender, args: Array<String>) {
        if (args.isEmpty()) throw WrongUsageException(getCommandUsage(null))
        when (args[0]) {
            "difficulty" -> {
                if (args.size < 2) throw WrongUsageException("/zombiesaddon difficulty <normal|hard|rip>")
                if (isNotPlayZombies()) {
                    addTranslatedChat("zombiesaddon.commands.zombiesaddon.difficulty.failed")
                    return
                }
                val game = ZombiesAddon.instance.gameManager.game ?: return
                val difficulty = when (args[1]) {
                    "normal" -> {
                        game.changeDifficulty(Difficulty.NORMAL)
                        "§aNormal"
                    }
                    "hard" -> {
                        game.changeDifficulty(Difficulty.HARD)
                        "§cHard"
                    }
                    "rip" -> {
                        game.changeDifficulty(Difficulty.RIP)
                        "§4RIP"
                    }
                    else -> throw WrongUsageException("/zombiesaddon difficulty <normal|hard|rip>")
                }
                addTranslatedChat("zombiesaddon.commands.zombiesaddon.difficulty", difficulty)
            }
            "hud" -> DelayedTask { mc.displayGuiScreen(ConfigGui()) }
            "checkUpdate" -> UpdateChecker.initAndCheck()
            else -> throw WrongUsageException(getCommandUsage(null))
        }
    }

    override fun addTabCompletionOptions(sender: ICommandSender?, args: Array<String?>, pos: BlockPos?): List<String>? =
        if (args.size == 1) getListOfStringsMatchingLastWord(args, "difficulty", "hud", "checkUpdate")
        else if (args.size == 2 && args[0] == "difficulty") getListOfStringsMatchingLastWord(args, "normal", "hard", "rip")
        else null
}