package juejin.netty.netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejin.netty.netty.protocol.packet.request.LogoutRequestPacket;
import juejin.netty.netty.protocol.packet.response.LogoutResponsePacket;
import juejin.netty.netty.session.SessionUtil;

/**
 * @author neptune
 * @create 2018 11 26 6:21 PM
 */
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    private LogoutRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) {
        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        // ctx.channel().writeAndFlush(logoutResponsePacket);
        ctx.writeAndFlush(logoutResponsePacket);
    }
}