//Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Optional;

public class ResourceLoader {
    public static Optional<JsonElement> readJsonResource(final String resourcePath) {
        ResourceLocation resourceLocation = new ResourceLocation("zombiesaddon", resourcePath);
        try (Reader reader = new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation).getInputStream())) {
            return Optional.ofNullable(new JsonParser().parse(reader));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}