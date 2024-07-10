//Code in Zombies AutoSplits by tahmid-23

package kr.hahaha98757.zombiesaddon.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import kr.hahaha98757.zombiesaddon.packet.PacketInterceptor;
import net.minecraft.network.Packet;

import java.util.Objects;

public class NettyPacketHandler extends SimpleChannelInboundHandler<Packet<?>> {

	private final Iterable<PacketInterceptor> interceptors;

	public NettyPacketHandler(Iterable<PacketInterceptor> interceptors) {
		this.interceptors = Objects.requireNonNull(interceptors, "interceptors");
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Packet<?> msg) {
		for (PacketInterceptor interceptor : interceptors) {
			interceptor.intercept(msg);
		}

		ctx.fireChannelRead(msg);
	}

}