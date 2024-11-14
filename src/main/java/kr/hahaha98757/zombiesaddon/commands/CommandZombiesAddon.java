package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.UpdateChecker;
import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.config.Hotkeys;
import kr.hahaha98757.zombiesaddon.hudposition.ConfigGui;
import kr.hahaha98757.zombiesaddon.hudposition.DelayedTask;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;

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
        return "/zombiesaddon <info|hud|checkUpdate|updateLog>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (sender != Minecraft.getMinecraft().thePlayer) return;

        if (args.length == 0) throw new WrongUsageException(getCommandUsage(null));

        switch (args[0]) {
            case "info":
                commandInfo(args);
                break;
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
        if (args.length == 1) return getListOfStringsMatchingLastWord(args, "info", "hud", "checkUpdate");
        else if (args.length == 2 && args[0].equals("info"))
            return getListOfStringsMatchingLastWord(args, "PV", "BlockAlarm", "NotLast", "AutoSplits", "WaveDelays", "ZSV", "SLA", "AutoRejoin", "PowerupPatterns", "LastWeapons", "TextMacro");
        return null;
    }

    private void commandInfo(String[] args) throws CommandException {
        if (args.length == 1) {
            Utils.addChat(Utils.LINE);
            Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info1", ZombiesAddon.NAME);
            Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info2", ZombiesAddon.VERSION);
            Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.zombiesaddon", new Object());
            //noinspection SpellCheckingInspection
            Utils.addChatWithURL("URL: ", "§9§nhttps://github.com/hahaha98757/zombies-addon", "https://github.com/hahaha98757/zombies-addon", "Open URL.", "");
            Utils.addChat(Utils.LINE);
            return;
        }

        switch (args[1]) {
            case "PV":
                Utils.addChat(Utils.LINE);
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general1", "Player Visibility");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general2", "thamid and Seosean");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.pv", new Object());
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.hotkeys", Keyboard.getKeyName(Hotkeys.togglePV.getKeyCode()));
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.config", new Object());
                Utils.addChat(Utils.LINE);
                break;
            case "BlockAlarm":
                Utils.addChat(Utils.LINE);
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general1", "Block Alarm");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general2", "syeyoung");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.blockAlarm", new Object());
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.hotkeys", Keyboard.getKeyName(Hotkeys.toggleBlockAlarm.getKeyCode()));
                Utils.addChat(Utils.LINE);
                break;
            case "NotLast":
                Utils.addChat(Utils.LINE);
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general1", "Not Last");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general2", "thamid");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.notLast", new Object());
                Utils.addChat(Utils.LINE);
                break;
            case "AutoSplits":
                Utils.addChat(Utils.LINE);
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general1", "Auto Splits");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general2", "thamid");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.autoSplits", new Object());
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.commands", "/autosplits");
                Utils.addChat(Utils.LINE);
                break;
            case "WaveDelays":
                Utils.addChat(Utils.LINE);
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general1", "Wave Delays");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general2", "hahaha98757 and Stachelbeere1248");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.waveDelays", new Object());
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.hotkeys", Keyboard.getKeyName(Hotkeys.toggleRLMode.getKeyCode()));
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.commands", "/wavedelays");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.config", new Object());
                Utils.addChat(Utils.LINE);
                break;
            case "ZSV":
                Utils.addChat(Utils.LINE);
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general1", "Zombies Strat Viewer");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general2", "syeyoung");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.zsv", new Object());
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.hotkeys", Keyboard.getKeyName(Hotkeys.zsvScrollUp.getKeyCode()) + " and " + Keyboard.getKeyName(Hotkeys.zsvScrollDown.getKeyCode()));
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.commands", "/zsv and /zsvlines");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.config", new Object());
                Utils.addChat(Utils.LINE);
                break;
            case "SLA":
                Utils.addChat(Utils.LINE);
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general1", "Spawn Limit Action");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general2", "Stachelbeere1248");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.sla", new Object());
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.commands", "/sla");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.config", new Object());
                Utils.addChat(Utils.LINE);
                break;
            case "AutoRejoin":
                Utils.addChat(Utils.LINE);
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general1", "Auto Rejoin");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general2", "thamid");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.autoRejoin", new Object());
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.hotkeys", Keyboard.getKeyName(Hotkeys.toggleAutoRejoin.getKeyCode()));
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.config", new Object());
                Utils.addChat(Utils.LINE);
                break;
            case "PowerupPatterns":
                Utils.addChat(Utils.LINE);
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general1", "Powerup Patterns");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general2", "hahaha98757");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.powerupPatterns", new Object());
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.commands", "/poweruppatterns or /pow");
                Utils.addChat(Utils.LINE);
                break;
            case "LastWeapons":
                Utils.addChat(Utils.LINE);
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general1", "Last Weapons");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general2", "hahaha98757");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.lastWeapons", new Object());
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.config", new Object());
                Utils.addChat(Utils.LINE);
                break;
            case "TextMacro":
                Utils.addChat(Utils.LINE);
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general1", "Text Macro");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.general2", "hahaha98757");
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.textMacro", new Object());
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.hotkeys", Keyboard.getKeyName(Hotkeys.textMacro.getKeyCode()));
                Utils.addTranslationChat("zombiesaddon.commands.zombiesaddon.info.config", new Object());
                Utils.addChat(Utils.LINE);
                break;
            default:
                throw new WrongUsageException("/zombiesaddon info [Feature Name]");
        }
    }
}