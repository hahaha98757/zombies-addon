//Code in Zombies AutoSplits by tahmid-23

package kr.hahaha98757.zombiesaddon.packet;

import net.minecraft.network.Packet;

import java.util.function.Consumer;

@FunctionalInterface
public interface PacketInterceptor extends Consumer<Packet<?>> {

	void intercept(Packet<?> packet);

	default void accept(Packet<?> packet) {
		intercept(packet);
	}

}