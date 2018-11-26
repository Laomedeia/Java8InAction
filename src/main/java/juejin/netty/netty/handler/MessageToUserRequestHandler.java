package juejin.netty.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejin.netty.netty.protocol.MessageResponsePacket;
import juejin.netty.netty.protocol.MessageToUserRequestPacket;
import juejin.netty.netty.protocol.MessageFromUserResponsePacket;
import juejin.netty.netty.session.SessionUtil;
import juejin.netty.netty.session.UserSession;

/**
 * @author neptune
 * @create 2018 11 26 10:59 AM
 */
public class MessageToUserRequestHandler extends SimpleChannelInboundHandler<MessageToUserRequestPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageToUserRequestPacket messageRequestPacket) throws Exception {
        // 1.拿到消息发送方的会话信息
        UserSession session = SessionUtil.getSession(ctx.channel());

        // 2.通过消息发送方的会话信息构造要发送的消息
        MessageFromUserResponsePacket messageResponsePacket = new MessageFromUserResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

        // 3.拿到消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        // 4.将消息发送给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            messageResponsePacket.setMessage("[" + messageRequestPacket.getToUserId() + "] 不在线，发送失败!");
            ctx.channel().writeAndFlush(messageResponsePacket);
            System.err.println("[" + messageRequestPacket.getToUserId() + "] 不在线，发送失败!");
        }
    }
}
