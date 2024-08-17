// Code in Zombies Strat Viewer by syeyoung

package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.listeners.ZSVListener;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandZSVLines extends CommandBase {

	@Override
	public String getCommandName() {
		return "ZSVLines";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "§cUsage: /ZSVLines <number>\n§cThe number must be greater than 0";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (sender != Minecraft.getMinecraft().thePlayer) return;

		if (args.length == 0) {
			Utils.addChat(getCommandUsage(null));
			return;
		}

		try {
			int toBe = Integer.parseInt(args[0]);
			if (toBe < 1) {
				Utils.addChat(getCommandUsage(null));
				return;
			}

			ZSVListener.linesOfView = toBe;
			ZSVListener.recalcActualLines();
			Utils.addChat("§eZSV: Set Lines of View to §a" + ZSVListener.linesOfView);
		} catch (Exception e) {
			Utils.addChat(getCommandUsage(null));
		}
	}
}