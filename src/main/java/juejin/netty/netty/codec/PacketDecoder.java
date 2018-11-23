package juejin.netty.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import juejin.netty.netty.protocol.PacketCodeC;

import java.util.List;

/**
 * 二进制解码成ByteBuf
 * 基于 ByteToMessageDecoder，我们可以实现自定义解码，而不用关心 ByteBuf 的强转和 解码结果的传递
 * @author neptune
 * @create 2018 11 22 6:54 PM
 */
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) {
        out.add(PacketCodeC.getInstance().decode(in));
    }
}