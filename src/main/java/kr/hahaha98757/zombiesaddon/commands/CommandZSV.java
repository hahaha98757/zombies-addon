// Code in Zombies Strat Viewer by syeyoung

package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.listeners.ZSVListener;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CommandZSV extends CommandBase implements ICommand {
	public static boolean ZSV = false;

	public String getCommandName() {
		return "ZSV";
	}

	public int getRequiredPermissionLevel() {
		return 0;
	}

	public String getCommandUsage(ICommandSender sender) {
		return "\u00A7cUsage: /ZSV <url|off>\nThe url must start with \"https://pastebin.com/raw/\"";
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
		if (!args[0].startsWith("https://pastebin.com/raw/")) {
			if (args[0].equals("off")) {
				ZSVListener.START_LINES.clear();
				ZSVListener.START_LINES.add("");
				ZSVListener.currentLine = 0;
				player.addChatComponentMessage(new ChatComponentText("\u00A7eZSV: Set ZSV to \u00A7coff"));
				ZSV = false;
				return;
			}
			player.addChatComponentMessage(new ChatComponentText(getCommandUsage(null)));
			return;
		}
		ZSV = true;
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

			ZSVListener.recalcActualLines();
			br.close();
			huc.getInputStream().close();
			sender.addChatMessage(new ChatComponentText("\u00A7eZSV: Set ZSV to \u00A7aon"));
		} catch (MalformedURLException var7) {
			var7.printStackTrace();
		} catch (IOException var8) {
			var8.printStackTrace();
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, "off");
		}
		return null;
	}
}
