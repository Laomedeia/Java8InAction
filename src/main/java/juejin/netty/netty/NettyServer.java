package juejin.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import juejin.netty.netty.codec.PacketDecoder;
import juejin.netty.netty.codec.PacketEncoder;
import juejin.netty.netty.handler.*;

/**
 * @author neptune
 * @create 2018 11 20 11:00 AM
 */
public class NettyServer {

    /**
     * 查找端口并绑定
     *
     * @param serverBootstrap
     * @param port
     */
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
//        serverBootstrap.attr(AttributeKey.newInstance("serverName"), "nettyServer");    // 自定义服务端的 channel 相关属性名称
//        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024); // 服务端channel设置一些属性。so_backlog：表示系统用于临时存放已完成三次握手的请求的队列的最大长度，如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
        serverBootstrap
                .group(boss, worker)
//                .childAttr(AttributeKey.newInstance("clientKey"), "clientValue")      // 自定义每一条连接属性
                .channel(NioServerSocketChannel.class)
                // 给每条连接设置一些tcp相关属性.(SO_KEEPALIVE表示是否开启TCP底层心跳机制，true为开启 / TCP_NODELAY表示是否开启Nagle算法，true表示关闭，false表示开启，通俗地说，如果要求高实时性，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启。)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
//                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
//                        ch.pipeline().addLast(new LifeCyCleTestHandler());
                        ch.pipeline().addLast(new IdleStateHandler(10, 0, 0));
                        ch.pipeline().addLast(new MyProtocolDecoder());   // 自定义netty的拆包粘包。使用基于长度域拆包器 LengthFieldBasedFrameDecoder 来拆解我们的自定义协议（第一个参数指的是数据包的最大长度，第二个参数指的是长度域的偏移量，第三个参数指的是长度域的长度）
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new AuthHandler());
                        ch.pipeline().addLast(new MessageToUserRequestHandler());
                        ch.pipeline().addLast(new PacketEncoder());

//                        ch.pipeline().addLast(new ServerHandler());
//                        ch.pipeline().addLast(new StringDecoder());
//                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
//                            @Override
//                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
//                                System.out.println(msg);
//                            }
//                        });
                    }
                });
        bind(serverBootstrap, 8600);
    }
}
