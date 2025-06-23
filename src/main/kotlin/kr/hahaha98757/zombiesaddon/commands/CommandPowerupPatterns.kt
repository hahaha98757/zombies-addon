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
                            PowerupPatterns.instance.rawInsPattern = 0
                        }
                        "2", "3" -> {
                            addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§cInsta Kill", "§a${args[1]}")
                            PowerupPatterns.instance.rawInsPattern = args[1].toInt()
                        }
                        else -> throw WrongUsageException("/poweruppatterns in <reset|2|3>")
                    }
                }
            }
            "max" -> {
                if (args.size == 1) PowerupPatterns.instance.maxTimer = true
                else {
                    when (args[1]) {
                        "reset" -> {
                            addTranslationChat("zombiesaddon.commands.poweruppatterns.success.reset", "§9Max Ammo")
                            PowerupPatterns.instance.rawMaxPattern = 0
                        }
                        "2", "3" -> {
                            addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§9Max Ammo", "§a${args[1]}")
                            PowerupPatterns.instance.rawMaxPattern = args[1].toInt()
                        }
                        else -> throw WrongUsageException("/poweruppatterns max <reset|2|3>")
                    }
                }
            }
            "ss" -> {
                if (args.size == 1) PowerupPatterns.instance.ssTimer = true
                else {
                    when (args[1]) {
                        "reset" -> {
                            addTranslationChat("zombiesaddon.commands.poweruppatterns.success.reset", "§5Shopping Spree")
                            PowerupPatterns.instance.rawSSPattern = 0
                        }
                        "5", "6", "7" -> {
                            addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§5Shopping Spree", "§a${args[1]}")
                            PowerupPatterns.instance.rawSSPattern = args[1].toInt()
                        }
                        else -> throw WrongUsageException("/poweruppatterns ss <reset|5|6|7>")
                    }
                }
            }
            "dg" -> PowerupPatterns.instance.dgTimer = true
            "car" -> PowerupPatterns.instance.carTimer = true
            "bg" -> PowerupPatterns.instance.bgTimer = true
            else -> throw WrongUsageException(getCommandUsage(null))
        }
    }

    override fun addTabCompletionOptions(sender: ICommandSender?, args: Array<String?>, pos: BlockPos?): List<String>? {
        return if (args.size == 1) getListOfStringsMatchingLastWord(args, "ins", "max", "ss", "dg", "car", "bg")
        else if (args[0] == "ins" || args[0] == "max") getListOfStringsMatchingLastWord(args, "reset", "2", "3")
        else if (args[0] == "ss") getListOfStringsMatchingLastWord(args, "reset", "5", "6", "7")
        else return null
    }
}