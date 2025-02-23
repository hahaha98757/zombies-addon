package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public abstract class CommandMap extends CommandBase {
    public abstract String getMap();

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (sender != Minecraft.getMinecraft().thePlayer) return;
        Utils.sendChat("/play arcade_zombies_" + getMap());
    }
}

class CommandDE extends CommandMap {
    @Override
    public String getCommandName() {
        return "de";
    }

    @Override
    public String getMap() {
        return "dead_end";
    }
}

class CommandBB extends CommandMap {
    @Override
    public String getCommandName() {
        return "bb";
    }

    @Override
    public String getMap() {
        return "bad_blood";
    }
}

class CommandAA extends CommandMap {
    @Override
    public String getCommandName() {
        return "aa";
    }

    @Override
    public String getMap() {
        return "alien_arcadium";
    }
}

class CommandPR extends CommandMap {
    @Override
    public String getCommandName() {
        return "pr";
    }

    @Override
    public String getMap() {
        return "prison";
    }
}