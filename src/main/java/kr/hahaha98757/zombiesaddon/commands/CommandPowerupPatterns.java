package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.features.ManualTimer;
import kr.hahaha98757.zombiesaddon.features.PowerupPatterns;
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
		return "§cUsage: /powerupPatterns <insta|max|ss|dg|carpenter|bg> [reset|number|on|off]";
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
					Utils.addChat("§ePowerup Patterns: You have run Manual Timer of §cInsta Kill §ewith a command");
					PowerupPatterns.instaTimer = true;
					ManualTimer.resetTimer((byte) 1);
				} else {
					switch (args[1]) {
						case "reset":
							Utils.addChat("§ePowerup Patterns: Reset §cInsta Kill §epattern");
							PowerupPatterns.instaPattern = 0;
							break;
						case "2":
						case "3":
							Utils.addChat(String.format("§ePowerup Patterns: Set insta pattern to §a%s", args[1]));
							PowerupPatterns.instaPattern = Byte.parseByte(args[1]);
							break;
						case "on":
							Utils.addChat("§ePowerup Patterns: Set §cInsta Kill §epattern to §aon");
							PowerupPatterns.useInstaPattern = true;
							break;
						case "off":
							Utils.addChat("§ePowerup Patterns: Set §cInsta Kill §epattern to §coff");
							PowerupPatterns.useInstaPattern = false;
							break;
						default:
							Utils.addChat("§cUsage: /powerupPatterns insta [reset|2|3|on|off]");
							break;
					}
				}
				break;
			case "max":
				if (args.length == 1) {
					Utils.addChat("§ePowerup Patterns: You have run Manual Timer of §9Max Ammo §ewith a command");
					PowerupPatterns.maxTimer = true;
					ManualTimer.resetTimer((byte) 2);
				} else {
					switch (args[1]) {
						case "reset":
							Utils.addChat("§ePowerup Patterns: Reset §9Max Ammo §epattern");
							PowerupPatterns.maxPattern = 0;
							break;
						case "2":
						case "3":
							Utils.addChat(String.format("§ePowerup Patterns: Set §9Max Ammo §epattern to §a%s", args[1]));
							PowerupPatterns.maxPattern = Byte.parseByte(args[1]);
							break;
						case "on":
							Utils.addChat("§ePowerup Patterns: Set §9Max Ammo §epattern to §aon");
							PowerupPatterns.useMaxPattern = true;
							break;
						case "off":
							Utils.addChat("§ePowerup Patterns: Set §9Max Ammo §epattern to §coff");
							PowerupPatterns.useMaxPattern = false;
							break;
						default:
							Utils.addChat("§cUsage: /powerupPatterns max [reset|2|3|on|off]");
							break;
					}
				}
				break;
			case "ss":
				if (args.length == 1) {
					Utils.addChat("§ePowerup Patterns: You have run Manual Timer of §5Shopping Spree §ewith a command");
					PowerupPatterns.ssTimer = true;
					ManualTimer.resetTimer((byte) 3);
				} else {
					switch (args[1]) {
						case "reset":
							Utils.addChat("§ePowerup Patterns: Reset §5Shopping Spree §epattern");
							PowerupPatterns.ssPattern = 0;
							break;
						case "5":
						case "6":
						case "7":
							Utils.addChat(String.format("§ePowerup Patterns: Set §5Shopping Spree §epattern to §a%s", args[1]));
							PowerupPatterns.ssPattern = Byte.parseByte(args[1]);
							break;
						case "on":
							Utils.addChat("§ePowerup Patterns: Set §5Shopping Spree §epattern to §aon");
							PowerupPatterns.useSSPattern = true;
							break;
						case "off":
							Utils.addChat("§ePowerup Patterns: Set §5Shopping Spree §epattern to §coff");
							PowerupPatterns.useSSPattern = false;
							break;
						default:
							Utils.addChat("§cUsage: /powerupPatterns ss [reset|5|6|7|on|off]");
							break;
					}
				}
				break;
			case "dg":
				Utils.addChat("§ePowerup Patterns: You have run Manual Timer of §6Double Gold §ewith a command");
				PowerupPatterns.dgTimer = true;
				ManualTimer.resetTimer((byte) 4);
				break;
			case "carpenter":
				Utils.addChat("§ePowerup Patterns: You have run Manual Timer of §9Carpenter §ewith a command");
				PowerupPatterns.carpenterTimer = true;
				ManualTimer.resetTimer((byte) 5);
				break;
			case "bg":
				Utils.addChat("§ePowerup Patterns: You have run Manual Timer of §6Bonus Gold §ewith a command");
				PowerupPatterns.bgTimer = true;
				ManualTimer.resetTimer((byte) 6);
				break;
			default:
				Utils.addChat(getCommandUsage(null));
		}
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1)
			return getListOfStringsMatchingLastWord(args, "insta", "max", "ss", "dg", "carpenter", "bg");
        else if (args[0].equals("insta") || args[0].equals("max"))
            return getListOfStringsMatchingLastWord(args, "reset", "2", "3", "on", "off");
        else if (args[0].equals("ss"))
			return getListOfStringsMatchingLastWord(args, "reset", "5", "6", "7", "on", "off");
		return null;
	}

}