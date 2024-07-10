//Code in Zombies AutoSplits by tahmid-23

package kr.hahaha98757.zombiesaddon.packet;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.listeners.EventListener;
import kr.hahaha98757.zombiesaddon.listeners.WaveDelaysListener;
import kr.hahaha98757.zombiesaddon.splitter.LiveSplitSplitter;
import kr.hahaha98757.zombiesaddon.splitter.internal.InternalSplitter;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.util.ChatComponentText;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class AutoSplitPacketInterceptor implements PacketInterceptor {

	private final Minecraft minecraft;

	private final Logger logger;

	private static LiveSplitSplitter splitter;

	private boolean AAr10 = false;

	public AutoSplitPacketInterceptor(Minecraft minecraft, Logger logger, LiveSplitSplitter splitter) {
		this.minecraft = Objects.requireNonNull(minecraft, "minecraft");
		this.logger = Objects.requireNonNull(logger, "logger");
		AutoSplitPacketInterceptor.splitter = Objects.requireNonNull(splitter, "splitter");
	}

	@Override
	public void intercept(Packet<?> packet) {
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}

		if (!(packet instanceof S29PacketSoundEffect)) {
			return;
		}
		S29PacketSoundEffect soundEffect = (S29PacketSoundEffect) packet;

		if (soundEffect.getSoundName().equals("mob.enderdragon.end")) {
			EventListener.gameOver();
		}
		if (soundEffect.getSoundName().equals("mob.wither.spawn")) {
			WaveDelaysListener.stop = false;
			WaveDelaysListener.escape = false;
		}

		if (soundEffect.getSoundName().equals("mob.wither.spawn")
				|| soundEffect.getSoundName().equals("mob.enderdragon.end")) {
			AAr10 = false;
		}

		if (soundEffect.getSoundName().equals("mob.wither.spawn")
				|| soundEffect.getSoundName().equals("mob.guardian.curse") && !AAr10) {
			if (soundEffect.getSoundName().equals("mob.guardian.curse")) {
				AAr10 = true;
			}

			try {
				splitter.startOrSplit();
			} catch (Exception e) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(
						new ChatComponentText("\u00A7eAuto Splits: \u00A7cFailed to run timer."));
			}
		} else if (soundEffect.getSoundName().equals("mob.enderdragon.end")) {
			InternalSplitter.instance.stop();
		}
	}

	public void setSplitter(LiveSplitSplitter splitter) {
		AutoSplitPacketInterceptor.splitter = Objects.requireNonNull(splitter, "splitter");
	}

	public static void runTimer() {
		splitter.startOrSplit();
	}
}