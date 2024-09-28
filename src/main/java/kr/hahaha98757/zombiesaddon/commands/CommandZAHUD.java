//Code in Show Spawn Time by Seosean

package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.hudposition.ConfigGui;
import kr.hahaha98757.zombiesaddon.hudposition.DelayedTask;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandZAHUD extends CommandBase {

    @Override
    public String getCommandName() {
        return "ZAHUD";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "§cUsage: /ZAHUD";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (sender != Minecraft.getMinecraft().thePlayer) return;

        new DelayedTask(() -> Minecraft.getMinecraft().displayGuiScreen(new ConfigGui()), 2);
    }
}