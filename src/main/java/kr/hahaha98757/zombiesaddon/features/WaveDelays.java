//Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.features;

import kr.hahaha98757.zombiesaddon.config.Hotkeys;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.data.GameData;
import kr.hahaha98757.zombiesaddon.data.wavedelays.*;
import kr.hahaha98757.zombiesaddon.enums.Difficulty;
import kr.hahaha98757.zombiesaddon.enums.Map;
import kr.hahaha98757.zombiesaddon.events.SoundEvent;
import kr.hahaha98757.zombiesaddon.utils.HUDUtils;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Arrays;

public class WaveDelays {
    private static final short DESPAWN_TICK = 6000;
    private static Difficulty difficulty = Difficulty.NORMAL;
    private static int rlOffset;
    private static boolean useRL;

    private static boolean escape;
    private static boolean gameEnd;
    private static byte round;

    public static void setDifficulty(Difficulty difficulty) {
        WaveDelays.difficulty = difficulty;
        String str;
        switch (difficulty) {
            case NORMAL:
                str = "§aNormal";
                break;
            case HARD:
                str = "§cHard";
                break;
            case RIP:
                str = "§4RIP";
                break;
            default:
                return;
        }
        Utils.addChat("§eWave Delays: Set difficulty to " + str);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Utils.isModDisable()) return;
        if (!Hotkeys.toggleRLMode.isPressed()) return;

        useRL = !useRL;
        if (useRL) rlOffset = ZombiesAddonConfig.getRLModeOffset();
        else rlOffset = 0;
        Utils.addChat("§eToggled RL Mode to " + (useRL ? "§aon" : "§coff"));

    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (Utils.isModDisable()) return;
        String message = EnumChatFormatting.getTextWithoutFormattingCodes(event.message.getUnformattedText());

        if (message.contains(">")) return;

        if (message.contains("The Helicopter is on its way! Hold out for 120 more seconds!")) escape = true;

