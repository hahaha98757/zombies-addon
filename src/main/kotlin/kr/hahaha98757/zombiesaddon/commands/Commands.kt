package kr.hahaha98757.zombiesaddon.commands

import net.minecraftforge.client.ClientCommandHandler

object Commands {
    fun registerCommands() {
        ClientCommandHandler.instance.registerCommand(CommandDE())
        ClientCommandHandler.instance.registerCommand(CommandBB())
        ClientCommandHandler.instance.registerCommand(CommandAA())
        ClientCommandHandler.instance.registerCommand(CommandPR())
        ClientCommandHandler.instance.registerCommand(CommandPowerupPatterns())
        ClientCommandHandler.instance.registerCommand(CommandSLA())
        ClientCommandHandler.instance.registerCommand(CommandZombiesAddon())
        ClientCommandHandler.instance.registerCommand(CommandZSV())
        ClientCommandHandler.instance.registerCommand(CommandZSVLines())
        ClientCommandHandler.instance.registerCommand(CommandWaveDelays())
        ClientCommandHandler.instance.registerCommand(CommandAutoSplits())
        ClientCommandHandler.instance.registerCommand(CommandInternalTimer())
    }
}