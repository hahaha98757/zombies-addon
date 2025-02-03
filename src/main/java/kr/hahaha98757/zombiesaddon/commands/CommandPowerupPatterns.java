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
			case "ins":
				if (args.length == 1) {
					Utils.addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§cInsta Kill");
					PowerupPatterns.insTimer = true;
					ManualTimer.INS.runTimer();
				} else {
					switch (args[1]) {
						case "reset":
							Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.reset", "§cInsta Kill");
							PowerupPatterns.insPattern = 0;
							break;
						case "2":
						case "3":
							Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§cInsta Kill", "§a" + args[1]);
							PowerupPatterns.insPattern = Byte.parseByte(args[1]);
							break;
						case "on":
							Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§cInsta Kill", "§a" + args[1]);
							PowerupPatterns.useInsPattern = true;
							break;
						case "off":
							Utils.addTranslationChat("zombiesaddon.commands.poweruppatterns.success.set", "§cInsta Kill", "§c" + args[1]);
							PowerupPatterns.useInsPattern = false;
							break;
						default:
							throw new WrongUsageException("/poweruppatterns ins [reset|2|3|on|off]");
					}
				}
				break;
			case "max":
				if (args.length == 1) {
					Utils.addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§9Max Ammo");
					PowerupPatterns.maxTimer = true;
					ManualTimer.MAX.runTimer();
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
					Utils.addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§5Shopping Spree");
					PowerupPatterns.ssTimer = true;
					ManualTimer.SS.runTimer();
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
				Utils.addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§6Double Gold");
				PowerupPatterns.dgTimer = true;
				ManualTimer.DG.runTimer();
				break;
			case "car":
				Utils.addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§9Carpenter");
				PowerupPatterns.carTimer = true;
				ManualTimer.CAR.runTimer();
				break;
			case "bg":
				Utils.addTranslationChat("zombiesaddon.features.powerupPatterns.timer", "§6Bonus Gold");
				PowerupPatterns.bgTimer = true;
				ManualTimer.BG.runTimer();
				break;
			default:
				throw new WrongUsageException(getCommandUsage(null));
		}
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1)
			return getListOfStringsMatchingLastWord(args, "ins", "max", "ss", "dg", "car", "bg");
        else if (args[0].equals("ins") || args[0].equals("max"))
            return getListOfStringsMatchingLastWord(args, "reset", "2", "3", "on", "off");
        else if (args[0].equals("ss"))
			return getListOfStringsMatchingLastWord(args, "reset", "5", "6", "7", "on", "off");
		return null;
	}
}