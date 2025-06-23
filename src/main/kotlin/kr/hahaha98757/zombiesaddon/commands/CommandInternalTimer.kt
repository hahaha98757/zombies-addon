package kr.hahaha98757.zombiesaddon.commands

import kr.hahaha98757.zombiesaddon.modules.InternalTimer
import net.minecraft.command.ICommandSender
import net.minecraft.command.WrongUsageException
import net.minecraft.util.BlockPos

class CommandInternalTimer: CustomCommandBase() {
    override fun getCommandName() = "internaltimer"
    override fun getCommandUsage(sender: ICommandSender?) = "/internaltimer <run|stop|reset>"
    override fun runCommand(sender: ICommandSender, args: Array<String>) {
        if (args.isEmpty()) throw WrongUsageException(getCommandUsage(null))

        when (args[0]) {
            "run" -> InternalTimer.instance.run()
            "stop" -> InternalTimer.instance.stop()
            "reset" -> InternalTimer.instance.reset()
            else -> throw WrongUsageException(getCommandUsage(null))
        }
    }

    override fun addTabCompletionOptions(sender: ICommandSender?, args: Array<String?>, pos: BlockPos?): List<String>? {
        return if (args.isNotEmpty()) getListOfStringsMatchingLastWord(args, "run", "stop", "reset") else null
    }
}