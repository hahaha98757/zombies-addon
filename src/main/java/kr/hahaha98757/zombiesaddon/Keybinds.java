//Code in Zombies Hologrambug Fixer by syeyoung

package kr.hahaha98757.zombiesaddon;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class Keybinds {
	public static final String KEY_CATEGORY = "Zombies Addon";
	public static KeyBinding toggleCornering;
	public static KeyBinding toggleBlockAlarm;
	public static KeyBinding toggleAutoRejoin;

	public static KeyBinding scrollUp;
	public static KeyBinding scrollDown;

	public static KeyBinding toggleRLMode;

	public static KeyBinding textMacro;

	public static void register() {
		toggleCornering = new KeyBinding("Toggle Cornering", Keyboard.KEY_V, KEY_CATEGORY);
		toggleBlockAlarm = new KeyBinding("Toggle Block Alarm", Keyboard.KEY_B, KEY_CATEGORY);
		toggleAutoRejoin = new KeyBinding("Toggle Auto Rejoin", Keyboard.CHAR_NONE, KEY_CATEGORY);

		scrollUp = new KeyBinding("ZSV Scroll Up", Keyboard.KEY_LBRACKET, KEY_CATEGORY);
		scrollDown = new KeyBinding("ZSV Scroll Down", Keyboard.KEY_RBRACKET, KEY_CATEGORY);

		toggleRLMode = new KeyBinding("Toggle RL Mode", Keyboard.KEY_L, KEY_CATEGORY);

		textMacro = new KeyBinding("Text Macro", Keyboard.KEY_T, KEY_CATEGORY);

		ClientRegistry.registerKeyBinding(toggleCornering);
		ClientRegistry.registerKeyBinding(toggleBlockAlarm);
		ClientRegistry.registerKeyBinding(toggleAutoRejoin);

		ClientRegistry.registerKeyBinding(scrollUp);
		ClientRegistry.registerKeyBinding(scrollDown);

		ClientRegistry.registerKeyBinding(toggleRLMode);

		ClientRegistry.registerKeyBinding(textMacro);
	}
}
