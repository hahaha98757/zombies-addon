//Code in Zombies Strat Viewer by syeyoung

package kr.hahaha98757.zombiesaddon;

import kr.hahaha98757.zombiesaddon.commands.CommandInfo;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateChecker {

	@SubscribeEvent
	public void playerJoinWorld(EntityJoinWorldEvent event) {
		if (event.entity != Minecraft.getMinecraft().thePlayer) {
			return;
		}
		MinecraftForge.EVENT_BUS.unregister(this);
		if (ZombiesAddon.VERSION.contains("Beta")) {
			return;
		}

		try {
			checkUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkUpdate() {
		String modVer = ZombiesAddon.VERSION;
		String latestVer = getlatestVer();

		if (latestVer != null) {
			displayText(compareVersions(modVer, latestVer), latestVer);
		}
	}

	// 0: Using latest ver, 1: Using latest pre-ver, 2: New ver with latest ver, 3:
	// New pre-ver with latest ver, 4: new ver with pre-ver, 5: new pre-ver with
	// pre-ver, 6: New required release
	private int compareVersions(String modVer, String latestVer) {
		boolean update = false;
		boolean modPre = false;
		boolean latestPre = false;
		String modPreVer = "9999";
		String latestPreVer = "9999";

		if (modVer.contains("pre")) {
			modPre = true;
			modPreVer = modVer.split("pre")[1];
			modVer = modVer.split("-")[0];
		}

		if (latestVer.contains("pre")) {
			latestPre = true;
			latestPreVer = latestVer.split("pre")[1];
			latestVer = latestVer.split("-")[0];
		}

		String[] modVerA = (modVer + "." + modPreVer).split("\\.");
		String[] latestVerA = (latestVer + "." + latestPreVer).split("\\.");

		for (int i = 0; i <= 3; i++) {
			int j = Integer.valueOf(modVerA[i]);
			int k = Integer.valueOf(latestVerA[i]);

			if (k > j) {
				if (i == 0) {
					return 6;
				}
				update = true;
				break;
			}
		}
		if (!update) {
			return modPre ? 1 : 0;
		}

		if (!modPre) {
			return latestPre ? 3 : 2;
		}

		return latestPre ? 5 : 4;
	}

	private void displayText(int i, String latestVer) {
		if (i == 0) {
			return;
		}
		if (i == 1) {
			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(CommandInfo.LINE
					+ "\nYou are using pre-release.\n\u00A7cPre-release is not perfect. There may be bugs.\n"
					+ CommandInfo.LINE));
		}
		if (i == 2) {
			ChatComponentText text1 = new ChatComponentText(CommandInfo.LINE + "\nA new release is available. ");
			ChatComponentText downloadText = new ChatComponentText("\u00A79\u00A7nClick here to download.");
			ChatComponentText text2 = new ChatComponentText("\n\u00A7rCurrent version: " + ZombiesAddon.VERSION
					+ "\nLatest version: " + latestVer + "\n" + CommandInfo.LINE);

			downloadText.getChatStyle().setChatClickEvent(
					new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/hahaha98757/zombies-addon/releases"));
			downloadText.getChatStyle().setChatHoverEvent(
					new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Open download URL.")));

			ChatComponentText text = new ChatComponentText("");
			text.appendSibling(text1);
			text.appendSibling(downloadText);
			text.appendSibling(text2);

			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(text);
		}
		if (i == 3) {
			if (!ZombiesAddonConfig.checkPreRelease) {
				return;
			}
			ChatComponentText text1 = new ChatComponentText(CommandInfo.LINE + "\nA new pre-release is available. ");
			ChatComponentText downloadText = new ChatComponentText("\u00A79\u00A7nClick here to download.");
			ChatComponentText text2 = new ChatComponentText("\n\u00A7rCurrent version: " + ZombiesAddon.VERSION
					+ "\nLatest version: " + latestVer
					+ "\n\u00A7cPre-release is not perfect. There may be bugs.\n\u00A7rNote: You can set hide this message in config.\n"
					+ CommandInfo.LINE);

			downloadText.getChatStyle().setChatClickEvent(
					new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/hahaha98757/zombies-addon/releases"));
			downloadText.getChatStyle().setChatHoverEvent(
					new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Open download URL.")));

			ChatComponentText text = new ChatComponentText("");
			text.appendSibling(text1);
			text.appendSibling(downloadText);
			text.appendSibling(text2);

			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(text);
		}
		if (i == 4) {
			ChatComponentText text1 = new ChatComponentText(
					CommandInfo.LINE + "\nA new release is available.\nYou are using pre-release.\nPlease update. ");
			ChatComponentText downloadText = new ChatComponentText("\u00A79\u00A7nClick here to download.");
			ChatComponentText text2 = new ChatComponentText("\n\u00A7rCurrent version: " + ZombiesAddon.VERSION
					+ "\nLatest version: " + latestVer + "\n" + CommandInfo.LINE);

			downloadText.getChatStyle().setChatClickEvent(
					new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/hahaha98757/zombies-addon/releases"));
			downloadText.getChatStyle().setChatHoverEvent(
					new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Open download URL.")));

			ChatComponentText text = new ChatComponentText("");
			text.appendSibling(text1);
			text.appendSibling(downloadText);
			text.appendSibling(text2);

			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(text);
			MinecraftForge.EVENT_BUS.register(this);
		}
		if (i == 5) {
			ChatComponentText text1 = new ChatComponentText(CommandInfo.LINE + "\nA new pre-release is available. ");
			ChatComponentText downloadText = new ChatComponentText("\u00A79\u00A7nClick here to download.");
			ChatComponentText text2 = new ChatComponentText("\n\u00A7rCurrent version: " + ZombiesAddon.VERSION
					+ "\nLatest version: " + latestVer
					+ "\n\u00A7cPre-release is not perfect. There may be bugs.\n\u00A7rNote: You can set hide this message in config.\n"
					+ CommandInfo.LINE);

			downloadText.getChatStyle().setChatClickEvent(
					new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/hahaha98757/zombies-addon/releases"));
			downloadText.getChatStyle().setChatHoverEvent(
					new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Open download URL.")));

			ChatComponentText text = new ChatComponentText("");
			text.appendSibling(text1);
			text.appendSibling(downloadText);
			text.appendSibling(text2);

			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(text);
		}
		if (i == 6) {
			ChatComponentText text1 = new ChatComponentText(CommandInfo.LINE + "\n\u00A7cRequired updates released. ");
			ChatComponentText downloadText = new ChatComponentText("\u00A79\u00A7nClick here to download.");
			ChatComponentText text2 = new ChatComponentText(
					"\n\u00A7r\u00A7cUPDATE NOW.\n\u00A7c\u00A7lThe game ends after 10 seconds.\n" + CommandInfo.LINE);

			downloadText.getChatStyle().setChatClickEvent(
					new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/hahaha98757/zombies-addon/releases"));
			downloadText.getChatStyle().setChatHoverEvent(
					new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Open download URL.")));

			ChatComponentText text = new ChatComponentText("");
			text.appendSibling(text1);
			text.appendSibling(downloadText);
			text.appendSibling(text2);

			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(text);
			ClientCrash.setUpdate();
			MinecraftForge.EVENT_BUS.register(new ClientCrash());
		}
	}

	private String getlatestVer() {
		try {
			URL url = new URL("https://pastebin.com/raw/ULVByaEU");
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			huc.setDoInput(true);
			huc.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));

			String str;
			while ((str = br.readLine()) != null) {
				if (str.contains("version=")) {
					return str.split("=")[1];
				}
			}

			br.close();
			huc.getInputStream().close();
		} catch (MalformedURLException var7) {
			var7.printStackTrace();
		} catch (IOException var8) {
			var8.printStackTrace();
		}
		return null;
	}
}
