package juejin.netty.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import juejin.netty.netty.serialize.impl.JSONSerializer;
import juejin.netty.netty.serialize.Serializer;

import java.util.HashMap;
import java.util.Map;

import static juejin.netty.netty.protocol.Command.*;

/**
 * 可进行(编码/解码)转换二进制的数据包对象
 * @author neptune
 * @create 2018 11 21 6:41 PM
 */
public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x12345678;
    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;
    private static PacketCodeC packetCodeC;

    private PacketCodeC(){
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(MESSAGE_TO_USER_REQUEST, MessageToUserRequestPacket.class);
        packetTypeMap.put(MESSAGE_FROM_USER_RESPONSE, MessageFromUserResponsePacket.class);
        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    // 静态内部类单例模式
    public static PacketCodeC getInstance() {
        return Instance.sInstance;
    }

    private static class Instance {
        private static final PacketCodeC sInstance = new PacketCodeC();
    }


    // packet编码二进制
//    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        // 1. 创建 ByteBuf 对象 (也可以通过ByteBufAllocator.DEFAULT.ioBuffer()创建一个 ByteBuf，这里我们调用 Netty 的 ByteBuf 分配器来创建，ioBuffer() 方法会返回适配 io 读写相关的内存，它会尽可能创建一个直接内存，直接内存可以理解为不受 jvm 堆管理的内存空间，写到 IO 缓冲区的效果更高)
//        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        // 2. 序列化 Java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    // byteBuf二进制解码成packet
    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }

}
