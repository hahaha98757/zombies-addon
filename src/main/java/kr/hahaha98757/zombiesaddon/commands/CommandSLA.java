// Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.listeners.sla.SLAListener;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class CommandSLA extends CommandBase {
	public static final CommandSLA instance = new CommandSLA();

	public String getCommandName() {
		return "SLA";
	}

	public int getRequiredPermissionLevel() {
		return 0;
	}

	public String getCommandUsage(ICommandSender sender) {
		return "\u00A7cUsage: /SLA <de|bb|aa|pr|off>";
	}

	public void processCommand(ICommandSender sender, String[] args) {
		if (!(sender instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer player = (EntityPlayer) sender;

		if (args.length >= 1) {
			if (!args[0].equals("de") && !args[0].equals("bb") && !args[0].equals("aa") && !args[0].equals("pr")
					&& !args[0].equals("off")) {
				player.addChatComponentMessage(new ChatComponentText(getCommandUsage(null)));
			} else if (args[0].equals("off")) {
				player.addChatComponentMessage(new ChatComponentText("\u00A7eSLA: Set SLA to \u00A7coff"));
				SLAListener.drop();
			} else {
				switch (args[0]) {
				case "de":
					SLAListener.instance = new SLAListener(1);
					Minecraft.getMinecraft().thePlayer
							.addChatMessage(new ChatComponentText("\u00A7eSLA: Set SLA to \u00A7ade"));
					break;
				case "bb":
					SLAListener.instance = new SLAListener(2);
					Minecraft.getMinecraft().thePlayer
							.addChatMessage(new ChatComponentText("\u00A7eSLA: Set SLA to \u00A7abb"));
					break;
				case "aa":
					SLAListener.instance = new SLAListener(3);
					Minecraft.getMinecraft().thePlayer
							.addChatMessage(new ChatComponentText("\u00A7eSLA: Set SLA to \u00A7aaa"));
					break;
				case "pr":
					SLAListener.instance = new SLAListener(4);
					Minecraft.getMinecraft().thePlayer
							.addChatMessage(new ChatComponentText("\u00A7eSLA: Set SLA to \u00A7apr"));
					break;
				}
			}
		} else {
			player.addChatComponentMessage(new ChatComponentText(getCommandUsage(null)));
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, "de", "bb", "aa", "pr", "off");
		}
		return null;
	}

	public void autoSLA(int map) {

		switch (map) {
		case 0:
			SLAListener.drop();
			break;
		case 1:
			SLAListener.instance = new SLAListener(1);
			Minecraft.getMinecraft().thePlayer
					.addChatComponentMessage(new ChatComponentText("\u00A7eAuto SLA: Set SLA to \u00A7ade"));
			break;
		case 2:
			SLAListener.instance = new SLAListener(2);
			Minecraft.getMinecraft().thePlayer
					.addChatComponentMessage(new ChatComponentText("\u00A7eAuto SLA: Set SLA to \u00A7abb"));
			break;
		case 3:
			SLAListener.instance = new SLAListener(3);
			Minecraft.getMinecraft().thePlayer
					.addChatComponentMessage(new ChatComponentText("\u00A7eAuto SLA: Set SLA to \u00A7aaa"));
			break;
		case 4:
			SLAListener.instance = new SLAListener(4);
			Minecraft.getMinecraft().thePlayer
					.addChatComponentMessage(new ChatComponentText("\u00A7eAuto SLA: Set SLA to \u00A7apr"));
			break;
		}
	}
}
