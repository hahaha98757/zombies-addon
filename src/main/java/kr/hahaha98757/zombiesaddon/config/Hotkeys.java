package kr.hahaha98757.zombiesaddon.config;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class Hotkeys {
    public static final KeyBinding togglePV = new KeyBinding("Toggle Player Visibility", Keyboard.KEY_Z, ZombiesAddon.NAME);
    public static final KeyBinding toggleBlockAlarm = new KeyBinding("Toggle Block Alarm", Keyboard.KEY_B, ZombiesAddon.NAME);
    public static final KeyBinding toggleAutoRejoin = new KeyBinding("Toggle Auto Rejoin", Keyboard.CHAR_NONE, ZombiesAddon.NAME);

    public static final KeyBinding zsvScrollUp = new KeyBinding("ZSV Scroll Up", Keyboard.KEY_LBRACKET, ZombiesAddon.NAME);
    public static final KeyBinding zsvScrollDown = new KeyBinding("ZSV Scroll Down", Keyboard.KEY_RBRACKET, ZombiesAddon.NAME);

    public static final KeyBinding toggleRLMode = new KeyBinding("Toggle RL Mode", Keyboard.KEY_UP, ZombiesAddon.NAME);

    public static final KeyBinding textMacro = new KeyBinding("Text Macro", Keyboard.KEY_Q, ZombiesAddon.NAME);

    public static final KeyBinding instaTimer = new KeyBinding("Insta Timer", Keyboard.KEY_NUMPAD1, ZombiesAddon.NAME);
    public static final KeyBinding maxTimer = new KeyBinding("Max Timer", Keyboard.KEY_NUMPAD2, ZombiesAddon.NAME);
    public static final KeyBinding ssTimer = new KeyBinding("SS Timer", Keyboard.KEY_NUMPAD3, ZombiesAddon.NAME);
    public static final KeyBinding dgTimer = new KeyBinding("DG Timer", Keyboard.KEY_NUMPAD4, ZombiesAddon.NAME);
    public static final KeyBinding carpenterTimer = new KeyBinding("Carpenter Timer", Keyboard.KEY_NUMPAD5, ZombiesAddon.NAME);
    public static final KeyBinding bgTimer = new KeyBinding("BG Timer", Keyboard.KEY_NUMPAD6, ZombiesAddon.NAME);

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