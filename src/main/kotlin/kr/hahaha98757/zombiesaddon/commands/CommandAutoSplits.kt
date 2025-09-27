package kr.hahaha98757.zombiesaddon.commands

import kr.hahaha98757.zombiesaddon.modules.AutoSplits
import net.minecraft.command.ICommandSender
import net.minecraft.command.WrongUsageException
import net.minecraft.util.BlockPos

class CommandAutoSplits: CustomCommandBase() {
    override fun getCommandName() = "autosplits"
    override fun getCommandUsage(sender: ICommandSender?) = "zombiesaddon.commands.autosplits.usage"
    override fun runCommand(sender: ICommandSender, args: Array<String>) {
        if (args.isEmpty()) throw WrongUsageException(getCommandUsage(null))
        AutoSplits.sendCommand(args[0])
    }

    override fun addTabCompletionOptions(sender: ICommandSender?, args: Array<String?>, pos: BlockPos?) =
        if (args.size == 1) getListOfStringsMatchingLastWord(args, "startorsplit", "split", "pause", "resume", "reset", "start") else null
}