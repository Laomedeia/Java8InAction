package juejin.netty.netty.protocol.packet.request;

import juejin.netty.netty.protocol.packet.Packet;

import static juejin.netty.netty.protocol.Command.HEARTBEAT_REQUEST;

/**
 * @author neptune
 * @create 2018 11 29 12:14 PM
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
