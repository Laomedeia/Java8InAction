package juejin.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import juejin.netty.netty.client.NettyClient;
import juejin.netty.netty.codec.PacketCodeC;
import juejin.netty.netty.protocol.packet.response.LoginResponsePacket;
import juejin.netty.netty.protocol.packet.response.MessageResponsePacket;
import juejin.netty.netty.protocol.packet.Packet;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author neptune
 * @create 2018 11 20 11:11 PM
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {


    private NettyClient client;

    public ClientHandler(NettyClient client) {
        this.client = client;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        // 1. 获取数据
//        ByteBuf hiMessage = getByteBuf(ctx);
//        // 2. 写数据，发送服务端有新的客户端连接
//        ctx.channel().writeAndFlush(hiMessage);
        System.out.println(new Date() + ": 客户端开始登录");

//        // 1. 创建登录对象packet
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        loginRequestPacket.setUserId(UUID.randomUUID().toString());
//        loginRequestPacket.setUsername("admin");
//        loginRequestPacket.setPassword("132423424");
//        ByteBuf requestLoginMessage = PacketCodeC.getInstance().encode(ctx.alloc(), loginRequestPacket);    // ctx.alloc() 获取的就是与当前连接相关的 ByteBuf 分配器
//        // 2. 发送登录请求
//        ctx.channel().writeAndFlush(requestLoginMessage);
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("掉线了...");
        //使用过程中断线重连
        super.channelInactive(ctx);
        client.doConnect(NettyClient.MAX_RETRY);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf byteBuf = (ByteBuf) msg;
//        System.out.println(new Date() + ": 客户端收到服务端信息 -> " + byteBuf.toString(Charset.forName("utf-8")));
        // Netty 使用了堆外内存，而堆外内存是不被 jvm 直接管理的，也就是说申请到的内存无法被垃圾回收器直接回收，所以需要我们手动回收。有点类似于c语言里面，申请到的内存必须手工释放，否则会造成内存泄漏。
        // 在一个函数体里面，只要增加了引用计数（包括 ByteBuf 的创建和手动调用 retain() 方法），就必须调用 release() 方法
        // 注意 slice(), duplicate() 和 copy() 的用法和区别. 本例没有涉及手动release，一旦手动申请了内存，一定要释放
        // byteBuf.release();

        // 获取服务器响应登录 response packet
        ByteBuf serverReturnMsg = (ByteBuf) msg;
        Packet packet = PacketCodeC.getInstance().decode(serverReturnMsg);
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if (loginResponsePacket.isSuccess()) {
                LoginUtil.markAsLogin(ctx.channel());
                System.out.println(new Date() + ": 客户端登录成功");
            } else {
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        } else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        }
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        // 1. 获取二进制抽象 ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();
        // 2. 准备数据，指定字符串的字符集为 utf-8
        String requestStr = "你好，我是来自"+ctx.channel().remoteAddress()+"的客户端!";
        byte[] bytes = requestStr.getBytes(Charset.forName("utf-8"));
        // 3. 填充数据到 ByteBuf
        buffer.writeBytes(bytes);
        return buffer;
    }

}
