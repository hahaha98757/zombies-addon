package kr.hahaha98757.zombiesaddon.commands

import kr.hahaha98757.zombiesaddon.modules.ZombiesStratViewer
import kr.hahaha98757.zombiesaddon.utils.addTranslationChat
import net.minecraft.command.CommandException
import net.minecraft.command.ICommandSender
import net.minecraft.command.NumberInvalidException
import net.minecraft.command.WrongUsageException

class CommandZsvLines: CustomCommandBase() {
    override fun getCommandName() = "zsvlines"
    override fun getCommandUsage(sender: ICommandSender?) = "zombiesaddon.commands.zsvlines.usage"
    override fun runCommand(sender: ICommandSender, args: Array<String>) {
        if (args.isEmpty()) throw WrongUsageException(getCommandUsage(null))

        try {
            val i = args[0].toInt()
            if (i < 1) throw CommandException("commands.generic.num.tooSmall", i, 1)

            ZombiesStratViewer.linesOfView = i
            ZombiesStratViewer.refreshActualLines()
            addTranslationChat("zombiesaddon.commands.zsvlines.success", "§a$i")
        } catch (_: NumberFormatException) {
            throw NumberInvalidException("commands.generic.num.invalid", args[0])
        }
    }
}