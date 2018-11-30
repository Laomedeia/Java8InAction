package juejin.netty.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import juejin.netty.netty.codec.MyProtocolDecoder;
import juejin.netty.netty.client.handler.*;
import juejin.netty.netty.codec.PacketDecoder;
import juejin.netty.netty.codec.PacketEncoder;
import juejin.netty.netty.client.console.ConsoleCommandManager;
import juejin.netty.netty.client.console.LoginConsoleCommand;
import juejin.netty.netty.server.handler.*;
import juejin.netty.netty.session.SessionUtil;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author neptune
 * @create 2018 11 20 3:24 PM
 */
public class NettyClient {

    private Channel channel;
    private Bootstrap bootstrap;
    //    private Thread sendDataThread;
    public static final int MAX_RETRY = 5;

    /**
     * 初始化客户端
     *
     * @throws InterruptedException
     */
    public void init() throws InterruptedException {
        bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                // channelHandler 分为 inBound 和 outBound 两种类型的接口，分别是处理数据读与数据写的逻辑，可与 tcp 协议栈联系起来。inBoundHandler 的执行顺序与我们实际的添加顺序相同，而 outBoundHandler 则相反。
                .handler(new ChannelInitializer<SocketChannel>() {  // IO 处理逻辑
                    @Override
                    protected void initChannel(SocketChannel ch) {
//                        ch.pipeline().addLast(new IdleStateHandler(0, 0, 5));
                        // 空闲检测
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        ch.pipeline().addLast(new MyProtocolDecoder());   // 自定义netty的拆包粘包。使用基于长度域拆包器 LengthFieldBasedFrameDecoder 来拆解我们的自定义协议（第一个参数指的是数据包的最大长度，第二个参数指的是长度域的偏移量，第三个参数指的是长度域的长度）
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new LogoutResponseHandler());
                        ch.pipeline().addLast(new GroupMessageResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new QuitGroupResponseHandler());
                        ch.pipeline().addLast(new JoinGroupResponseHandler());
                        ch.pipeline().addLast(new ListGroupMembersResponseHandler());
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                        // 心跳定时器
                        ch.pipeline().addLast(new HeartBeatTimerHandler());

//                        ch.pipeline().addLast(new StringEncoder());
//                        ch.pipeline().addLast(new ClientHandler(NettyClient.this));   // 多个处理器可以处理同一个数据, 可通过 fireChannelRead(msg) 往下一个处理器传递
                    }
                });
        bootstrap.attr(AttributeKey.newInstance("clientName"), "nettyClient");  // 为NioSocketChannel绑定自定义属性，然后我们可以通过channel.attr()取出这个属性
        doConnect(MAX_RETRY);
    }

    /**
     * 持续向服务端发送数据
     *
     * @throws InterruptedException
     */
    public void sendLoopData() throws InterruptedException {
        while (true) {
            if (channel != null && channel.isActive()) {
                channel.writeAndFlush(new Date() + ": hello world, human!");
            }
            Thread.sleep(2000);
        }
    }

    /**
     * 向服务端发出数据
     *
     * @throws InterruptedException
     */
    public void sendData() {
        if (channel != null && channel.isActive()) {
            channel.writeAndFlush(new Date() + ": 你好服务器，我是一个新的连接!");
        }
    }

    /**
     * 建立连接并实现断线重连. (通常情况下，连接建立失败不会立即重新连接，而是会通过一个指数退避的方式，比如每隔 1 秒、2 秒、4 秒、8 秒，以 2 的幂次来建立连接，然后到达一定次数之后就放弃连接，接下来我们就来实现一下这段逻辑，我们默认重试 5 次)
     *
     * @param retry 断线重连次数
     * @throws InterruptedException
     */
    public void doConnect(int retry) throws InterruptedException {
        if (channel != null && channel.isActive()) {
            return;
        }

        ChannelFuture future = bootstrap.connect("127.0.0.1", 8600);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    channel = channelFuture.channel();
                    System.out.println("Connect to server successfully!");

                    // 连接成功之后，启动控制台线程
                    startConsoleThread(channel);

                } else if (retry == 0) {
                    System.err.println("重试次数已用完，放弃连接！");
                } else {
                    System.out.println("Failed to connect to server, try connect");
                    // 第几次重连
                    int order = (MAX_RETRY - retry) + 1;
                    // 本次重连的间隔。指数递增
                    int delay = 1 << order;
                    System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                    System.out.println("本次重连的间隔: " + delay + "s");
                    bootstrap.config().group().schedule(() -> {
                        try {
                            doConnect(retry - 1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }, delay, TimeUnit
                            .SECONDS);
//                    channelFuture.channel().eventLoop().schedule(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                doConnect(retry - 1);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }, delay, TimeUnit.SECONDS);
                }
            }

        });
    }

    /**
     * 启动控制台线程
     *
     * @param channel
     */
    private static void startConsoleThread(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
//                    System.out.println("输入用户名登录: ");
//                    String username = sc.nextLine();
//                    loginRequestPacket.setUserName(username);
//                    channel.writeAndFlush(loginRequestPacket);
//                    waitForLoginResponse();
                    loginConsoleCommand.exec(scanner, channel);
                } else {
//                    System.out.println("请输入[用户id + ' ' + 消息] 发送给对方消息");
//                    String toUserId = sc.next();
//                    String message = sc.next();
//                    channel.writeAndFlush(new MessageToUserRequestPacket(toUserId, message));
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NettyClient nettyClient = new NettyClient();
        nettyClient.init();
//        nettyClient.sendData();
//        nettyClient.sendLoopData();

    }
}
