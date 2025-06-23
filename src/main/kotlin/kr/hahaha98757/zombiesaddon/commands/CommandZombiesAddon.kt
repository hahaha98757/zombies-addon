package kr.hahaha98757.zombiesaddon.commands

import kr.hahaha98757.zombiesaddon.MODID
import kr.hahaha98757.zombiesaddon.gui.hudposition.ConfigGui
import kr.hahaha98757.zombiesaddon.utils.DelayedTask
import kr.hahaha98757.zombiesaddon.utils.mc
import net.minecraft.command.ICommandSender
import net.minecraft.command.WrongUsageException
import net.minecraft.util.BlockPos

class CommandZombiesAddon: CustomCommandBase() {
    override fun getCommandName() = MODID
    override fun getCommandUsage(sender: ICommandSender?) = "/zombiesaddon hud"
    override fun runCommand(sender: ICommandSender, args: Array<String>) {
        if (args.isEmpty()) throw WrongUsageException(getCommandUsage(null))
        when (args[0]) {
            "hud" -> DelayedTask { mc.displayGuiScreen(ConfigGui()) }
            else -> throw WrongUsageException(getCommandUsage(sender))
        }
    }

    override fun addTabCompletionOptions(sender: ICommandSender?, args: Array<String?>, pos: BlockPos?): List<String>? =
        if (args.size == 1) getListOfStringsMatchingLastWord(args, "hud")
        else null
}