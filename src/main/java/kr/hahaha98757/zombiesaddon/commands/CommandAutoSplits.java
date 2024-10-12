package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.features.AutoSplits;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.List;

public class CommandAutoSplits extends CommandBase {

    @Override
    public String getCommandName() {
        return "autosplits";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return String.format("§eUsage: /%s <stop|run>", getCommandName());
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (sender != Minecraft.getMinecraft().thePlayer) return;

        if (args.length == 0) {
            Utils.addChat(getCommandUsage(null));
            return;
        }

        if (args[0].equalsIgnoreCase("stop")) {
            AutoSplits.stopTimer();
            Utils.addChat("§eAuto Splits: Stopping timer");
        } else if (args[0].equalsIgnoreCase("run")) {
            AutoSplits.runTimer();
            Utils.addChat("§eAuto Splits: Running timer");
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length != 1) return null;
        return getListOfStringsMatchingLastWord(args, "stop", "run");
    }
}