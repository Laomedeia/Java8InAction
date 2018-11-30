package juejin.netty.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import juejin.netty.netty.protocol.packet.Packet;

/**
 * 编码message对象成二进制
 * 基于 MessageToByteEncoder，我们可以实现自定义编码，而不用关心 ByteBuf 的创建，不用每次向对端写 Java 对象都进行一次编码。
 * @author neptune
 * @create 2018 11 22 6:31 PM
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodeC.getInstance().encode(out, msg);
    }
}
