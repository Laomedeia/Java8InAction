package juejin.netty.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejin.netty.netty.protocol.packet.response.QuitGroupResponsePacket;

/**
 * @author neptune
 * @create 2018 11 27 3:28 PM
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        if (quitGroupResponsePacket.isSuccess()) {
            System.out.println("退群[" + quitGroupResponsePacket.getGroupId() + "]成功!");
        } else {
            System.err.println("退群[" + quitGroupResponsePacket.getGroupId() + "]失败，原因为：" + quitGroupResponsePacket.getReason());
        }
    }
}
