package juejin.netty.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejin.netty.netty.protocol.MessageFromUserResponsePacket;
import juejin.netty.netty.protocol.MessageResponsePacket;

import java.util.Date;

/**
 * @author neptune
 * @create 2018 11 23 10:06 AM
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageFromUserResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageFromUserResponsePacket messageFromUserResponsePacket) {
        String fromUserId = messageFromUserResponsePacket.getFromUserId();
        String fromUserName = messageFromUserResponsePacket.getFromUserName();
        System.out.println(fromUserId + ":" + fromUserName + " -> " + messageFromUserResponsePacket
                .getMessage());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + " 客户端已经断开连接，请重新尝试登录.");
        super.channelInactive(ctx);
    }
}