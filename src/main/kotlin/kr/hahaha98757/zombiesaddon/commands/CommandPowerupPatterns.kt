package kr.hahaha98757.zombiesaddon.commands

import kr.hahaha98757.zombiesaddon.modules.PowerupPatterns
import kr.hahaha98757.zombiesaddon.utils.addTranslationChat
import net.minecraft.command.ICommandSender
import net.minecraft.command.WrongUsageException
import net.minecraft.util.BlockPos

class CommandPowerupPatterns: CustomCommandBase() {
    override fun getCommandName() = "poweruppatterns"
    override fun getCommandUsage(sender: ICommandSender?) = "zombiesaddon.commands.poweruppatterns.usage"
    override fun getCommandAliases() = listOf("pow")
    override fun runCommand(sender: ICommandSender, args: Array<String>) {
        if (args.isEmpty()) throw WrongUsageException(getCommandUsage(null))

        when (args[0]) {
            "ins" -> {
                if (args.size == 1) PowerupPatterns.instance.insTimer = true
                else {
                    when (args[1]) {
                        "reset" -> {
                            addTranslationChat("zombiesaddon.commands.poweruppatterns.success.reset", "§cInsta Kill")
                            PowerupPatterns.instance.queuedInsPattern = 0
                        }
                        "2", "3" -> {
                            addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§cInsta Kill", "§a${args[1]}")
                            PowerupPatterns.instance.queuedInsPattern = args[1].toInt()
                        }
                        else -> throw WrongUsageException("/poweruppatterns ins [reset|2|3]")
                    }
                }
            }
            "max" -> {
                if (args.size == 1) PowerupPatterns.instance.maxTimer = true
                else {
                    when (args[1]) {
                        "reset" -> {
                            addTranslationChat("zombiesaddon.commands.poweruppatterns.success.reset", "§9Max Ammo")
                            PowerupPatterns.instance.queuedMaxPattern = 0
                        }
                        "2", "3" -> {
                            addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§9Max Ammo", "§a${args[1]}")
                            PowerupPatterns.instance.queuedMaxPattern = args[1].toInt()
                        }
                        else -> throw WrongUsageException("/poweruppatterns max [reset|2|3]")
                    }
                }
            }
            "ss" -> {
                if (args.size == 1) PowerupPatterns.instance.ssTimer = true
                else {
                    when (args[1]) {
                        "reset" -> {
                            addTranslationChat("zombiesaddon.commands.poweruppatterns.success.reset", "§5Shopping Spree")
                            PowerupPatterns.instance.queuedSsPattern = 0
                        }
                        "5", "6", "7" -> {
                            addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§5Shopping Spree", "§a${args[1]}")
                            PowerupPatterns.instance.queuedSsPattern = args[1].toInt()
                        }
                        else -> throw WrongUsageException("/poweruppatterns ss [reset|5|6|7]")
                    }
                }
            }
            "dg" -> PowerupPatterns.instance.dgTimer = true
            "car" -> PowerupPatterns.instance.carTimer = true
            "bg" -> PowerupPatterns.instance.bgTimer = true
            else -> throw WrongUsageException(getCommandUsage(null))
        }
    }

    override fun addTabCompletionOptions(sender: ICommandSender?, args: Array<String?>, pos: BlockPos?) =
        when (args.size) {
            1 -> getListOfStringsMatchingLastWord(args, "ins", "max", "ss", "dg", "car", "bg")
            2 -> if (args[0] == "ins" || args[0] == "max") getListOfStringsMatchingLastWord(args, "reset", "2", "3")
            else if (args[0] == "ss") getListOfStringsMatchingLastWord(args, "reset", "5", "6", "7")
            else null
            else -> null
        }
}