package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.listeners.PowerupPatternsListener;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.Collections;
import java.util.List;

public class CommandPowerupPatterns extends CommandBase {

	@Override
	public String getCommandName() {
		return "powerupPatterns";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "\u00A7cUsage: /powerupPatterns <insta|max|ss> [number|on|off]";
	}

	@Override
	public List<String> getCommandAliases() {
		return Collections.singletonList("pow");
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (sender != Minecraft.getMinecraft().thePlayer) return;

		if (args.length == 0) {
			Utils.addChat(getCommandUsage(null));
			return;
		}
		switch (args[0]) {
			case "insta":
				if (args.length == 1) {
					Utils.addChat("\u00A7ePowerup Patterns: Reset insta pattern");
					PowerupPatternsListener.instaPattern = 0;
				} else {
					switch (args[1]) {
						case "2":
						case "3":
							Utils.addChat(String.format("\u00A7ePowerup Patterns: Set insta pattern to \u00A7a%s", args[1]));
							PowerupPatternsListener.instaPattern = Byte.parseByte(args[1]);
							break;
						case "on":
							Utils.addChat("\u00A7ePowerup Patterns: Set insta pattern to \u00A7aon");
							PowerupPatternsListener.useInstaPattern = true;
							break;
						case "off":
							Utils.addChat("\u00A7ePowerup Patterns: Set insta pattern to \u00A7coff");
							PowerupPatternsListener.useInstaPattern = false;
							break;
						default:
							Utils.addChat("\u00A7cUsage: /powerupPatterns insta [2|3|on|off]");
							break;
					}
				}
				break;
			case "max":
				if (args.length == 1) {
					Utils.addChat("\u00A7ePowerup Patterns: Reset max pattern");
					PowerupPatternsListener.maxPattern = 0;
				} else {
					switch (args[1]) {
						case "2":
						case "3":
							Utils.addChat(String.format("\u00A7ePowerup Patterns: Set max pattern to \u00A7a%s", args[1]));
							PowerupPatternsListener.maxPattern = Byte.parseByte(args[1]);
							break;
						case "on":
							Utils.addChat("\u00A7ePowerup Patterns: Set max pattern to \u00A7aon");
							PowerupPatternsListener.useMaxPattern = true;
							break;
						case "off":
							Utils.addChat("\u00A7ePowerup Patterns: Set max pattern to \u00A7coff");
							PowerupPatternsListener.useMaxPattern = false;
							break;
						default:
							Utils.addChat("\u00A7cUsage: /powerupPatterns max [2|3|on|off]");
							break;
					}
				}
				break;
			case "ss":
				if (args.length == 1) {
					Utils.addChat("\u00A7ePowerup Patterns: Reset ss pattern");
					PowerupPatternsListener.ssPattern = 0;
				} else {
					switch (args[1]) {
						case "5":
						case "6":
						case "7":
							Utils.addChat(String.format("\u00A7ePowerup Patterns: Set ss pattern to \u00A7a%s", args[1]));
							PowerupPatternsListener.ssPattern = Byte.parseByte(args[1]);
							break;
						case "on":
							Utils.addChat("\u00A7ePowerup Patterns: Set ss pattern to \u00A7aon");
							PowerupPatternsListener.useSSPattern = true;
							break;
						case "off":
							Utils.addChat("\u00A7ePowerup Patterns: Set ss pattern to \u00A7coff");
							PowerupPatternsListener.useSSPattern = false;
							break;
						default:
							Utils.addChat("\u00A7cUsage: /powerupPatterns ss [5|6|7|on|off]");
							break;
					}
				}
				break;
			default:
				Utils.addChat(getCommandUsage(null));
		}
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1)
			return getListOfStringsMatchingLastWord(args, "insta", "max", "ss");
        else if (args[0].equals("insta") || args[0].equals("max"))
            return getListOfStringsMatchingLastWord(args, "2", "3", "on", "off");
        else if (args[0].equals("ss"))
			return getListOfStringsMatchingLastWord(args, "5", "6", "7", "on", "off");
		return null;
	}

}