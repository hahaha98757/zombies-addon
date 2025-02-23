package kr.hahaha98757.zombiesaddon.data.wavedelays;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class CustomPlaySoundLoader {

    public static CustomPlaySound[] readFromFile(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) return null;

        try (Reader reader = new FileReader(file)) {
            JsonElement JsonElement = new JsonParser().parse(reader);
            return new Gson().fromJson(JsonElement, CustomPlaySound[].class);
        } catch (Exception e) {
            return null;
        }
    }
}