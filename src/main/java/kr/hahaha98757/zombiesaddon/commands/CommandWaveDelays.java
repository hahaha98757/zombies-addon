package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.enums.Difficulty;
import kr.hahaha98757.zombiesaddon.features.WaveDelays;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
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
        return "/wavedelays difficult <normal|hard|rip>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (sender != Minecraft.getMinecraft().thePlayer) return;

        if (args.length < 2) throw new WrongUsageException(getCommandUsage(null));

        switch (args[1]) {
            case "normal":
                WaveDelays.setDifficulty(Difficulty.NORMAL);
                Utils.addTranslationChat("zombiesaddon.commands.wavedelays.success", "§aNormal");
                break;
            case "hard":
                WaveDelays.setDifficulty(Difficulty.HARD);
                Utils.addTranslationChat("zombiesaddon.commands.wavedelays.success", "§cHard");
                break;
            case "rip":
                WaveDelays.setDifficulty(Difficulty.RIP);
                Utils.addTranslationChat("zombiesaddon.commands.wavedelays.success", "§4RIP");
                break;
            default:
                throw new WrongUsageException(getCommandUsage(null));
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) return getListOfStringsMatchingLastWord(args, "difficult");
        else if (args.length == 2) return getListOfStringsMatchingLastWord(args, "normal", "hard", "rip");
        return null;
    }
}