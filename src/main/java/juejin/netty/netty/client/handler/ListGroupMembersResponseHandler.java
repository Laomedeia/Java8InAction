package juejin.netty.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejin.netty.netty.protocol.packet.response.ListGroupMembersResponsePacket;

/**
 * @author neptune
 * @create 2018 11 27 3:52 PM
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket listGroupMembersResponsePacket) throws Exception {
        if (listGroupMembersResponsePacket.isSuccess()) {
            System.out.println("当前参与群["+listGroupMembersResponsePacket.getGroupId()+"]的成员有: " + listGroupMembersResponsePacket.getUserNameList());
        } else {
            System.err.println("查询群聊成员失败，原因为：" + listGroupMembersResponsePacket.getReason());
        }
    }
}
