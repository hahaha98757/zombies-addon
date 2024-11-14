package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.features.ManualTimer;
import kr.hahaha98757.zombiesaddon.features.PowerupPatterns;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.BlockPos;

import java.util.Collections;
import java.util.List;

public class CommandPowerupPatterns extends CommandBase {

	@Override
	public String getCommandName() {
		return "poweruppatterns";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "zombiesaddon.commands.poweruppatterns.usage";
	}

	@Override
	public List<String> getCommandAliases() {
		return Collections.singletonList("pow");
	}

    @Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (sender != Minecraft.getMinecraft().thePlayer) return;

		if (args.length == 0) throw new WrongUsageException(getCommandUsage(null));
		switch (args[0]) {
			case "insta":
				if (args.length == 1) {
					Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.timer", "§cInsta Kill");
					PowerupPatterns.instaTimer = true;
					ManualTimer.resetTimer((byte) 1);
				} else {
					switch (args[1]) {
						case "reset":
							Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.reset", "§cInsta Kill");
							PowerupPatterns.instaPattern = 0;
							break;
						case "2":
						case "3":
							Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§cInsta Kill", "§a" + args[1]);
							PowerupPatterns.instaPattern = Byte.parseByte(args[1]);
							break;
						case "on":
							Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§cInsta Kill", "§a" + args[1]);
							PowerupPatterns.useInstaPattern = true;
							break;
						case "off":
							Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§cInsta Kill", "§c" + args[1]);
							PowerupPatterns.useInstaPattern = false;
							break;
						default:
							throw new WrongUsageException("/poweruppatterns insta [reset|2|3|on|off]");
					}
				}
				break;
			case "max":
				if (args.length == 1) {
					Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.timer", "§9Max Ammo");
					PowerupPatterns.maxTimer = true;
					ManualTimer.resetTimer((byte) 2);
				} else {
					switch (args[1]) {
						case "reset":
							Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.reset", "§9Max Ammo");
							PowerupPatterns.maxPattern = 0;
							break;
						case "2":
						case "3":
							Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§9Max Ammo", "§a" + args[1]);
							PowerupPatterns.maxPattern = Byte.parseByte(args[1]);
							break;
						case "on":
							Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§9Max Ammo", "§a" + args[1]);
							PowerupPatterns.useMaxPattern = true;
							break;
						case "off":
							Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§9Max Ammo", "§c" + args[1]);
							PowerupPatterns.useMaxPattern = false;
							break;
						default:
							throw new WrongUsageException("/poweruppatterns max [reset|2|3|on|off]");
					}
				}
				break;
			case "ss":
				if (args.length == 1) {
					Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.timer", "§5Shopping Spree");
					PowerupPatterns.ssTimer = true;
					ManualTimer.resetTimer((byte) 3);
				} else {
					switch (args[1]) {
						case "reset":
							Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.reset", "§5Shopping Spree");
							PowerupPatterns.ssPattern = 0;
							break;
						case "5":
						case "6":
						case "7":
							Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§5Shopping Spree", "§a" + args[1]);
							PowerupPatterns.ssPattern = Byte.parseByte(args[1]);
							break;
						case "on":
							Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§5Shopping Spree", "§a" + args[1]);
							PowerupPatterns.useSSPattern = true;
							break;
						case "off":
							Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§5Shopping Spree", "§c" + args[1]);
							PowerupPatterns.useSSPattern = false;
							break;
						default:
							throw new WrongUsageException("/poweruppatterns ss [reset|5|6|7|on|off]");
					}
				}
				break;
			case "dg":
				Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.timer", "§6Double Gold");
				PowerupPatterns.dgTimer = true;
				ManualTimer.resetTimer((byte) 4);
				break;
			case "carpenter":
				Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.timer", "§9Carpenter");
				PowerupPatterns.carpenterTimer = true;
				ManualTimer.resetTimer((byte) 5);
				break;
			case "bg":
				Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.timer", "§6Bonus Gold");
				PowerupPatterns.bgTimer = true;
				ManualTimer.resetTimer((byte) 6);
				break;
			default:
				throw new WrongUsageException(getCommandUsage(null));
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