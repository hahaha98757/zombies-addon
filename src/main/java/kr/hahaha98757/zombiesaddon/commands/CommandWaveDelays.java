package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.enums.Difficulty;
import kr.hahaha98757.zombiesaddon.features.WaveDelays;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.List;

public class CommandWaveDelays extends CommandBase {

    @Override
    public String getCommandName() {
        return "wavedelays";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return String.format("§cUsage: /%s difficult <normal|hard|rip>", getCommandName());
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (sender != Minecraft.getMinecraft().thePlayer) return;

        if (args.length < 2) {
            Utils.addChat(getCommandUsage(null));
            return;
        }

        if (args[1].equalsIgnoreCase("normal") || args[1].equals("0")) WaveDelays.setDifficulty(Difficulty.NORMAL);
        else if (args[1].equalsIgnoreCase("hard") || args[1].equals("1")) WaveDelays.setDifficulty(Difficulty.HARD);
        else if (args[1].equalsIgnoreCase("rip") || args[1].equals("2")) WaveDelays.setDifficulty(Difficulty.RIP);
        else Utils.addChat(getCommandUsage(null));
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) return getListOfStringsMatchingLastWord(args, "difficult");
        else if (args.length == 2) return getListOfStringsMatchingLastWord(args, "normal", "hard", "rip");
        return null;
    }
}