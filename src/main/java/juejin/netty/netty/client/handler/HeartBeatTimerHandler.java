package juejin.netty.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import juejin.netty.netty.protocol.packet.request.HeartBeatRequestPacket;

import java.util.concurrent.TimeUnit;

/**
 * 客户端需定时发送的心跳包 handler
 * @author neptune
 * @create 2018 11 29 12:10 PM
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    // 空闲检测时间的三分之一. 在生产环境中，我们的发送心跳间隔时间和空闲检测时间可以略长一些，可以设置为几分钟级别. 具体应用可以具体对待.
    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);

        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        // ctx.executor() 返回的是当前的 channel 绑定的 NIO 线程. schedule() 类似延时任务机制，隔一段时间之后执行一个任务
        ctx.executor().schedule(() -> {

            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }

        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
