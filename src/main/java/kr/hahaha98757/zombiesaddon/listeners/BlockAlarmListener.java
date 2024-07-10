//Code in Cornering by syeyoung

package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.lwjgl.opengl.GL11;

import java.util.Iterator;
import java.util.List;

public class BlockAlarmListener {
	private static List<Entity> spawnedEntities;

	@SubscribeEvent
	public void blockAlarm(RenderGameOverlayEvent.Post event) {
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}

		if (event.type != ElementType.EXPERIENCE) {
			return;
		}
		if (EventListener.blockAlarm) {
			Scoreboard scoreboard = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
			ScoreObjective scoreObjective = scoreboard
					.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));
			if (scoreObjective != null
					&& scoreObjective.getDisplayName().replaceAll("\u00A7.", "").equalsIgnoreCase("ZOMBIES")) {
				int rev = 0;
				int dead = 0;
				int quit = 0;
				Iterator var7 = scoreboard.getSortedScores(scoreObjective).iterator();

				while (var7.hasNext()) {
					Score score = (Score) var7.next();
					ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
					String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName());
					if (score.getScorePoints() <= 10 && score.getScorePoints() >= 7) {
						s = s.replaceAll("\u00A7.", "");

						try {
							s = s.split(":")[1].replaceAll("[^a-zA-Z0-9\uAC00-\uD7A3]", "").trim();
						} catch (Exception var13) {
							return;
						}

						if (s.equalsIgnoreCase("revive") || s.equalsIgnoreCase("\uBD80\uD65C")) {
							++rev;
						} else if (s.equalsIgnoreCase("dead") || s.equalsIgnoreCase("\uC0AC\uB9DD")) {
							++dead;
						} else if (s.equalsIgnoreCase("quit") || s.equalsIgnoreCase("\uB5A0\uB0A8")) {
							++quit;
						}
					}
				}

				FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
				ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
				ReflectionHelper.getPrivateValue(GuiIngame.class, Minecraft.getMinecraft().ingameGUI, 0);

				if ((rev + dead + quit == 3) && (quit + dead != 3)) {
					GL11.glPushMatrix();
					GL11.glScalef(7.0F, 7.0F, 7.0F);
					fr.drawStringWithShadow("BLOCK", (float) (sr.getScaledWidth() + fr.getStringWidth("BLOCK")) / 14.0F,
							((float) sr.getScaledHeight() / 2.0F - 8.0F) / 7.0F, 11141120);
					GL11.glPopMatrix();
				}
			}
		}
	}
}
