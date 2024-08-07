// Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.listeners.SLAListener;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.List;

public class CommandSLA extends CommandBase {

	@Override
	public String getCommandName() {
		return "SLA";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "\u00A7cUsage: /SLA <de|bb|aa|pr|off>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (sender != Minecraft.getMinecraft().thePlayer) return;

		if (args.length == 0 || !(args[0].equals("de") || args[0].equals("bb") || args[0].equals("aa") || args[0].equals("pr") || args[0].equals("off"))) {
			Utils.addChat(getCommandUsage(null));
			return;
		}

		Utils.addChat("\u00A7eSLA: Set SLA to " + (args[0].equals("off") ? "\u00A7c" : "\u00A7a") + args[0]);
		SLAListener.setSLA(args[0]);
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length != 1) return null;
		return getListOfStringsMatchingLastWord(args, "de", "bb", "aa", "pr", "off");
	}
}