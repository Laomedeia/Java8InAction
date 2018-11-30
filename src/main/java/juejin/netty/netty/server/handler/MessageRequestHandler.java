package juejin.netty.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejin.netty.netty.protocol.packet.request.MessageRequestPacket;
import juejin.netty.netty.protocol.packet.response.MessageResponsePacket;

import java.util.Date;

/**
 * server 端收到 message request 后处理的 handler
 * @author neptune
 * @create 2018 11 22 6:21 PM
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
        messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");

        // ctx.channel().writeAndFlush(messageResponsePacket);
        ctx.writeAndFlush(messageResponsePacket);
    }
}
