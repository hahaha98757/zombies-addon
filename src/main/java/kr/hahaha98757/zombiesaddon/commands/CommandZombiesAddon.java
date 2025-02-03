package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.UpdateChecker;
import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.hudposition.ConfigGui;
import kr.hahaha98757.zombiesaddon.hudposition.DelayedTask;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.BlockPos;

import java.util.List;

public class CommandZombiesAddon extends CommandBase {

    @Override
    public String getCommandName() {
        return ZombiesAddon.MODID;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/zombiesaddon <hud|checkUpdate|updateLog>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (sender != Minecraft.getMinecraft().thePlayer) return;

        if (args.length == 0) throw new WrongUsageException(getCommandUsage(null));

        switch (args[0]) {
            case "hud":
                new DelayedTask(() -> Minecraft.getMinecraft().displayGuiScreen(new ConfigGui()), 2);
                break;
            case "checkUpdate":
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.checkingUpdate", new Object());
                UpdateChecker.checkUpdate();
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.checkedUpdate", new Object());
                break;
            case "updateLog":
                Utils.addChatWithURL(Utils.LINE + "\n", "§9§nClick here to view log.", "https://github.com/hahaha98757/zombies-addon?tab=readme-ov-file#update-log", "Open URL.", "\n" + Utils.LINE);
                break;
            default:
                throw new WrongUsageException(getCommandUsage(null));
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) return getListOfStringsMatchingLastWord(args, "hud", "checkUpdate", "updateLog");
        return null;
    }
}