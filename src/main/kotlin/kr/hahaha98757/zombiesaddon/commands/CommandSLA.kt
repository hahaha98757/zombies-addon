package kr.hahaha98757.zombiesaddon.commands

import kr.hahaha98757.zombiesaddon.enums.Map
import kr.hahaha98757.zombiesaddon.modules.SLA
import net.minecraft.command.ICommandSender
import net.minecraft.command.NumberInvalidException
import net.minecraft.command.WrongUsageException
import net.minecraft.util.BlockPos

class CommandSLA: CustomCommandBase() {
    override fun getCommandName() = "sla"
    override fun getCommandUsage(sender: ICommandSender?) = "/sla <de|bb|aa|off|quick|custom>"
    override fun runCommand(sender: ICommandSender, args: Array<String>) {
        if (args.isEmpty()) throw WrongUsageException(getCommandUsage(null))

        when (args[0]) {
            "off" -> SLA.off()
            "de" -> SLA.on(Map.DEAD_END)
            "bb" -> SLA.on(Map.BAD_BLOOD)
            "aa" -> SLA.on(Map.ALIEN_ARCADIUM)
            "quick" -> {
                if (args.size == 1) throw WrongUsageException("/sla quick <mogi_a|ghxula|ghxula-garden>")
                when (args[1]) {
                    "mogi_a" -> {
                        SLA.on(Map.BAD_BLOOD)
                        SLA.sla?.rotate(3)
                        SLA.sla?.setOffset(-3, 35, -9)
                    }
                    "ghxula" -> {
                        SLA.on(Map.DEAD_END)
                        SLA.sla?.rotate(1)
                        SLA.sla?.setOffset(27, 35, 5)
                    }
                    "ghxula-garden" -> {
                        SLA.on(Map.DEAD_END)
                        SLA.sla?.rotate(1)
                        SLA.sla?.setOffset(13, 53, -8)
                    }
                    else -> throw WrongUsageException("/sla quick <mogi_a|ghxula|ghxula-garden>")
                }
            }
            "custom" -> {
                if (args.size == 1) throw WrongUsageException("/sla custom <offset|rotate|mirror>")
                when (args[1]) {
                    "offset" -> {
                        if (args.size == 2) SLA.sla?.resetOffset()
                        else if (args.size != 5) throw WrongUsageException("/sla custom offset <x> <y> <z>")
                        else {
                            val blockPos = parseBlockPos(sender ,args, 2, false)
                            SLA.sla?.setOffset(blockPos.x, blockPos.y, blockPos.z)
                        }
                    }
                    "rotate" -> {
                        if (args.size == 2) SLA.sla?.resetRotate()
                        else try {
                            SLA.sla?.rotate(args[2].toInt())
                        } catch (_: NumberFormatException) {
                            throw NumberInvalidException("commands.generic.num.invalid", args[2])
                        }
                    }
                    "mirror" -> {
                        if (args.size == 2) SLA.sla?.resetMirroring()
                        else when (args[2]) {
                            "x" -> SLA.sla?.mirrorX()
                            "z" -> SLA.sla?.mirrorZ()
                            else -> throw WrongUsageException("/sla custom mirror <x|z>")
                        }
                    }
                    else -> throw WrongUsageException("/sla custom <offset|rotate|mirror>")
                }
            }
            else -> throw WrongUsageException(getCommandUsage(null))
        }
    }
    override fun addTabCompletionOptions(sender: ICommandSender?, args: Array<String?>, pos: BlockPos?): List<String>? {
        if (args.isEmpty()) return null
        if (args.size == 1) return getListOfStringsMatchingLastWord(args, "de", "bb", "aa", "off", "quick", "custom")
        return when (args[0]) {
            "quick" -> getListOfStringsMatchingLastWord(args, "mogi_a", "ghxula", "ghxula-garden")
            "custom" -> {
                if (args.size == 2) getListOfStringsMatchingLastWord(args, "offset", "rotate", "mirror")
                else when (args[1]) {
                    "rotate" -> getListOfStringsMatchingLastWord(args, "0", "1", "2", "3")
                    "mirror" -> getListOfStringsMatchingLastWord(args, "x", "z")
                    else -> null
                }
            }
            else -> null
        }
    }
}