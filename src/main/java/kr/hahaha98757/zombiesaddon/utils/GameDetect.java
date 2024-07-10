//Code in Cornering by syeyoung

package kr.hahaha98757.zombiesaddon.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.BlockPos;

import java.util.Iterator;

public class GameDetect {

	public static int getRound() {
		int round = 0;
		Scoreboard scoreboard = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
		ScoreObjective scoreObjective = scoreboard
				.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));
		if (scoreObjective != null
				&& scoreObjective.getDisplayName().replaceAll("\u00A7.", "").equalsIgnoreCase("ZOMBIES")) {
			Iterator var7 = scoreboard.getSortedScores(scoreObjective).iterator();

			while (var7.hasNext()) {
				Score score = (Score) var7.next();
				ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
				String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName());
				if (score.getScorePoints() == 13 && (s.contains("Round") || s.contains("\uB77C\uC6B4\uB4DC"))) {
					s = s.replaceAll("\u00A7.", "");
					round = 0;
					try {
						s = s.replaceAll("[^0-9]", "").trim();
					} catch (Exception var13) {
						round = 0;
						return round;
					}

					try {
						round = Integer.parseInt(s);
					} catch (Exception e) {
						round = 0;
						return round;
					}

				}
			}
			return round;
		}
		return 0;
	}

	public static int getDifficult(int map, int round) {// Normal: 1, Hard: 2, RIP: 3
		Scoreboard scoreboard = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
		ScoreObjective scoreObjective = scoreboard
				.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));
		if (scoreObjective != null
				&& scoreObjective.getDisplayName().replaceAll("\u00A7.", "").equalsIgnoreCase("ZOMBIES")) {
			Iterator var7 = scoreboard.getSortedScores(scoreObjective).iterator();

			while (var7.hasNext()) {
				Score score = (Score) var7.next();
				ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
				String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName());
				if (score.getScorePoints() == 12) {
					s = s.replaceAll("\u00A7.", "");
					try {
						s = s.replaceAll("[^0-9]", "").trim();
					} catch (Exception var13) {
						return 1;
					}
					// �ڵ� ���� ��
					if (map == 1) {
						if (round == 4 && s.equals("26")) {
							return 3;
						}
						if (round == 9 && s.equals("34")) {
							return 3;
						}
						if (round == 14) {
							if (s.equals("41")) {
								return 2;
							} else if (s.equals("48")) {
								return 3;
							}
						}
						if (round == 19 && s.equals("68")) {
							return 3;
						}
						if (round == 24) {
							if (s.equals("70")) {
								return 2;
							} else if (s.equals("78")) {
								return 3;
							}
						}
						if (round == 29) {
							if (s.equals("101")) {
								return 2;
							} else if (s.equals("111")) {
								return 3;
							}
						}
					} else if (map == 2) {
						if (round == 14 && s.equals("42")) {
							return 3;
						}
						if (round == 19 && s.equals("44")) {
							return 3;
						}
						if (round == 24 && s.equals("66")) {
							return 3;
						}
						if (round == 29 && s.equals("83")) {
							return 3;
						}
					}
				}
			}
		}
		return 1;
	}

	public static int getMap() {// DE: 1, BB: 2, AA: 3, Prison: 4
		if (!isZombiesGame()) {
			return 0;
		}
		BlockPos blockPos = new BlockPos(0, 72, 12);
		String blockName = Minecraft.getMinecraft().theWorld.getBlockState(blockPos).getBlock().getUnlocalizedName();

		// System.out.println(blockName);

		switch (blockName) {
		case "tile.cloth":// wool
			return 1;
		case "tile.stonebricksmooth":// stonebrick
			return 2;
		case "tile.woolCarpet":// carpet
			return 3;
		case "tile.clayHardenedStained":// stained_hardened_clay
			return 4;
		}
		return 0;
	}

	public static boolean isZombiesGame() {
		try {
			Scoreboard scoreboard = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
			ScoreObjective scoreObjective = scoreboard
					.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));
			if (scoreObjective != null
					&& scoreObjective.getDisplayName().replaceAll("\u00A7.", "").equalsIgnoreCase("ZOMBIES")) {
				Iterator var7 = scoreboard.getSortedScores(scoreObjective).iterator();
				while (var7.hasNext()) {
					Score score = (Score) var7.next();
					ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
					String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName());
					if (score.getScorePoints() == 10) {
						s = s.replaceAll("\u00A7.", "");

						try {
							s = s.split(":")[0].replaceAll("[^a-zA-Z0-9_]", "");
						} catch (Exception var13) {
							return false;
						}

						if (s.equalsIgnoreCase(Minecraft.getMinecraft().thePlayer.getName())) {
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static int getLang() {// English: 0, Korean: 1
		int lang = 0;
		Scoreboard scoreboard = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
		ScoreObjective scoreObjective = scoreboard
				.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));
		if (scoreObjective != null
				&& scoreObjective.getDisplayName().replaceAll("\u00A7.", "").equalsIgnoreCase("ZOMBIES")) {
			Iterator var7 = scoreboard.getSortedScores(scoreObjective).iterator();

			while (var7.hasNext()) {
				Score score = (Score) var7.next();
				ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
				String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName());
				if (score.getScorePoints() == 13) {
					if (s.contains("Round")) {
						lang = 0;
						return lang;
					} else if (s.contains("\uB77C\uC6B4\uB4DC")) {
						lang = 1;
						return lang;
					}
				}
			}
		}
		return lang;
	}

	public static String[] getPlayerNames() {
		String[] names = { "", "", "", "" };
		try {
			Scoreboard scoreboard = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
			ScoreObjective scoreObjective = scoreboard
					.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));
			if (scoreObjective != null
					&& scoreObjective.getDisplayName().replaceAll("\u00A7.", "").equalsIgnoreCase("ZOMBIES")) {
				Iterator var7 = scoreboard.getSortedScores(scoreObjective).iterator();
				while (var7.hasNext()) {
					Score score = (Score) var7.next();
					ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
					String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName());
					if (score.getScorePoints() == 10) {
						String name;
						try {
							name = s.split(":")[0].replaceAll("\u00A7.", "").replaceAll("[^a-zA-Z0-9_]", "");
						} catch (Exception var13) {
							name = "";
						}
						names[0] = name;
					}
					if (score.getScorePoints() == 9) {
						String name;
						try {
							name = s.split(":")[0].replaceAll("\u00A7.", "").replaceAll("[^a-zA-Z0-9_]", "");
						} catch (Exception var13) {
							name = "";
						}
						names[1] = name;
					}
					if (score.getScorePoints() == 8) {
						String name;
						try {
							name = s.split(":")[0].replaceAll("\u00A7.", "").replaceAll("[^a-zA-Z0-9_]", "");
						} catch (Exception var13) {
							name = "";
						}
						names[2] = name;
					}
					if (score.getScorePoints() == 7) {
						String name;
						try {
							name = s.split(":")[0].replaceAll("\u00A7.", "").replaceAll("[^a-zA-Z0-9_]", "");
						} catch (Exception var13) {
							name = "";
						}
						names[3] = name;
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		return names;
	}

	public static int[] getPlayerState() {// 0: living, 1: revive, 2: dead, 3: quit
		int[] state = { 0, 0, 0, 0 };
		try {
			Scoreboard scoreboard = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
			ScoreObjective scoreObjective = scoreboard
					.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));
			if (scoreObjective != null
					&& scoreObjective.getDisplayName().replaceAll("\u00A7.", "").equalsIgnoreCase("ZOMBIES")) {
				Iterator var7 = scoreboard.getSortedScores(scoreObjective).iterator();
				while (var7.hasNext()) {
					Score score = (Score) var7.next();
					ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
					String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName());
					if (score.getScorePoints() == 10) {
						try {
							s = s.split(":")[1].replaceAll("\u00A7.", "").replaceAll("[^a-zA-Z0-9\uAC00-\uD7A3]", "");
						} catch (Exception var13) {
							s = "";
						}

						if (s.equalsIgnoreCase("revive") || s.equalsIgnoreCase("\uBD80\uD65C")) {
							state[0] = 1;
						} else if (s.equalsIgnoreCase("dead") || s.equalsIgnoreCase("\uC0AC\uB9DD")) {
							state[0] = 2;
						} else if (s.equalsIgnoreCase("quit") || s.equalsIgnoreCase("\uB5A0\uB0A8")) {
							state[0] = 3;
						}
					}
					if (score.getScorePoints() == 9) {
						try {
							s = s.split(":")[1].replaceAll("\u00A7.", "").replaceAll("[^a-zA-Z0-9\uAC00-\uD7A3]", "");
						} catch (Exception var13) {
							s = "";
						}

						if (s.equalsIgnoreCase("revive") || s.equalsIgnoreCase("\uBD80\uD65C")) {
							state[1] = 1;
						} else if (s.equalsIgnoreCase("dead") || s.equalsIgnoreCase("\uC0AC\uB9DD")) {
							state[1] = 2;
						} else if (s.equalsIgnoreCase("quit") || s.equalsIgnoreCase("\uB5A0\uB0A8")) {
							state[1] = 3;
						}
					}
					if (score.getScorePoints() == 8) {
						try {
							s = s.split(":")[1].replaceAll("\u00A7.", "").replaceAll("[^a-zA-Z0-9\uAC00-\uD7A3]", "");
						} catch (Exception var13) {
							s = "";
						}

						if (s.equalsIgnoreCase("revive") || s.equalsIgnoreCase("\uBD80\uD65C")) {
							state[2] = 1;
						} else if (s.equalsIgnoreCase("dead") || s.equalsIgnoreCase("\uC0AC\uB9DD")) {
							state[2] = 2;
						} else if (s.equalsIgnoreCase("quit") || s.equalsIgnoreCase("\uB5A0\uB0A8")) {
							state[2] = 3;
						}
					}
					if (score.getScorePoints() == 7) {
						try {
							s = s.split(":")[1].replaceAll("\u00A7.", "").replaceAll("[^a-zA-Z0-9\uAC00-\uD7A3]", "");
						} catch (Exception var13) {
							s = "";
						}

						if (s.equalsIgnoreCase("revive") || s.equalsIgnoreCase("\uBD80\uD65C")) {
							state[3] = 1;
						} else if (s.equalsIgnoreCase("dead") || s.equalsIgnoreCase("\uC0AC\uB9DD")) {
							state[3] = 2;
						} else if (s.equalsIgnoreCase("quit") || s.equalsIgnoreCase("\uB5A0\uB0A8")) {
							state[3] = 3;
						}
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		return state;
	}

	public static boolean isSolo() {
		int p1 = getPlayerState()[0];
		int p2 = getPlayerState()[1];
		int p3 = getPlayerState()[2];
		int p4 = getPlayerState()[3];

		if (p1 == 0 && p2 != 0 && p3 != 0 && p4 != 0) {
			return true;
		}
		if (p1 != 0 && p2 == 0 && p3 != 0 && p4 != 0) {
			return true;
		}
		if (p1 != 0 && p2 != 0 && p3 == 0 && p4 != 0) {
			return true;
		}
        return p1 != 0 && p2 != 0 && p3 != 0 && p4 == 0;
    }


	public static String getArea() {
		String area = "";
		Scoreboard scoreboard = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
		ScoreObjective scoreObjective = scoreboard
				.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));
		if (scoreObjective != null
				&& scoreObjective.getDisplayName().replaceAll("\u00A7.", "").equalsIgnoreCase("ZOMBIES")) {
			Iterator var7 = scoreboard.getSortedScores(scoreObjective).iterator();

			while (var7.hasNext()) {
				Score score = (Score) var7.next();
				ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
				String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName());
				if (score.getScorePoints() == 3 && s.contains("Area")) {
					s = s.replaceAll("\u00A7.", "");
					try {
						area = s.split(":")[1].replaceAll("[^A-Za-z]", "").trim();
					} catch (Exception var13) {
						area = "";
						return area;
					}
				}
			}
			return area;
		}
		return area;
	}

}
