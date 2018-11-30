package juejin.netty.netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import juejin.netty.netty.protocol.packet.request.QuitGroupRequestPacket;
import juejin.netty.netty.protocol.packet.response.QuitGroupResponsePacket;
import juejin.netty.netty.session.SessionUtil;

/**
 * @author neptune
 * @create 2018 11 27 3:08 PM
 */
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    private QuitGroupRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket requestPacket) {
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移除
        String groupId = requestPacket.getGroupId();
        String userId = SessionUtil.getSession(ctx.channel()).getUserId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        // 2. 构造退群响应发送给客户端
        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();
        responsePacket.setGroupId(requestPacket.getGroupId());
        responsePacket.setSuccess(true);
        // ctx.channel().writeAndFlush(responsePacket);
        ctx.writeAndFlush(responsePacket);
        System.out.print("用户 "+userId+" 已退群 ["+groupId+"]");

    }
}
