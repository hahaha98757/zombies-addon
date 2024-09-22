// Code in Zombies Strat Viewer by syeyoung

package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.features.ZombiesStratViewer;
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
		return "§cUsage: /ZSVLines <number>";
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
				Utils.addChat("§cThe number must be higher than 0");
				return;
			}

			ZombiesStratViewer.linesOfView = toBe;
			ZombiesStratViewer.recalcActualLines();
			Utils.addChat("§eZSV: Set lines of view to §a" + ZombiesStratViewer.linesOfView);
		} catch (Exception e) {
			Utils.addChat("§cThe number must be higher than 0");
		}
	}
}