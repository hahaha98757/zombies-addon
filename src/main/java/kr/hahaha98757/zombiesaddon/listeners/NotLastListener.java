//Code in NOTLAST by tahmid-23

package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.*;

public class NotLastListener {
	private final Map<String, Integer> scoreMap = new HashMap();
	private final Scoreboard scoreboard;
	private final ScoreObjective scoreObjective;
	private static int tick;

	public NotLastListener() {
		this.scoreboard = Minecraft.getMinecraft().theWorld.getScoreboard();
		this.scoreObjective = scoreboard.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("list"));
		Iterator var1 = this.scoreboard.getSortedScores(this.scoreObjective).iterator();

		while (var1.hasNext()) {
			Score score = (Score) var1.next();
			this.scoreMap.put(score.getPlayerName(), score.getScorePoints());
		}

	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent tickEvent) {
		if (!ZombiesAddonConfig.enableMod) {
			MinecraftForge.EVENT_BUS.unregister(this);
			return;
		}

		List<String> players = new ArrayList();
		Iterator var3 = this.scoreboard.getSortedScores(this.scoreObjective).iterator();

		while (var3.hasNext()) {
			Score score = (Score) var3.next();
			Integer kills = this.scoreMap.get(score.getPlayerName());
			if (kills != null && score.getScorePoints() != kills) {
				players.add(score.getPlayerName());
			}
		}

		if (players.size() > 0) {
			this.printLast(players);
		}

		if (tick >= 100) {
			MinecraftForge.EVENT_BUS.unregister(this);
			Minecraft.getMinecraft().thePlayer
					.addChatComponentMessage(new ChatComponentText("\u00A7eNot Last: \u00A7cFailed to find the last."));
			tick = 0;
		}

	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.START) {
			tick++;
		}
	}

	public static void resetTick() {
		tick = 0;
	}

	private void printLast(List<String> players) {
		MinecraftForge.EVENT_BUS.unregister(this);
		StringBuilder last = new StringBuilder();
		last.append(players.get(0));

		for (int i = 1; i < players.size(); ++i) {
			String player = players.get(i);
			last.append(" or ");
			last.append(player);
		}

		last.append(" was last");
		Minecraft.getMinecraft().thePlayer
				.addChatComponentMessage(new ChatComponentText("\u00A7eNot Last: " + last));
	}
}