        if (message.contains("Hard Difficulty") || message.contains("어려움 난이도")) difficulty = Difficulty.HARD;
        if (message.contains("RIP Difficulty") || message.contains("RIP 난이도")) difficulty = Difficulty.RIP;
    }

    @SubscribeEvent
    public void onSound(SoundEvent event) {
        if (Utils.isModDisable()) return;
        if (event.getSoundName().equals("mob.enderdragon.end")) gameEnd = true;
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (Utils.isModDisable()) return;
        if (ZombiesAddonConfig.isNotToggleWaveDelays()) return;
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return;
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;

        if (Utils.isNotZombies()) {
            gameEnd = false;
            return;
        }

        if (Utils.isNotPlayZombies()) {
            difficulty = Difficulty.NORMAL;
            gameEnd = false;
            escape = false;
        }

        if (!gameEnd) if (Utils.getMap() == Map.PRISON && escape) round = 31;
        else round = Utils.getRound();

        Round round = GameData.INSTANCE.getRound(Utils.getGameMode(difficulty), WaveDelays.round);
        if (round == null) return;

        Wave[] waves = round.getWaves();
        int roundTime = RoundTimer.instance.getTicks();
        int length = waves.length;
        int index = 0;
        boolean faded;
        String color = "§e";

        if (ZombiesAddonConfig.getHighlightStyle().equals("Zombies Addon")) {
            for (int i = waves.length-1; i >= 0; i--) {
                int waveDelay = waves[i].getTime() + rlOffset;

                if (roundTime >= waveDelay) {
                    if (ZombiesAddonConfig.isHidePassedWave() && roundTime > waveDelay) break;
                    String str = "➤ " + ZombiesAddonConfig.getTextStyle();
                    fr.drawStringWithShadow("§5➤ ", HUDUtils.getWaveDelaysStrX(str), HUDUtils.getWaveDelaysStrY(length, i), 0);
                    break;
                }
            }
        }

        for (Wave wave : waves) {
            int waveTime = wave.getTime() + rlOffset;

            String[] bossColor = new String[] {"", ""};
            for (Prefix prefix : wave.getPrefixes()) {
                if (!(prefix == Prefix.BOSS && ZombiesAddonConfig.isBossWaveColor())) continue;
                bossColor[0] = Utils.getBossColor1(Utils.getMap(), difficulty, WaveDelays.round, (byte) (index + 1));
                if (Utils.getMap() == Map.ALIEN_ARCADIUM) bossColor[1] = Utils.getBossColor2(WaveDelays.round, (byte) (index + 1));
            }

            String str = "";
            switch (ZombiesAddonConfig.getTextStyle()) {
                case "W1: 0:10.0":
                    str = "W" + (index + 1) + ": " + bossColor[0] + getMinutesString(waveTime) + ":" + bossColor[1] + getSecondsString(waveTime) + "." + getTenthSecondsString(waveTime);
                    break;
                case "W1 0:10.0":
                    str = "W" + (index + 1) + " " + bossColor[0] + getMinutesString(waveTime) + ":" + bossColor[1] + getSecondsString(waveTime) + "." + getTenthSecondsString(waveTime);
                    break;
                case "W1: 00:10":
                    str = "W" + (index + 1) + ": " + bossColor[0] + "0" + getMinutesString(waveTime) + ":" + bossColor[1] + getSecondsString(waveTime);
                    break;
                case "W1 00:10":
                    str = "W" + (index + 1) + " " + bossColor[0] + "0" + getMinutesString(waveTime) + ":" + bossColor[1] + getSecondsString(waveTime);
                    break;
            }

            switch (ZombiesAddonConfig.getHighlightStyle()) {
                case "Zombies Addon":
                    if (roundTime >= waveTime + DESPAWN_TICK) {
                        fr.drawStringWithShadow("§c" + str, HUDUtils.getWaveDelaysStrX(str), HUDUtils.getWaveDelaysStrY(length, index), 0);
                    } else if (roundTime >= waveTime)
                        if (roundTime != waveTime && ZombiesAddonConfig.isHidePassedWave()) {
                            index++;
                            continue;
                        } else
                            fr.drawStringWithShadow("§a" + str, HUDUtils.getWaveDelaysStrX(str), HUDUtils.getWaveDelaysStrY(length, index), 0);
                    else if (roundTime > waveTime - 60)
                        fr.drawStringWithShadow("§e" + str, HUDUtils.getWaveDelaysStrX(str), HUDUtils.getWaveDelaysStrY(length, index), 0);
                    else
                        fr.drawStringWithShadow("§8" + str, HUDUtils.getWaveDelaysStrX(str), HUDUtils.getWaveDelaysStrY(length, index), 0);

                    float width = HUDUtils.getWaveDelaysStrX("➤ " + str);
                    for (Prefix prefix : wave.getPrefixes()) {
                        if (ZombiesAddonConfig.isNotWavePrefix()) continue;
                        if (ZombiesAddonConfig.isBossWaveColor() && prefix == Prefix.BOSS) continue;
                        String prefixStr = prefix.getPrefix() + " ";
                        width -= fr.getStringWidth(prefixStr);
                        fr.drawStringWithShadow(prefixStr, width, HUDUtils.getWaveDelaysStrY(length, index), prefix.getColor());
                    }
                    break;
                case "Zombies Utils":
                    if (roundTime > waveTime) {
                        if (!ZombiesAddonConfig.isHidePassedWave()) {
                            faded = true;
                        } else {
                            index++;
                            continue;
                        }
                    } else {
                        faded = false;
                    }
                    fr.drawStringWithShadow((faded ? "§8" : color) + str, HUDUtils.getWaveDelaysStrX(str), HUDUtils.getWaveDelaysStrY(length, index), 0);
                    if (!faded && color.equals("§e"))
                        fr.drawStringWithShadow("§5➤ ", HUDUtils.getWaveDelaysStrX("➤ " + str), HUDUtils.getWaveDelaysStrY(length, index), 0);
                    for (Prefix prefix : wave.getPrefixes()) {
                        if (ZombiesAddonConfig.isNotWavePrefix()) continue;
                        if (ZombiesAddonConfig.isBossWaveColor() && prefix == Prefix.BOSS) continue;
                        String prefixString = prefix.getPrefix() + " ";
                        fr.drawStringWithShadow(prefixString, HUDUtils.getWaveDelaysStrX(prefixString + "➤ " + str), HUDUtils.getWaveDelaysStrY(length, index), prefix.getColor());
                    }
                    if (!faded) color = "§7";
                    break;
            }
            index++;
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (Utils.isModDisable()) return;
        if (event.phase != TickEvent.Phase.START) return;
        if (Utils.isNotZombies()) return;
        if (ZombiesAddonConfig.isNotToggleWaveDelays()) return;
        if (gameEnd) return;
        if (round == 0) return;

        Round round = GameData.INSTANCE.getRound(Utils.getGameMode(difficulty), WaveDelays.round);
        if (round == null) return;

        Wave[] waves = round.getWaves();
        int roundTime = RoundTimer.instance.getTicks();
        int length = waves.length;
        int index = 0;

        for (Wave wave : waves) {
            int waveTime = wave.getTime() + rlOffset;

            if (ZombiesAddonConfig.isCustomPlaySound()) {
                CustomPlaySound[] customPlaySounds = CustomPlaySoundLoader.readFromFile("config/zombiesaddon/CustomPlaySound.json");
                if (customPlaySounds == null) return;
                int pre = roundTime - waveTime;

                for (CustomPlaySound customPlaySound : customPlaySounds) {
                    if (customPlaySound.getTiming() != pre) continue;
                    String name = customPlaySound.getName();
                    float pitch = customPlaySound.getPitch();

                    switch (customPlaySound.getPlayWave()) {
                        case 1:
                            if (length != index + 1) Utils.playSound(name, pitch);
                            break;
                        case 2:
                            if (length == index + 1) Utils.playSound(name, pitch);
                            break;
                        default:
                            Utils.playSound(name, pitch);
                            break;
                    }
                }
            } else {
                int[] timings = ZombiesAddonConfig.getPlaySounds();
                @SuppressWarnings("WrapperTypeMayBePrimitive") Integer pre = roundTime - waveTime;
                if (Arrays.stream(timings).anyMatch(pre::equals)) Utils.playSound("note.pling", 2.0F);
            }
            index++;
        }
    }

    private static String getMinutesString(long ticks) {
        long ms = ticks * 50;
        return String.format("%d", ms / 60000);
    }

    private static String getSecondsString(long ticks) {
        long ms = ticks * 50;
        return String.format("%02d", (ms % 60000) / 1000);
    }

    private static String getTenthSecondsString(long ticks) {
        long ms = ticks * 50;
        return String.format("%d", (ms % 1000) / 100);
    }
}