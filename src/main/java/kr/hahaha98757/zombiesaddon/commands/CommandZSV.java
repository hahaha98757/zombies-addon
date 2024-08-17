// Code in Zombies Strat Viewer by syeyoung

package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.listeners.ZSVListener;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CommandZSV extends CommandBase {
	public static boolean ZSV = false;

	@Override
	public String getCommandName() {
		return "ZSV";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "§cUsage: /ZSV <url|off>\n§cThe url must start with \"https://pastebin.com/raw/\"";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (sender != Minecraft.getMinecraft().thePlayer) return;

		if (args.length == 0) {
			Utils.addChat(getCommandUsage(null));
			return;
		}

		if (args[0].equals("off")) {
			ZSVListener.START_LINES.clear();
			ZSVListener.START_LINES.add("");
			ZSVListener.currentLine = 0;
			Utils.addChat("§eZSV: Set ZSV to §coff");
			ZSV = false;
			return;
		}

		if (!args[0].startsWith("https://pastebin.com/raw/")) {
			Utils.addChat(getCommandUsage(null));
			return;
		}

		ZSVListener.START_LINES.clear();
		ZSVListener.START_LINES.add("");
		ZSVListener.currentLine = 0;
		try {
			URL url = new URL(args[0]);
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			huc.setDoInput(true);
			huc.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));

			String str;
			while ((str = br.readLine()) != null) {
				ZSVListener.START_LINES.add(str);
			}
			ZSV = true;
			ZSVListener.recalcActualLines();
			br.close();
			huc.getInputStream().close();
			Utils.addChat("§eZSV: Set ZSV to §aon");
		} catch (IOException e) {
			Utils.addChat("§eZSV: §cWrong URL.");
		}
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) return getListOfStringsMatchingLastWord(args, "off");
		return null;
	}
}