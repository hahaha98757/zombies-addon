//Code in Zombies Strat Viewer by syeyoung

package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.features.ZombiesStratViewer;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.BlockPos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CommandZSV extends CommandBase {

	@Override
	public String getCommandName() {
		return "zsv";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/zsv <off|URL>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (sender != Minecraft.getMinecraft().thePlayer) return;

		if (args.length == 0) throw new WrongUsageException(getCommandUsage(null));

		if (args[0].equals("off")) {
			ZombiesStratViewer.START_LINES.clear();
			ZombiesStratViewer.START_LINES.add("");
			ZombiesStratViewer.currentLine = 0;
			Utils.addTranslationChat("zombiesaddon.commands.zsv.success", "§coff");
			ZombiesStratViewer.zombiesStratViewer = false;
			return;
		}

		if (!args[0].startsWith("https://pastebin.com/raw/"))
            throw new CommandException("zombiesaddon.commands.zsv.wrongURL");

		ZombiesStratViewer.START_LINES.clear();
		ZombiesStratViewer.START_LINES.add("");
		ZombiesStratViewer.currentLine = 0;
		try {
			URL url = new URL(args[0]);
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			huc.setDoInput(true);
			huc.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));

			String str;
			while ((str = br.readLine()) != null) ZombiesStratViewer.START_LINES.add(str);
			ZombiesStratViewer.zombiesStratViewer = true;
			ZombiesStratViewer.recalcActualLines();
			br.close();
			huc.getInputStream().close();
			Utils.addTranslationChat("zombiesaddon.commands.zsv.success", "§aon");
		} catch (IOException e) {
			Utils.addTranslationChat("zombiesaddon.commands.zsv.failed", new Object());
		}
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) return getListOfStringsMatchingLastWord(args, "off");
		return null;
	}
}