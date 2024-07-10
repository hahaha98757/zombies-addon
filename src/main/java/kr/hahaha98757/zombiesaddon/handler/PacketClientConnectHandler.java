//Code in Zombies AutoSplits by tahmid-23

package kr.hahaha98757.zombiesaddon.handler;

import io.netty.channel.ChannelHandler;
import kr.hahaha98757.zombiesaddon.netty.NettyPacketHandler;
import kr.hahaha98757.zombiesaddon.packet.PacketInterceptor;

import java.util.Objects;

public class PacketClientConnectHandler extends ClientConnectHandler {

	private final Iterable<PacketInterceptor> interceptors;

	public PacketClientConnectHandler(Iterable<PacketInterceptor> interceptors) {
		this.interceptors = Objects.requireNonNull(interceptors, "interceptors");
	}

	@Override
	protected ChannelHandler createChannelHandler() {
		return new NettyPacketHandler(interceptors);
	}

}