package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.Keybinds;
import kr.hahaha98757.zombiesaddon.ZombiesAddon;
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
			Utils.addChatWithURL(Utils.LINE + "\nMod name: " + ZombiesAddon.NAME + "\nVersion: " + ZombiesAddon.VERSION + "\nInfo: The mod for Hypixel Zombies.\nURL: ", "§9§nhttps://github.com/hahaha98757/zombies-addon", "https://github.com/hahaha98757/zombies-addon", "Open URL.", "\n" + Utils.LINE);
			return;
		}

		String text;

		switch (args[0]) {
			case "Cornering":
				text = "Name: Cornering\nCode by: syeyoung and thamid\nInfo: Hides the players nearby.\nKeybinds: " + Keyboard.getKeyName(Keybinds.toggleCornering.getKeyCode()) + "\nConfig: Used";
				break;
			case "BlockAlarm":
				text = "Name: Block Alarm\nCode by: syeyoung\nInfo: Show \"BLOCK\" on display when you survived alone.\nKeybinds: " + Keyboard.getKeyName(Keybinds.toggleBlockAlarm.getKeyCode());
				break;
			case "NotLast":
				text = "Name: Not Last\nCode by: thamid\nInfo: Shows the player who killed the last.";
				break;
			case "AutoSplits":
				text = "Name: Auto Splits\nCode by: thamid\nInfo: Run timer at start of round.";
				break;
			case "WaveDelays":
				text = "Name: Wave Delays\nCode by: hahaha98757\nInfo: Displays the wave delays.\nConfig: Used";
				break;
			case "ZSV":
				text = "Name: ZSV(Zombies Strat Viewer)\nCode by: syeyoung\nInfo: Displays the text in https://pastebin.com/ on the in-game screen.\nCommands: /ZSV and /ZSVLines";
				break;
			case "SLA":
				text = "Name: SLA(Spawn Limit Action)\nCode by: Stachelbeere1248\nInfo: Display number and name of windows where zombies can spawn.\nCommands: /SLA\nConfig: Used";
				break;
			case "AutoRejoin":
				text = "Name: Auto Rejoin\nCode by: thamid\nInfo: Automatically rejoin Zombies.\nKeybinds: " + Keyboard.getKeyName(Keybinds.toggleAutoRejoin.getKeyCode()) + "\nConfig: Used";
				break;
			case "PowerupPatterns":
				text = "Name: Powerup Alarm\nCode by: hahaha98757\nInfo: Show power-up patterns.\nCommands: /powerupPatterns, /pow";
				break;
			case "LastWeapons":
				text = "Name: Last Weapons\nCode by: hahaha98757\nInfo: Show your weapons at Game Over.";
				break;
			case "TextMacro":
				text = "Name: Text Macro\nCode by: hahaha98757\nInfo: Send the text on chat when press hotkey of Text Macro.";
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
		return getListOfStringsMatchingLastWord(args, "Cornering", "BlockAlarm", "NotLast", "AutoSplits", "WaveDelays", "ZSV", "SLA", "AutoRejoin", "PowerupPatterns", "TextMacro");

	}
}