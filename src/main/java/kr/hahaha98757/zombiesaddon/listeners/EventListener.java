//Code in Zombies Hologrambug Fixer by syeyoung

package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.ClientCrash;
import kr.hahaha98757.zombiesaddon.Keybinds;
import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.events.SoundEvent;
import kr.hahaha98757.zombiesaddon.events.TitleEvent;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class EventListener {
	public static boolean cornering = ZombiesAddonConfig.modDefaultValue[0];
	public static boolean blockAlarm = ZombiesAddonConfig.modDefaultValue[1];
	public static boolean autoRejoin = ZombiesAddonConfig.modDefaultValue[2];

	public static boolean rlMode;

	private static boolean join;

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (!ZombiesAddonConfig.enableMod) return;

		if (Keybinds.toggleCornering.isPressed()) {
			cornering = !cornering;
			addToggleChat(cornering, "Cornering");
		}
		if (Keybinds.toggleBlockAlarm.isPressed()) {
			blockAlarm = !blockAlarm;
			addToggleChat(blockAlarm, "Block Alarm");
		}
		if (Keybinds.toggleAutoRejoin.isPressed()) {
			autoRejoin = !autoRejoin;
			addToggleChat(autoRejoin, "Auto Rejoin");
		}
		if (Keybinds.toggleRLMode.isPressed()) {
			rlMode = !rlMode;
			addToggleChat(rlMode, "RL Mode");
		}
	}

	private void addToggleChat(boolean var, String name) {
		Utils.addChat("§eToggled " + name + " to " + (var ? "§aon" : "§coff"));
	}

	@SubscribeEvent
	public void showMods(RenderGameOverlayEvent.Post event) {
		FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
		if (ClientCrash.update) {
			String str = "Zombies Addon update required!";
			fr.drawStringWithShadow(str, Utils.getX(str), 0, 0xff5555);
			return;
		}
		if (ClientCrash.unlegit) {
			String str = "Detected Unlegit Mods!";
			fr.drawStringWithShadow(str, Utils.getX(str), 0, 0xff5555);
			return;
		}

		if (!ZombiesAddonConfig.enableMod) return;

		if (event.type != ElementType.TEXT) return;

		if (!ZombiesAddonConfig.showMods) return;

		byte y = (byte) fr.FONT_HEIGHT;

		String str = "Zombies Addon v" + ZombiesAddon.VERSION;
		fr.drawStringWithShadow(str, Utils.getX(str), 1, 0xffff55);

		str = "Cornering: " + (cornering ? "§aon" : "§coff");
		fr.drawStringWithShadow(str, Utils.getX(str), 1 + y, 0xffff55);

		str = "Block Alarm: " + (blockAlarm ? "§aon" : "§coff");
		fr.drawStringWithShadow(str, Utils.getX(str), 1 + y*2, 0xffff55);

		if (ZombiesAddonConfig.hideAutoRejoin) return;
		str = "Auto Rejoin: " + (autoRejoin ? "§aon" : "§coff");
		fr.drawStringWithShadow(str, Utils.getX(str), 1 + y*3, 0xffff55);
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals(ZombiesAddon.MODID)) ZombiesAddonConfig.reloadConfig();
	}

	@SubscribeEvent
	public void onTitle(TitleEvent event) {
		String str = EnumChatFormatting.getTextWithoutFormattingCodes(event.getTitle());

		if (str.equals("Round 1") || str.equals("1라운드")) {
			PowerupPatternsListener.spawnedEntities.clear();
			PowerupPatternsListener.instaPattern = 0;
			PowerupPatternsListener.maxPattern = 0;
			PowerupPatternsListener.ssPattern = 0;
		}

		if (str.equals("You Win!") || str.equals("승리했습니다!")) {
			LastWeaponsListener.gameOver = true;
		}
	}

	@SubscribeEvent
	public void onSound(SoundEvent event) {
		if (event.getSoundEffect().getSoundName().equals("mob.wither.spawn")) {
			WaveDelaysListener.stop = false;
			WaveDelaysListener.escape = false;
			PowerupPatternsListener.stop = false;
			LastWeaponsListener.gameOver = false;
		}

		if (!event.getSoundEffect().getSoundName().equals("mob.enderdragon.end")) return;

		WaveDelaysListener.stop = true;
		PowerupPatternsListener.stop = true;
	}

	@SubscribeEvent
	public void overlayKorean(ClientChatReceivedEvent event) {
		if (!ZombiesAddonConfig.enableMod) return;

		if (!ZombiesAddonConfig.overlayKorean) return;

		String message = event.message.getUnformattedText();

		if (message.contains(">")) return;

		if (message.startsWith("온라인: ")) {// who
			String playerList = message.split(":")[1].trim();

			Utils.addChat("ONLINE: " + playerList);

		}

		if (message.contains("님이 참여했습니다!")) {// join
			String playerName = message.split(" ")[0];
			String number = message.split(" ")[3].split("/")[0].replaceAll("[^0-9]", "");

			Utils.addChat(playerName + " has joined (" + number + "/4)!");
		}

		if (message.contains("님이 나갔습니다!")) {// quit
			String playerName = message.split(" ")[0];

			Utils.addChat(playerName + " has quit!");
		}

		if (message.contains("서버로 이동합니다!")) {// join the zombies
			String text = message.split(" ")[0];

			Utils.addChat("Sending you to " + text + "!");
		}
	}

	@SubscribeEvent
	public void onPlayerJoinWorld(EntityJoinWorldEvent event) {
		if (event.entity != Minecraft.getMinecraft().thePlayer) return;

		if (join) return;

		if (ZombiesAddon.isConfigReset)
            Utils.addChatLine("The config of Zombies Addon has been reset.\nPlease check the config.");

		if (ZombiesAddon.oldSST) {
			Utils.addChatLine("You are using Show Spawn Time of old version.\nPlease update the mod.");
		}

		if (ZombiesAddon.detectUnlegit) {
			Utils.addChatLine("§cYou are using ZombiesSatellite, Zombies Explorer, or TeammatesOutline.\n§cThey are unlegit mods. Please remove them.\n§c§lThe game ends after 10 seconds.");
			ClientCrash.setUnlegit();
			MinecraftForge.EVENT_BUS.register(new ClientCrash());
		}

		join = true;
	}
}