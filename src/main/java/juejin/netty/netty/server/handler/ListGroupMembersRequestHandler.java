package juejin.netty.netty.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import juejin.netty.netty.protocol.packet.request.ListGroupMembersRequestPacket;
import juejin.netty.netty.protocol.packet.response.ListGroupMembersResponsePacket;
import juejin.netty.netty.session.SessionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neptune
 * @create 2018 11 27 3:42 PM
 */
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

    private ListGroupMembersRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket listGroupMembersRequestPacket) throws Exception {
        // 1. 获取群的 ChannelGroup
        String groupId = listGroupMembersRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        // 2. 遍历群成员的 channel，对应的 session，构造群成员的信息
        List<String> userNameList = new ArrayList<>();
        if(channelGroup == null) {
            ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();
            responsePacket.setSuccess(false);
            responsePacket.setReason("查询出错，没有找到该群组");
            ctx.channel().writeAndFlush(responsePacket);
            return;
        }
        for (Channel channel : channelGroup) {
            String userName = SessionUtil.getSession(channel).getUserName();
            userNameList.add(userName);
        }

        ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);
        responsePacket.setUserNameList(userNameList);
//        ctx.channel().writeAndFlush(responsePacket);
        ctx.writeAndFlush(responsePacket);
        System.out.println("执行了查询群聊成员的请求");
        // System.out.println("当前参加群聊的成员有: " + userNameList);
    }
}
