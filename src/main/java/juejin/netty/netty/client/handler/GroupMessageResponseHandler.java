package juejin.netty.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejin.netty.netty.protocol.packet.response.GroupMessageResponsePacket;
import juejin.netty.netty.session.UserSession;

/**
 * @author neptune
 * @create 2018 11 28 11:47 AM
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket groupMessageResponsePacket) throws Exception {
        UserSession fromUser = groupMessageResponsePacket.getFromUser();
        String fromGroupId = groupMessageResponsePacket.getFromGroupId();
        String message = groupMessageResponsePacket.getMessage();
        System.out.println("收到群[" + fromGroupId + "]中[" + fromUser + "]发来的消息：" + message);
    }
}