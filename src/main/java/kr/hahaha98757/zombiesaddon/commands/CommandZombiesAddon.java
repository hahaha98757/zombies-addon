package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.UpdateChecker;
import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.config.Hotkeys;
import kr.hahaha98757.zombiesaddon.hudposition.ConfigGui;
import kr.hahaha98757.zombiesaddon.hudposition.DelayedTask;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
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
        return String.format("§cUsage: /%s <info|hud|license|checkUpdate|updateLog>", getCommandName());
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (sender != Minecraft.getMinecraft().thePlayer) return;

        if (args.length == 0) {
            Utils.addChat(getCommandUsage(null));
            return;
        }

        switch (args[0]) {
            case "info":
                commandInfo(args);
                break;
            case "hud":
                new DelayedTask(() -> Minecraft.getMinecraft().displayGuiScreen(new ConfigGui()), 2);
                break;
            case "license":
                commandLicense(args);
                break;
            case "checkUpdate":
                UpdateChecker.checkUpdate();
                Utils.addChat("§eChecking update...");
                break;
            case "updateLog":
                Utils.addChatWithURL(Utils.LINE + "\n", "§9§nClick here to view log.", "https://github.com/hahaha98757/zombies-addon?tab=readme-ov-file#update-log", "Open URL.", "\n" + Utils.LINE);
                break;
            default:
                Utils.addChat(getCommandUsage(null));
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) return getListOfStringsMatchingLastWord(args, "info", "hud", "license", "checkUpdate");
        else if (args.length == 2 && args[0].equals("info"))
            return getListOfStringsMatchingLastWord(args, "PV", "BlockAlarm", "NotLast", "AutoSplits", "WaveDelays", "ZSV", "SLA", "AutoRejoin", "PowerupPatterns", "LastWeapons", "TextMacro");
        return null;
    }

    private void commandInfo(String[] args) {
        if (args.length == 1) {
            //noinspection SpellCheckingInspection
            Utils.addChatWithURL(Utils.LINE + "\nMod name: " + ZombiesAddon.NAME + "\nVersion: " + ZombiesAddon.VERSION + "\nInfo: The mod for Hypixel Zombies.\nURL: ", "§9§nhttps://github.com/hahaha98757/zombies-addon", "https://github.com/hahaha98757/zombies-addon", "Open URL.", "\n" + Utils.LINE);
            return;
        }

        String text;

        switch (args[1]) {
            case "PV":
                text = "Name: Player Visibility\nCode by: thamid and Seosean\nInfo: Hide nearby players from your view.\nHotkeys: " + Keyboard.getKeyName(Hotkeys.togglePV.getKeyCode()) + "\nConfig: Used";
                break;
            case "BlockAlarm":
                text = "Name: Block Alarm\nCode by: syeyoung\nInfo: Display \"BLOCK\" on the screen when you survived alone and there is a player you can revive.\nHotkeys: " + Keyboard.getKeyName(Hotkeys.toggleBlockAlarm.getKeyCode());
                break;
            case "NotLast":
                text = "Name: Not Last\nCode by: thamid\nInfo: Display the player who killed the last one.";
                break;
            case "AutoSplits":
                text = "Name: Auto Splits\nCode by: thamid\nInfo: Run a timer at start of the round.\nCommand: /autoSplits";
                break;
            case "WaveDelays":
                text = "Name: Wave Delays\nCode by: hahaha98757 and Stachelbeere1248\nInfo: Display the wave delays.\nCommand: /waveDelays\nConfig: Used";
                break;
            case "ZSV":
                text = "Name: Zombies Strat Viewer\nCode by: syeyoung\nInfo: Display the text in https://pastebin.com/ on the screen.\nCommands: /ZSV and /ZSVLines\nHotkeys: " + Keyboard.getKeyName(Hotkeys.zsvScrollUp.getKeyCode()) + " and " + Keyboard.getKeyName(Hotkeys.zsvScrollDown.getKeyCode());
                break;
            case "SLA":
                text = "Name: Spawn Limit Action\nCode by: Stachelbeere1248\nInfo: Display the number and names of windows where zombies can spawn.\nCommands: /SLA\nConfig: Used";
                break;
            case "AutoRejoin":
                text = "Name: Auto Rejoin\nCode by: thamid\nInfo: Automatically rejoin Zombies when start of the round.\nHotkeys: " + Keyboard.getKeyName(Hotkeys.toggleAutoRejoin.getKeyCode()) + "\nConfig: Used";
                break;
            case "PowerupPatterns":
                text = "Name: Powerup Patterns\nCode by: hahaha98757\nInfo: Display patterns of power-ups.\nCommands: /powerupPatterns or /pow";
                break;
            case "LastWeapons":
                text = "Name: Last Weapons\nCode by: hahaha98757\nInfo: Display your weapons when You Win.";
                break;
            case "TextMacro":
                text = "Name: Text Macro\nCode by: hahaha98757\nInfo: Send the text on chat when press the hotkey.\nHotkeys: " + Keyboard.getKeyName(Hotkeys.textMacro.getKeyCode()) + "\nConfig: Used";
                break;
            default:
                Utils.addChat(String.format("§cUsage: /%s info [Feature Name]", getCommandName()));
                return;
        }
        Utils.addChatLine(text);
    }

    private void commandLicense(String[] args) {
        Utils.addChatWithURL(Utils.LINE + "\nZombies Addon: MIT(Copyright (c) 2024 hahaha98757)\nZombies AutoSplits: MIT(Copyright (c) 2022 tahmid-23)\nZombies Utils: MIT(Copyright (c) 2023 Stachelbeere1248)\nShow Spawn Time: MIT\nZombies Cornering Mod: MIT(Copyright (c) 2024 syeyoung)\nZombies Strat Viewer: MIT(Copyright (c) 2024 syeyoung)\nZombies Hologrambug Fixer, Zombies Cornering Mod, and Zombies Strat Viewer was originally licensed under the LGPL, but the author, syeyoung, granted permission to license it under the MIT license.\nShow Spawn Time has MIT license in the LICENSE.txt file inside ShowSpawnTime-1.15.0.jar.\n", "§9§nClick here to view more detail.", "https://github.com/hahaha98757/zombies-addon?tab=readme-ov-file#credits", "Open URL.", "\n" + Utils.LINE);
    }
}