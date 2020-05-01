package juejin.netty.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import juejin.netty.netty.codec.MyProtocolDecoder;
import juejin.netty.netty.server.handler.*;

/**
 * @author neptune
 * @create 2018 11 20 11:00 AM
 */
public class NettyHttpResponseServer {

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
        try {
        serverBootstrap
                .group(boss, worker)
//                .childAttr(AttributeKey.newInstance("clientKey"), "clientValue")      // 自定义每一条连接属性
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        // 管道添加Handler, HttpServerCodec 是 netty 自己提供的Http解码助手类
                        ch.pipeline().addLast("HttpServerCodec", new HttpServerCodec());
                        ch.pipeline().addLast("customHttpHandler", new CustomHttpHandler());

                    }
                });
        ChannelFuture channelFuture = serverBootstrap.bind(8700).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
        // bind(serverBootstrap, 8600);
    }
}
