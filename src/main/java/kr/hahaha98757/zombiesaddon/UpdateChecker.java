package kr.hahaha98757.zombiesaddon;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateChecker {
	private static final String urlText = "\u00A79\u00A7nClick here to download.";
	private static final String url = "https://github.com/hahaha98757/zombies-addon1.12.2/releases";
	private static final String showURLText = "Open download URL.";

	@SubscribeEvent
	public void playerJoinWorld(EntityJoinWorldEvent event) {
		if (event.entity != Minecraft.getMinecraft().thePlayer) return;

		MinecraftForge.EVENT_BUS.unregister(this);

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

	private String getlatestVer() {
		try {
			URL url = new URL("https://pastebin.com/raw/ULVByaEU");
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			huc.setDoInput(true);
			huc.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));

			String str;
			while ((str = br.readLine()) != null) {
				if (str.contains("version1.12.2=")) {
					return str.split("=")[1];
				}
			}

			br.close();
			huc.getInputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 0: Using latest ver, 1: Using latest pre-ver, 2: New ver with latest ver, 3: New pre-ver with latest ver, 4: New ver with pre-ver, 5: new pre-ver with pre-ver, 6: New required release
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
			int j = Integer.parseInt(modVerA[i]);
			int k = Integer.parseInt(latestVerA[i]);

			if (k > j) {
				if (i == 0) return 6;
				update = true;
				break;
			}
		}
		if (!update) return modPre ? 1 : 0;

		if (!modPre) return latestPre ? 3 : 2;

		return latestPre ? 5 : 4;
	}

	private void displayText(int i, String latestVer) {
		if (i == 0) return;
		if (i == 1)
			Utils.addChatLine("You are using pre-release.\n\u00A7cPre-release is not perfect. There may be bugs.");
		if (i == 2)
			Utils.addChatWithURL(Utils.LINE + "\nA new release is available. ", urlText, url, showURLText, "\n\u00A7rCurrent version: " + ZombiesAddon.VERSION + "\nLatest version: " + latestVer + "\n" + Utils.LINE);
		if (i == 3) {
			if (!ZombiesAddonConfig.checkPreRelease) return;
			Utils.addChatWithURL(Utils.LINE + "\nA new pre-release is available. ", urlText, url, showURLText, "\n\u00A7rCurrent version: " + ZombiesAddon.VERSION + "\nLatest version: " + latestVer + "\n\u00A7cPre-release is not perfect. There may be bugs.\n\u00A7rNote: You can set hide this message in config.\n" + Utils.LINE);
		}
		if (i == 4) {
			Utils.addChatWithURL(Utils.LINE + "\nA new release is available.\nYou are using pre-release.\nPlease update. ", urlText, url, showURLText, "\n\u00A7rCurrent version: " + ZombiesAddon.VERSION + "\nLatest version: " + latestVer + "\n" + Utils.LINE);
			MinecraftForge.EVENT_BUS.register(this);
		}
		if (i == 5) {
			Utils.addChatWithURL(Utils.LINE + "\nA new pre-release is available. ", urlText, url, showURLText, "\n\u00A7rCurrent version: " + ZombiesAddon.VERSION + "\nLatest version: " + latestVer + "\n\u00A7cPre-release is not perfect. There may be bugs.\n\u00A7rNote: You can set hide this message in config.\n" + Utils.LINE);
			MinecraftForge.EVENT_BUS.register(this);
		}
		if (i == 6) {
			Utils.addChatWithURL(Utils.LINE + "\n\u00A7cRequired updates released. " , urlText, url, showURLText, "\n\u00A7r\u00A7cUPDATE NOW.\n\u00A7c\u00A7lThe game ends after 10 seconds.\n" + Utils.LINE);
			ClientCrash.setUpdate();
			MinecraftForge.EVENT_BUS.register(new ClientCrash());
		}
	}
}