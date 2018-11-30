package juejin.netty.netty.protocol.packet.response;

import juejin.netty.netty.protocol.packet.Packet;

import static juejin.netty.netty.protocol.Command.HEARTBEAT_RESPONSE;

/**
 * @author neptune
 * @create 2018 11 29 12:14 PM
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
