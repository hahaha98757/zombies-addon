package kr.hahaha98757.zombiesaddon.commands

import kr.hahaha98757.zombiesaddon.ZombiesAddon
import kr.hahaha98757.zombiesaddon.modules.recorder.Category
import kr.hahaha98757.zombiesaddon.utils.addTranslatedChat
import net.minecraft.command.ICommandSender
import net.minecraft.command.WrongUsageException
import net.minecraft.util.BlockPos

object CommandRecorder: CustomCommandBase() {
    override fun getCommandName() = "recorder"
    override fun getCommandUsage(sender: ICommandSender?) = "zombiesaddon.commands.recorder.usage"
    override fun runCommand(sender: ICommandSender, args: Array<String>) {
        if (args.isEmpty() || args[0] != "category") throw WrongUsageException(getCommandUsage(null))

        if (args.size == 1) addTranslatedChat("zombiesaddon.commands.recorder.get", Category.selectedCategory)
        else {
            Category.selectedCategory = args[1]
            addTranslatedChat("zombiesaddon.commands.recorder.set", Category.selectedCategory)
        }
    }

    override fun addTabCompletionOptions(sender: ICommandSender?, args: Array<String?>, pos: BlockPos?) =
        if (args.size == 1) getListOfStringsMatchingLastWord(args, "category")
        else if (args.size == 2 && args[0] == "category")
            getListOfStringsMatchingLastWord(args, ZombiesAddon.instance.config.recorderDefaultCategory)
        else null
}