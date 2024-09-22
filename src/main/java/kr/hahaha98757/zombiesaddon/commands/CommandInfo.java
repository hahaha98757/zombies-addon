package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.config.Hotkeys;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class CommandInfo extends CommandBase {

	@Override
	public String getCommandName() {
		return "info";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "§cUsage: /info [mod name]";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (sender != Minecraft.getMinecraft().thePlayer) return;

		if (args.length == 0) {
            //noinspection SpellCheckingInspection
            Utils.addChatWithURL(Utils.LINE + "\nMod name: " + ZombiesAddon.NAME + "\nVersion: " + ZombiesAddon.VERSION + "\nInfo: The mod for Hypixel Zombies.\nURL: ", "§9§nhttps://github.com/hahaha98757/zombies-addon", "https://github.com/hahaha98757/zombies-addon", "Open URL.", "\n" + Utils.LINE);
			return;
		}

		String text;

		switch (args[0]) {
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
				text = "Name: Auto Splits\nCode by: thamid\nInfo: Run a timer at start of the round.";
				break;
			case "WaveDelays":
				text = "Name: Wave Delays\nCode by: hahaha98757 and Stachelbeere1248\nInfo: Display the wave delays.\nConfig: Used";
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
				Utils.addChat(getCommandUsage(null));
				return;
		}
		Utils.addChatLine(text);
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length != 1) return null;
		return getListOfStringsMatchingLastWord(args, "PV", "BlockAlarm", "NotLast", "AutoSplits", "WaveDelays", "ZSV", "SLA", "AutoRejoin", "PowerupPatterns", "TextMacro");

	}
}