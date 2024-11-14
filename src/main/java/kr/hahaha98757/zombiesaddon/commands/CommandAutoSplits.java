package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.features.AutoSplits;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
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
        return "/autosplits <stop|run>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (sender != Minecraft.getMinecraft().thePlayer) return;

        if (args.length == 0) throw new WrongUsageException(getCommandUsage(null));

        switch (args[0]) {
            case "stop":
                AutoSplits.stopTimer();
                Utils.addTranslationChat("zombiesaddon.commands.autosplits.success.stop", new Object());
                break;
            case "run":
                AutoSplits.runTimer();
                Utils.addTranslationChat("zombiesaddon.commands.autosplits.success.run", new Object());
                break;
            default:
                throw new WrongUsageException(getCommandUsage(null));
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length != 1) return null;
        return getListOfStringsMatchingLastWord(args, "stop", "run");
    }
}