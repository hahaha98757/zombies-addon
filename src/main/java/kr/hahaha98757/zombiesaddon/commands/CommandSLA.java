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
		return "/sla <de|bb|aa|off|quick|custom>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (sender != Minecraft.getMinecraft().thePlayer) return;

		if (args.length == 0) throw new WrongUsageException(getCommandUsage(null));

		switch (args[0]) {
			case "off":
				SpawnLimitAction.offMap();
				Utils.addTranslationChat("zombiesaddon.commands.sla.setMap", "§coff");
				break;
			case "de":
				SpawnLimitAction.setMap(Map.DEAD_END);
				Utils.addTranslationChat("zombiesaddon.commands.sla.setMap", "§aDead End");
				break;
			case "bb":
				SpawnLimitAction.setMap(Map.BAD_BLOOD);
				Utils.addTranslationChat("zombiesaddon.commands.sla.setMap", "§aBad Blood");
				break;
			case "aa":
				SpawnLimitAction.setMap(Map.ALIEN_ARCADIUM);
				Utils.addTranslationChat("zombiesaddon.commands.sla.setMap", "§aAlien Arcadium");
				break;
            case "quick":
				if (args.length == 1) throw new WrongUsageException("/sla quick <mogi_a|ghxula|ghxula-garden>");
                switch (args[1]) {
					case "mogi_a":
						SpawnLimitAction.setMap(Map.BAD_BLOOD);
						SpawnLimitAction.rotate(3);
						SpawnLimitAction.setOffset(new int[]{-3, 35, -9});
						Utils.addTranslationChat("zombiesaddon.commands.sla.setMap", "§aBad Blood");
						Utils.addTranslationChat("zombiesaddon.commands.sla.rotates", "§a90°");
						Utils.addTranslationChat("zombiesaddon.commands.sla.setOffset", "§a-3 35 -9");
						break;
					case "ghxula":
						SpawnLimitAction.setMap(Map.DEAD_END);
						SpawnLimitAction.rotate(1);
						SpawnLimitAction.setOffset(new int[]{27, 35, 5});
						Utils.addTranslationChat("zombiesaddon.commands.sla.setMap", "§aDead End");
						Utils.addTranslationChat("zombiesaddon.commands.sla.rotates", "§a270°");
						Utils.addTranslationChat("zombiesaddon.commands.sla.setOffset", "§a27 35 5");
						break;
                    case "ghxula-garden":
						SpawnLimitAction.setMap(Map.DEAD_END);
						SpawnLimitAction.rotate(1);
						SpawnLimitAction.setOffset(new int[]{13, 53, -8});
						Utils.addTranslationChat("zombiesaddon.commands.sla.setMap", "§aDead End");
						Utils.addTranslationChat("zombiesaddon.commands.sla.rotates", "§a270°");
						Utils.addTranslationChat("zombiesaddon.commands.sla.setOffset", "§a13 53 -8");
						break;
					default:
						throw new WrongUsageException("/sla quick <mogi_a|ghxula|ghxula-garden>");
				}
				break;
			case "custom":
				if (args.length == 1) throw new WrongUsageException("/sla custom <offset|rotate|mirror>");
				switch (args[1]) {
					case "offset":
						if (args.length == 2) {
                            SpawnLimitAction.resetOffset();
							Utils.addTranslationChat("zombiesaddon.commands.sla.setOffset", "§coff");
							return;
                        } else if (args.length != 5) throw new WrongUsageException("/sla custom offset <x> <y> <z>");
                        else {
							BlockPos blockPos = parseBlockPos(sender, args, 2, false);
							SpawnLimitAction.setOffset(new int[] {blockPos.getX(), blockPos.getY(), blockPos.getZ()});
							Utils.addTranslationChat("zombiesaddon.commands.sla.setOffset", String.format("§a%d %d %d", blockPos.getX(), blockPos.getY(), blockPos.getZ()));
						}
						break;
					case "rotate":
						if (args.length == 2) {
                            SpawnLimitAction.resetRotate();
							Utils.addTranslationChat("zombiesaddon.commands.sla.resetRotations", new Object());
							return;
                        } else {
							try {
								int rotations = Integer.parseInt(args[2]);
								rotations %= 4;
								if (rotations < 0) rotations += 4;
								SpawnLimitAction.rotate(rotations);
								switch (rotations) {
									case 0:
										Utils.addTranslationChat("zombiesaddon.commands.sla.rotates", "§a0°");
										break;
									case 1:
										Utils.addTranslationChat("zombiesaddon.commands.sla.rotates", "§a270°");
										break;
									case 2:
										Utils.addTranslationChat("zombiesaddon.commands.sla.rotates", "§a180°");
										break;
									case 3:
										Utils.addTranslationChat("zombiesaddon.commands.sla.rotates", "§a90°");
										break;
								}
							} catch (NumberFormatException e) {
								throw new NumberInvalidException("commands.generic.num.invalid", args[2]);
							}
						}
						break;
					case "mirror":
						if (args.length == 2) {
                            SpawnLimitAction.resetMirroring();
							Utils.addTranslationChat("zombiesaddon.commands.sla.resetMirroring", new Object());
							return;
                        }
						switch (args[2]) {
							case "x":
								SpawnLimitAction.mirrorX();
								Utils.addTranslationChat("zombiesaddon.commands.sla.mirror", "§a0 y z");
								break;
							case "z":
								SpawnLimitAction.mirrorZ();
								Utils.addTranslationChat("zombiesaddon.commands.sla.mirror", "§ax y 0");
								break;
							default:
								throw new WrongUsageException("/sla custom mirror <x|z>");
						}
						break;
					default:
						throw new WrongUsageException("/sla custom <offset|rotate|mirror>");
				}
				break;
            default:
				throw new WrongUsageException(getCommandUsage(null));
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