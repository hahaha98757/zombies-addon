package kr.hahaha98757.zombiesaddon.config;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class Hotkeys {
    public static final KeyBinding togglePV = new KeyBinding("zombiesaddon.key.togglePlayerVisibility", Keyboard.KEY_Z, ZombiesAddon.NAME);
    public static final KeyBinding toggleBlockAlarm = new KeyBinding("zombiesaddon.key.toggleBlockAlarm", Keyboard.KEY_B, ZombiesAddon.NAME);
    public static final KeyBinding toggleAutoRejoin = new KeyBinding("zombiesaddon.key.toggleAutoRejoin", Keyboard.CHAR_NONE, ZombiesAddon.NAME);

    public static final KeyBinding zsvScrollUp = new KeyBinding("zombiesaddon.key.zsvScrollUp", Keyboard.KEY_LBRACKET, ZombiesAddon.NAME);
    public static final KeyBinding zsvScrollDown = new KeyBinding("zombiesaddon.key.zsvScrollDown", Keyboard.KEY_RBRACKET, ZombiesAddon.NAME);

    public static final KeyBinding toggleRLMode = new KeyBinding("zombiesaddon.key.toggleRLMode", Keyboard.KEY_UP, ZombiesAddon.NAME);

    public static final KeyBinding textMacro = new KeyBinding("zombiesaddon.key.textMacro", Keyboard.KEY_Q, ZombiesAddon.NAME);

    public static final KeyBinding instaTimer = new KeyBinding("zombiesaddon.key.instaTimer", Keyboard.KEY_NUMPAD1, ZombiesAddon.NAME);
    public static final KeyBinding maxTimer = new KeyBinding("zombiesaddon.key.maxTimer", Keyboard.KEY_NUMPAD2, ZombiesAddon.NAME);
    public static final KeyBinding ssTimer = new KeyBinding("zombiesaddon.key.ssTimer", Keyboard.KEY_NUMPAD3, ZombiesAddon.NAME);
    public static final KeyBinding dgTimer = new KeyBinding("zombiesaddon.key.dgTimer", Keyboard.KEY_NUMPAD4, ZombiesAddon.NAME);
    public static final KeyBinding carpenterTimer = new KeyBinding("zombiesaddon.key.carpenterTimer", Keyboard.KEY_NUMPAD5, ZombiesAddon.NAME);
    public static final KeyBinding bgTimer = new KeyBinding("zombiesaddon.key.bgTimer", Keyboard.KEY_NUMPAD6, ZombiesAddon.NAME);

    public static void registerAll() {
        ClientRegistry.registerKeyBinding(togglePV);
        ClientRegistry.registerKeyBinding(toggleBlockAlarm);
        ClientRegistry.registerKeyBinding(toggleAutoRejoin);
        ClientRegistry.registerKeyBinding(zsvScrollUp);
        ClientRegistry.registerKeyBinding(zsvScrollDown);
        ClientRegistry.registerKeyBinding(toggleRLMode);
        ClientRegistry.registerKeyBinding(textMacro);
        ClientRegistry.registerKeyBinding(instaTimer);
        ClientRegistry.registerKeyBinding(maxTimer);
        ClientRegistry.registerKeyBinding(ssTimer);
        ClientRegistry.registerKeyBinding(dgTimer);
        ClientRegistry.registerKeyBinding(carpenterTimer);
        ClientRegistry.registerKeyBinding(bgTimer);
    }
}