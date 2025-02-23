package kr.hahaha98757.zombiesaddon.commands;

import net.minecraftforge.client.ClientCommandHandler;

public class Commands {
    public static void registerAll() {
        ClientCommandHandler.instance.registerCommand(new CommandSLA());
        ClientCommandHandler.instance.registerCommand(new CommandZSV());
        ClientCommandHandler.instance.registerCommand(new CommandZSVLines());
        ClientCommandHandler.instance.registerCommand(new CommandPowerupPatterns());
        ClientCommandHandler.instance.registerCommand(new CommandWaveDelays());
        ClientCommandHandler.instance.registerCommand(new CommandAutoSplits());
        ClientCommandHandler.instance.registerCommand(new CommandZombiesAddon());
        ClientCommandHandler.instance.registerCommand(new CommandDE());
        ClientCommandHandler.instance.registerCommand(new CommandBB());
        ClientCommandHandler.instance.registerCommand(new CommandAA());
        ClientCommandHandler.instance.registerCommand(new CommandPR());
    }
}