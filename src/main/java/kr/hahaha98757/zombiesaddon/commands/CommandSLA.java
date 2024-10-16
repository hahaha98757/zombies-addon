//Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.commands;

import kr.hahaha98757.zombiesaddon.enums.Map;
import kr.hahaha98757.zombiesaddon.features.SpawnLimitAction;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.*;
import net.minecraft.util.BlockPos;

import java.util.List;

public class CommandSLA extends CommandBase {

	@Override
	public String getCommandName() {
		return "sla";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return String.format("§cUsage: /%s <de|bb|aa|off|quick|custom>", getCommandName());
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (sender != Minecraft.getMinecraft().thePlayer) return;

		if (args.length == 0) {
			Utils.addChat(getCommandUsage(null));
			return;
		}

		switch (args[0]) {
			case "off":
				SpawnLimitAction.offMap();
				break;
			case "de":
				SpawnLimitAction.setMap(Map.DEAD_END);
				break;
			case "bb":
				SpawnLimitAction.setMap(Map.BAD_BLOOD);
				break;
			case "aa":
				SpawnLimitAction.setMap(Map.ALIEN_ARCADIUM);
				break;
            case "quick":
				if (args.length == 1) {
                    Utils.addChat(String.format("§cUsage: /%s quick <mogi_a|ghxula|ghxula-garden>", getCommandName()));
					return;
				}
                switch (args[1]) {
					case "mogi_a":
						SpawnLimitAction.setMap(Map.BAD_BLOOD);
						SpawnLimitAction.rotate(3);
						SpawnLimitAction.setOffset(new int[]{-3, 35, -9});
						break;
					case "ghxula":
						SpawnLimitAction.setMap(Map.DEAD_END);
						SpawnLimitAction.rotate(1);
						SpawnLimitAction.setOffset(new int[]{27, 35, 5});
						break;
                    case "ghxula-garden":
						SpawnLimitAction.setMap(Map.DEAD_END);
						SpawnLimitAction.rotate(1);
						SpawnLimitAction.setOffset(new int[]{13, 53, -8});
						break;
					default:
						Utils.addChat(String.format("§cUsage: /%s quick <mogi_a|ghxula|ghxula-garden>", getCommandName()));
				}
				break;
			case "custom":
				if (args.length == 1) {
					Utils.addChat(String.format("§cUsage: /%s custom <offset|rotate|mirror>", getCommandName()));
					return;
				}
				switch (args[1]) {
					case "offset":
						if (args.length == 2) {
                            //noinspection SpellCheckingInspection
                            Utils.addChat("§eSLA: §cReset §eoffset");
                            SpawnLimitAction.resetOffset();
                        } else if (args.length != 5) Utils.addChat(String.format("§cUsage: /%s set offset [x] [y] [z]", getCommandName()));
                        else {
							try {
								int x = Integer.parseInt(args[2]);
								int y = Integer.parseInt(args[3]);
								int z = Integer.parseInt(args[4]);
								SpawnLimitAction.setOffset(new int[] {x, y, z});
							} catch (NumberFormatException ignored) {
								Utils.addChat("§eSLA: §cWrong number");
								return;
							}
						}
						break;
					case "rotate":
						if (args.length == 2) SpawnLimitAction.rotate(1);
						else {
							int rotations;
							try {
								rotations = Integer.parseInt(args[2]);
							} catch (NumberFormatException ignored) {
								Utils.addChat("§eSLA: §cWrong number");
								return;
							}
							SpawnLimitAction.rotate(rotations);
						}
						break;
					case "mirror":
						if (args.length == 2) {
							Utils.addChat(String.format("§cUsage: /%s custom mirror <x|z>", getCommandName()));
							break;
						}
						switch (args[2]) {
							case "x":
								SpawnLimitAction.mirrorX();
								break;
							case "z":
								SpawnLimitAction.mirrorZ();
								break;
							default:
								Utils.addChat(String.format("§cUsage: /%s custom mirror <x|z>", getCommandName()));
						}
						break;
					default:
						Utils.addChat(String.format("§cUsage: /%s custom <offset|rotate|mirror>", getCommandName()));
				}
				break;
            default:
				Utils.addChat(getCommandUsage(null));
		}
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 0) return null;
		if (args.length == 1) return getListOfStringsMatchingLastWord(args, "de", "bb", "aa", "off", "quick", "custom");
		switch (args[0]) {
			case "quick":
                return getListOfStringsMatchingLastWord(args, "mogi_a", "ghxula", "ghxula-garden");
			case "custom":
				if (args.length == 2)
                    return getListOfStringsMatchingLastWord(args, "offset", "rotate", "mirror");
				switch (args[1]) {
					case "rotate":
						return getListOfStringsMatchingLastWord(args, "0", "1", "2", "3");
					case "mirror":
						return getListOfStringsMatchingLastWord(args, "x", "z");
                }
			default:
				return null;
		}
	}
}