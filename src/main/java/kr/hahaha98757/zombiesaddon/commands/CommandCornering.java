package kr.hahaha98757.zombiesaddon.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class CommandCornering extends CommandBase {
	public static double range = 2.5;

	public String getCommandName() {
		return "cornering";
	}

	public int getRequiredPermissionLevel() {
		return 0;
	}

	public String getCommandUsage(ICommandSender sender) {
		return "\u00A7cUsage: /cornering [number]";
	}

	public void processCommand(ICommandSender sender, String[] args) {
		if (!(sender instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer player = (EntityPlayer) sender;

		if (args.length < 1) {
			player.addChatComponentMessage(new ChatComponentText("\u00A7eCornering: Range is " + range));
			return;
		}
		try {
			double input = Float.parseFloat(args[0]);
			range = input;
			player.addChatComponentMessage(new ChatComponentText("\u00A7eCornering: Range set to " + args[0]));
		} catch (NumberFormatException e) {
			player.addChatComponentMessage(new ChatComponentText(getCommandUsage(null)));
        }
	}

	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, "2.5");
		}
		return null;
	}
}
