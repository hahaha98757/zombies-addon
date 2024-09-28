//Code in Show Spawn Time by Seosean

package kr.hahaha98757.zombiesaddon.utils;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class HUDUtils {
    private static FontRenderer fr;

    public static double autoSplitsX;
    public static double autoSplitsY;

    public static double waveDelaysX;
    public static double waveDelaysY;

    public static double powerupPatternsX;
    public static double powerupPatternsY;

    private static final String REGEX1 = "^W[1-9]:? \\d+:\\d{2}\\.\\d$";
    private static final String REGEX2 = "^W[1-9]:? \\d{2}:\\d{2}$";

    public static void set() {
        fr = Minecraft.getMinecraft().fontRendererObj;
    }

    public static double getAutoSplitsX() {
        return autoSplitsX < 0 ? 1.0 - (double) (fr.getStringWidth("0:00.0") + 1) / Utils.getX() : autoSplitsX;
    }

    public static double getAutoSplitsY(){
        return autoSplitsY < 0 ? 1.0 - (fr.FONT_HEIGHT + 1) / Utils.getY() : autoSplitsY;
    }

    public static double getWaveDelaysX() {
        return waveDelaysX < 0 ? 1.0 - (double) (fr.getStringWidth("➤ " + ZombiesAddonConfig.getTextStyle()) + 1) / Utils.getX() : waveDelaysX;
    }

    public static double getWaveDelaysY(){
        return waveDelaysY < 0 ? 1.0 - (fr.FONT_HEIGHT * 9 + 1) / Utils.getY() : waveDelaysY;
    }

    public static double getPowerupPatternsX() {
        return powerupPatternsX < 0 ? 1.0 - (fr.getStringWidth("Shopping Spree: Round 105") + 1) / Utils.getX() : powerupPatternsX;
    }

    public static double getPowerupPatternsY() {
        return powerupPatternsY < 0 ? 1.0 - (fr.FONT_HEIGHT * 15 + 1) / Utils.getY() : powerupPatternsY;
    }
    
    public static float getAutoSplitsStrX(String str) {
        return autoSplitsX < 0 ? Utils.getX(str) : (float) getAutoSplitsX() * Utils.getX();
    }
    
    public static float getAutoSplitsStrY() {
        return autoSplitsY < 0 ? Utils.getYFont() : (float) getAutoSplitsY() * Utils.getY();
    }
    
    public static float getWaveDelaysStrX(String str) {
        if (str.matches(REGEX1) || str.matches(REGEX2)) {
            return waveDelaysX < 0 ? Utils.getX(str) : (float) (getWaveDelaysX() * Utils.getX() + fr.getStringWidth("➤ "));
        }
        return waveDelaysX < 0 ? Utils.getX(str) : (float) (getWaveDelaysX() * Utils.getX());
    }

    public static float getWaveDelaysStrY(int length, int index) {
        return waveDelaysY < 0 ? Utils.getYFont() - fr.FONT_HEIGHT * (length - index) : (float) getWaveDelaysY() * Utils.getY() + fr.FONT_HEIGHT * 8 - fr.FONT_HEIGHT * (length - index);
    }
    
    public static float getPowerupPatternsStrX(String str) {
        if (powerupPatternsX < 0) return Utils.getX(str);
        else if (powerupPatternsX < 0.5) return (float) (getPowerupPatternsX() * Utils.getX());
        else
            return (float) (getPowerupPatternsX() * Utils.getX() + fr.getStringWidth("Shopping Spree: Round 105") - fr.getStringWidth(str));
    }
    
    public static float getPowerupPatternsStrY(int i) {
        return powerupPatternsY < 0 ? Utils.getYFont() - fr.FONT_HEIGHT * (15 - i) : (float) (getPowerupPatternsY() * Utils.getY() + fr.FONT_HEIGHT * (i - 1));
    }
}