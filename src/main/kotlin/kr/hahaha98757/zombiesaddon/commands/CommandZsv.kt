package kr.hahaha98757.zombiesaddon.commands

import kr.hahaha98757.zombiesaddon.modules.ZombiesStratViewer
import kr.hahaha98757.zombiesaddon.utils.addTranslationChat
import kr.hahaha98757.zombiesaddon.utils.mc
import net.minecraft.command.CommandException
import net.minecraft.command.ICommandSender
import net.minecraft.command.WrongUsageException
import net.minecraft.util.BlockPos
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets

class CommandZsv: CustomCommandBase() {
    override fun getCommandName() = "zsv"
    override fun getCommandUsage(sender: ICommandSender?) = "/zsv <off|URL>"

    override fun runCommand(sender: ICommandSender, args: Array<String>) {
        if (sender != mc.thePlayer) return
        if (args.isEmpty()) throw WrongUsageException(getCommandUsage(null))
        if (args[0] == "off") {
            ZombiesStratViewer.stratLines.clear()
            ZombiesStratViewer.stratLines += ""
            ZombiesStratViewer.currentLine = 0
            addTranslationChat("zombiesaddon.commands.zsv.success", "§coff")
            ZombiesStratViewer.enabled = false
            return
        }
        if (!args[0].startsWith("https://pastebin.com/raw/")) throw CommandException("zombiesaddon.commands.zsv.wrongUrl")

        Thread {
            try {
                val list = mutableListOf("")
                val url = URL(args[0])
                val connection = url.openConnection()
                connection.doInput = true
                connection.connect()
                BufferedReader(InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)).use { reader ->
                    var str: String?
                    while (reader.readLine().also { str = it } != null) list += str!!
                }
                mc.addScheduledTask {
                    ZombiesStratViewer.stratLines.clear()
                    ZombiesStratViewer.currentLine = 0
                    ZombiesStratViewer.stratLines += list
                    ZombiesStratViewer.enabled = true
                    ZombiesStratViewer.refreshActualLines()
                    addTranslationChat("zombiesaddon.commands.zsv.success", "§aon")
                }
            } catch (_: Exception) {
                mc.addScheduledTask { addTranslationChat("zombiesaddon.commands.zsv.failed") }
            }
        }.start()
    }

    override fun addTabCompletionOptions(sender: ICommandSender?, args: Array<String?>, pos: BlockPos?) =
        if (args.size == 1) getListOfStringsMatchingLastWord(args, "off") else null
}