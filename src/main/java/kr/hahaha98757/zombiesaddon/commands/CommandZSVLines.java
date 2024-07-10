// Code in Zombies Strat Viewer by syeyoung

package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.listeners.ZSVListener;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class CommandZSVLines extends CommandBase implements ICommand {
	public CommandZSVLines() {
	}

	public String getCommandName() {
		return "ZSVLines";
	}

	public int getRequiredPermissionLevel() {
		return 0;
	}

	public String getCommandUsage(ICommandSender sender) {
		return "\u00A7cUsage: /ZSVLines <number>\nThe number must be greater than 0";
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
		try {
			int toBe = Integer.parseInt(args[0]);
			if (toBe < 1) {
				player.addChatComponentMessage(new ChatComponentText(getCommandUsage(null)));
				return;
			}

			ZSVListener.linesOfView = toBe;
			ZSVListener.recalcActualLines();
			player.addChatComponentMessage(
					new ChatComponentText("\u00A7eZSV: Set Lines of View to \u00A7a" + ZSVListener.linesOfView));
		} catch (Exception var4) {
			player.addChatComponentMessage(new ChatComponentText(getCommandUsage(null)));
		}
	}
}
