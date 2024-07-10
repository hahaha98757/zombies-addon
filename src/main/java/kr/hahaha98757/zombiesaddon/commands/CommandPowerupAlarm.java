package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.listeners.PowerupAlarmListener;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.Collections;
import java.util.List;

public class CommandPowerupAlarm extends CommandBase {

	public String getCommandName() {
		return "powerupAlarm";
	}

	public int getRequiredPermissionLevel() {
		return 0;
	}

	public String getCommandUsage(ICommandSender sender) {
		return "\u00A7cUsage: /powerupAlarm <insta|max|ss> [number|on|off]";
	}

	public List<String> getCommandAliases() {
		return Collections.singletonList("pow");
	}

	public void processCommand(ICommandSender sender, String[] args) {
		if (!(sender instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer player = (EntityPlayer) sender;

		if (args.length == 0) {
			player.addChatComponentMessage(new ChatComponentText(getCommandUsage(null)));
			return;
		}
		switch (args[0]) {
		case "insta":
			if (args.length == 1) {
				player.addChatComponentMessage(new ChatComponentText("\u00A7ePowerup Alarm: Reset insta pattern"));
				PowerupAlarmListener.instaPattern = 0;
			} else {
				if (args[1].equals("2") || args[1].equals("3")) {
					player.addChatComponentMessage(new ChatComponentText(
							String.format("\u00A7ePowerup Alarm: Set insta pattern to \u00A7a%s", args[1])));
					int i = Integer.parseInt(args[1]);
					PowerupAlarmListener.instaPattern = i;
				} else if (args[1].equals("on")) {
					player.addChatComponentMessage(
							new ChatComponentText("\u00A7ePowerup Alarm: Set insta pattern to \u00A7aon"));
					PowerupAlarmListener.useInstaPattern = true;
				} else if (args[1].equals("off")) {
					player.addChatComponentMessage(
							new ChatComponentText("\u00A7ePowerup Alarm: Set insta pattern to \u00A7coff"));
					PowerupAlarmListener.useInstaPattern = false;
				} else {
					player.addChatComponentMessage(
							new ChatComponentText("\u00A7cUsage: /powerupAlarm insta [2|3|on|off]"));
				}
			}
			break;
		case "max":
			if (args.length == 1) {
				player.addChatComponentMessage(
						new ChatComponentText("\u00A7ePowerup Alarm: Reset max pattern."));
				PowerupAlarmListener.maxPattern = 0;
			} else {
				if (args[1].equals("2") || args[1].equals("3")) {
					player.addChatComponentMessage(new ChatComponentText(
							String.format("\u00A7ePowerup Alarm: Set max pattern to \u00A7a%s", args[1])));
					int i = Integer.parseInt(args[1]);
					PowerupAlarmListener.maxPattern = i;
				} else if (args[1].equals("on")) {
					player.addChatComponentMessage(
							new ChatComponentText("\u00A7ePowerup Alarm: Set max pattern to \u00A7aon"));
					PowerupAlarmListener.useMaxPattern = true;
				} else if (args[1].equals("off")) {
					player.addChatComponentMessage(
							new ChatComponentText("\u00A7epPwerup Alarm: Set max pattern to \u00A7coff"));
					PowerupAlarmListener.useMaxPattern = false;
				} else {
					player.addChatComponentMessage(
							new ChatComponentText("\u00A7cUsage: /powerupAlarm max [2|3|on|off]"));
				}
			}
			break;
		case "ss":
			if (args.length == 1) {
				player.addChatComponentMessage(
						new ChatComponentText("\u00A7ePowerup Alarm: Reset ss pattern"));
				PowerupAlarmListener.ssPattern = 0;
			} else {
				if (args[1].equals("5") || args[1].equals("6") || args[1].equals("7")) {
					player.addChatComponentMessage(new ChatComponentText(
							String.format("\u00A7ePowerup Alarm: Set ss pattern to \u00A7a%s", args[1])));
					int i = Integer.parseInt(args[1]);
					PowerupAlarmListener.ssPattern = i;
				} else if (args[1].equals("on")) {
					player.addChatComponentMessage(
							new ChatComponentText("\u00A7ePowerup Alarm: Set ss pattern to \u00A7aon"));
					PowerupAlarmListener.useSSPattern = true;
				} else if (args[1].equals("off")) {
					player.addChatComponentMessage(
							new ChatComponentText("\u00A7ePowerup Alarm: Set ss pattern to \u00A7coff"));
					PowerupAlarmListener.useSSPattern = false;
				} else {
					player.addChatComponentMessage(
							new ChatComponentText("\u00A7cUsage: /powerupAlarm ss [5|6|7|on|off]"));
				}
			}
			break;
		default:
			player.addChatComponentMessage(new ChatComponentText(getCommandUsage(null)));
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, "insta", "max", "ss");
		} else if (args[0].equals("insta") || args[0].equals("max")) {
			return getListOfStringsMatchingLastWord(args, "2", "3", "on", "off");
		} else if (args[0].equals("ss")) {
			return getListOfStringsMatchingLastWord(args, "5", "6", "7", "on", "off");
		}
		return null;
	}

}
