package kr.hahaha98757.zombiesaddon;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.ClientCrash;
import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.io.IOUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;

public class UpdateChecker {
	private static final String URL_TEXT = "§9§nClick here to download.";
	private static final String URL = "https://github.com/hahaha98757/zombies-addon/releases";
	private static final String SHOW_URL_TEXT = "Open download URL.";
	private static final String VERSION_URL = "https://raw.githubusercontent.com/hahaha98757/zombies-addon/master/versions.json";

	private static String latestVer = null;
	private static String log = null;

	private static SSLContext ctx;
	static {
		try {
			KeyStore myKeyStore = KeyStore.getInstance("JKS");

            //noinspection SpellCheckingInspection
            myKeyStore.load(UpdateChecker.class.getResourceAsStream("/mykeystore.jks"), "changeit".toCharArray());
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			kmf.init(myKeyStore, null);
			tmf.init(myKeyStore);
			ctx = SSLContext.getInstance("TLS");
			ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		} catch (Exception e) {
			e.printStackTrace();
			ctx = null;
		}
	}

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

	public static void checkUpdate() {
		new Thread(() -> {
			try {
				URL url = new URL(VERSION_URL);
				HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
				if (connection != null && ctx != null) {
					connection.setSSLSocketFactory(ctx.getSocketFactory());
				}
                //noinspection DataFlowIssue
                connection.setRequestMethod("GET");
				connection.setConnectTimeout(60000);
				connection.setReadTimeout(60000);

				InputStream inputStream = connection.getInputStream();
				String jsonResponse = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
				inputStream.close();

				JsonObject json = new JsonParser().parse(jsonResponse).getAsJsonObject();
				latestVer = json.get("version").getAsString();
				log = json.get("log").getAsString();
				Minecraft.getMinecraft().addScheduledTask(() -> {
					if (latestVer != null) displayText(compareVersions(ZombiesAddon.VERSION, latestVer));
				});
			} catch (Exception e) {
				e.printStackTrace();
				latestVer = null;
				log = null;
			}
		}).start();
	}

	// 0: Using latest ver, 1: Using latest pre-ver, 2: New ver with latest ver, 3: New pre-ver with latest ver, 4: New ver with pre-ver, 5: new pre-ver with pre-ver, 6: New required release
	@SuppressWarnings("SameParameterValue")
    private static int compareVersions(String modVer, String latestVer) {
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

	private static void displayText(int i) {
		switch (i) {
			case 1:
				Utils.addChatWithLine("You are using pre-release.\n§cPre-release is not perfect. There may be bugs.");
				return;
			case 2:
				Utils.addChatWithURL(Utils.LINE + "\nA new release is available. ", URL_TEXT, URL, SHOW_URL_TEXT, "\n§rCurrent version: " + ZombiesAddon.VERSION + "\nLatest version: " + latestVer + "\n" + Utils.LINE);
				break;
			case 3:
				if (!ZombiesAddonConfig.isCheckPreRelease()) return;
				Utils.addChatWithURL(Utils.LINE + "\nA new pre-release is available. ", URL_TEXT, URL, SHOW_URL_TEXT, "\n§rCurrent version: " + ZombiesAddon.VERSION + "\nLatest version: " + latestVer + "\n§cPre-release is not perfect. There may be bugs.\n§rNote: You can set hide this message in config.\n" + Utils.LINE);
				break;
			case 4:
				Utils.addChatWithURL(Utils.LINE + "\nA new release is available.\nYou are using pre-release.\nPlease update. ", URL_TEXT, URL, SHOW_URL_TEXT, "\n§rCurrent version: " + ZombiesAddon.VERSION + "\nLatest version: " + latestVer + "\n" + Utils.LINE);
				MinecraftForge.EVENT_BUS.register(new UpdateChecker());
				break;
			case 5:
				Utils.addChatWithURL(Utils.LINE + "\nA new pre-release is available. ", URL_TEXT, URL, SHOW_URL_TEXT, "\n§rCurrent version: " + ZombiesAddon.VERSION + "\nLatest version: " + latestVer + "\n§cPre-release is not perfect. There may be bugs.\n" + Utils.LINE);
				MinecraftForge.EVENT_BUS.register(new UpdateChecker());
				break;
			case 6:
				Utils.addChatWithURL(Utils.LINE + "\n§cRequired updates released. " , URL_TEXT, URL, SHOW_URL_TEXT, "\n§r§cUPDATE NOW.\n§c§lThe game ends after 10 seconds.\n" + Utils.LINE);
				ClientCrash.setUpdate();
				MinecraftForge.EVENT_BUS.register(new ClientCrash());
				break;
			default:
				return;
		}
		String[] strArray = log.split("\n");
		StringBuilder newLog = new StringBuilder();
		for (String str : strArray) newLog.append("\n- ").append(str);
		Utils.addChatWithLine("Update log" + newLog);
	}
}