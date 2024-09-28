//Code in NOTLAST by tahmid-23

package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.events.SoundEvent;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotLast {
    private final Map<String, Integer> scoreMap = new HashMap<>();
    private Scoreboard scoreboard;
    private ScoreObjective scoreObjective;
    private static byte tick;
    private static boolean work = false;

    @SubscribeEvent
    public void onSound(SoundEvent event) {
        if (Utils.isModDisable()) return;

        String soundName = event.getSoundName();

        if (!ZombiesAddonConfig.isToggleNotLast()) return;
        if (Utils.isNotZombies()) return;
        if (!soundName.equals("mob.wither.spawn")) return;
        if (Utils.getRound() == 1) return;
        if (Utils.getRevDeadQuit()[2] == 3) return;

        scoreboard = Minecraft.getMinecraft().theWorld.getScoreboard();
        scoreObjective = scoreboard.getObjectiveInDisplaySlot(0);
        for (Score score : scoreboard.getSortedScores(scoreObjective))
            scoreMap.put(score.getPlayerName(), score.getScorePoints());

        resetTick();
        work = true;
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent tickEvent) {
        if (Utils.isModDisable()) {
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
            Utils.addChat("§eNot Last: §cFailed to find the last.");
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
        Utils.addChat("§eNot Last: " + last);
    }
}