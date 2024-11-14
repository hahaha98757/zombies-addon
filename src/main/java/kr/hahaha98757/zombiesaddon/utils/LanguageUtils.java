package kr.hahaha98757.zombiesaddon.utils;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class LanguageUtils {//en_US, ko_KR, pt_BR, zh_CN, zh_TW, cs_CZ, da_DK, nl_NL, fi_FI, fr_FR, de_DE, el_GR, hu_HU, it_IT, ja_JP, no_NO, en_PT, pl_PL, pt_PT, ro_RO, ru_RU, es_ES, uk_UA

    public static String getTranslateKey(String key) {
        String lang = ZombiesAddonConfig.getLanguage();
        String langCode = "en_US";
        switch (lang) {
            case "Auto":
                return key;
            case "English (US)":
                langCode = "en_US";
                break;
            case "한국어 (한국)":
                langCode = "ko_KR";
                break;
        }
        Properties langFile = new Properties();
        ResourceLocation resourceLocation = new ResourceLocation("zombiesaddon", "lang/" + langCode + ".lang");
        try {
            Reader reader = new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation).getInputStream());
            langFile.load(reader);
        } catch (IOException e) {
            return "";
        }
        return langFile.getProperty(key, key);
    }
}