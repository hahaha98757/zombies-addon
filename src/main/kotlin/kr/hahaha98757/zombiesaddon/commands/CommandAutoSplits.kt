package kr.hahaha98757.zombiesaddon.commands

import kr.hahaha98757.zombiesaddon.modules.AutoSplits
import net.minecraft.command.ICommandSender
import net.minecraft.command.WrongUsageException
import net.minecraft.util.BlockPos

class CommandAutoSplits: CustomCommandBase() {
    override fun getCommandName() = "autosplits"
    override fun getCommandUsage(sender: ICommandSender?) = "/autosplits <startorsplit|pause|reset>"
    override fun runCommand(sender: ICommandSender, args: Array<String>) {
        if (args.isEmpty()) throw WrongUsageException(getCommandUsage(null))

        when (val it = args[0]) {
            "startorsplit" -> AutoSplits.instance.sendCommand(it)
            "pause" -> AutoSplits.instance.sendCommand(it)
            "reset" -> AutoSplits.instance.sendCommand(it)
            else -> throw WrongUsageException(getCommandUsage(null))
        }
    }

    override fun addTabCompletionOptions(sender: ICommandSender?, args: Array<String?>, pos: BlockPos?): List<String>? {
        return if (args.isNotEmpty()) getListOfStringsMatchingLastWord(args, "startorsplit", "pause", "reset") else null
    }
}