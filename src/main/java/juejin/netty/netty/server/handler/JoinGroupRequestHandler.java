package juejin.netty.netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import juejin.netty.netty.protocol.packet.request.JoinGroupRequestPacket;
import juejin.netty.netty.protocol.packet.response.JoinGroupResponsePacket;
import juejin.netty.netty.session.SessionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neptune
 * @create 2018 11 27 12:37 PM
 */
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    private JoinGroupRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        String groupId = joinGroupRequestPacket.getGroupId();
        String userId = SessionUtil.getSession(ctx.channel()).getUserId();
        List<String> userNameList = new ArrayList<>();

        // 判断 group 是否存在，存在则加入该用户channel
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        // 返回 response
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        joinGroupResponsePacket.setSuccess(true);
        joinGroupResponsePacket.setGroupId(groupId);
//        ctx.channel().writeAndFlush(joinGroupResponsePacket);
        ctx.writeAndFlush(joinGroupResponsePacket);
        System.out.print("用户 "+userId+" 加群 ["+groupId+"] 成功");
    }
}
