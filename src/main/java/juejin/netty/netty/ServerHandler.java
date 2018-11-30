package juejin.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import juejin.netty.netty.protocol.packet.request.LoginRequestPacket;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author neptune
 * @create 2018 11 21 3:07 PM
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端建立了一个新的客户端连接...");
        // 回复客户端信息
        System.out.println(new Date() + ": 服务端写出信息 >>> ");
        ByteBuf out = getByteBuf(ctx);
        ctx.channel().writeAndFlush(out);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        // 1. 获取客户端请求登录
//        ByteBuf receiveMsg = (ByteBuf) msg;
//        // 2. 解码
//        Packet packet = PacketCodeC.getInstance().decode(receiveMsg);
//        if(packet != null && packet instanceof LoginRequestPacket) {
//            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
//            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
//
//            // 登录校验
//            if (valid(loginRequestPacket)) {
//                // 校验成功
//                loginResponsePacket.setSuccess(true);
//                System.out.println(new Date() + ": 登录成功!");
//            } else {
//                // 校验失败
//                loginResponsePacket.setSuccess(false);
//                loginResponsePacket.setReason("账号密码校验失败");
//                System.out.println(new Date() + ": 登录失败!!");
//            }
//            // 3. 登录响应
//            ByteBuf responseByteBuf = PacketCodeC.getInstance().encode(ctx.alloc(), loginResponsePacket);
//            ctx.channel().writeAndFlush(responseByteBuf);
//        } else if (packet instanceof MessageRequestPacket) {
//            // 处理消息
//            MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);
//            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
//
//            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
//            messageResponsePacket.setMessage("服务端回复【你发的消息已收到】");
//            ByteBuf responseByteBuf = PacketCodeC.getInstance().encode(ctx.alloc(), messageResponsePacket);
//            ctx.channel().writeAndFlush(responseByteBuf);
//        }

    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "你好，欢迎关注巧口微信公众号!".getBytes(Charset.forName("utf-8"));
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        return buffer;
    }
}