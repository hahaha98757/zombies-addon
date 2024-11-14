//Code in Zombies Strat Viewer by syeyoung

package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.features.ZombiesStratViewer;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.*;

public class CommandZSVLines extends CommandBase {

	@Override
	public String getCommandName() {
		return "zsvlines";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "zombiesaddon.commands.zsvlines.usage";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (sender != Minecraft.getMinecraft().thePlayer) return;

		if (args.length == 0) throw new WrongUsageException(getCommandUsage(null));

		try {
			int toBe = Integer.parseInt(args[0]);
			if (toBe < 1) throw new CommandException("commands.generic.num.tooSmall", toBe, 1);

			ZombiesStratViewer.linesOfView = toBe;
			ZombiesStratViewer.recalcActualLines();
			Utils.addTranslationChat("zombiesaddon.commands.zsvlines.success", "§a" + toBe);
		} catch (NumberFormatException e) {
			throw new NumberInvalidException("commands.generic.num.invalid", args[0]);
		}
	}
}