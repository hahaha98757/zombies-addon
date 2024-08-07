//Code in NOTLAST by tahmid-23

package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.events.TitleEvent;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotLastListener {
	private final Map<String, Integer> scoreMap = new HashMap<>();
	private Scoreboard scoreboard;
	private ScoreObjective scoreObjective;
	private static byte tick;
	private static boolean work = false;

	@SubscribeEvent
	public void onTitle(TitleEvent event) {
		if (!ZombiesAddonConfig.enableMod) return;

		String str = EnumChatFormatting.getTextWithoutFormattingCodes(event.getTitle());

		if (!ZombiesAddonConfig.toggleNotLast) return;

		if (!(str.startsWith("Round ") && !str.equals("Round 1")) && !(str.matches(".*\\d+\uB77C\uC6B4\uB4DC") && !str.equals("1\uB77C\uC6B4\uB4DC")))
			return;

		if (Utils.getRevDeadQuit()[2] == 3) return;

		scoreboard = Minecraft.getMinecraft().theWorld.getScoreboard();
		scoreObjective = scoreboard.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("list"));
		for (Score score : scoreboard.getSortedScores(scoreObjective))
			scoreMap.put(score.getPlayerName(), score.getScorePoints());

		NotLastListener.resetTick();
		work = true;
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent tickEvent) {
		if (!ZombiesAddonConfig.enableMod) {
			work = false;
			return;
		}

		if (!work) return;

		List<String> players = new ArrayList<>();

		for (Score score : scoreboard.getSortedScores(scoreObjective)) {
			Integer kills = scoreMap.get(score.getPlayerName());
			if (kills != null && score.getScorePoints() != kills) players.add(score.getPlayerName());
		}

		if (!players.isEmpty()) printLast(players);

		if (tick >= 100) {
			work = false;
			Utils.addChat("\u00A7eNot Last: \u00A7cFailed to find the last.");
			tick = 0;
		}

	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.START) tick++;
	}

	private static void resetTick() {
		tick = 0;
	}

	private void printLast(List<String> players) {
		work = false;
		StringBuilder last = new StringBuilder();
		last.append(players.get(0));

		for (int i = 1; i < players.size(); ++i) {
			String player = players.get(i);
			last.append(" or ");
			last.append(player);
		}

		last.append(" was last");
		Utils.addChat("\u00A7eNot Last: " + last);
	}
}