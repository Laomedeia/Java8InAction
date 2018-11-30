package juejin.netty.netty.protocol.packet.request;

import juejin.netty.netty.protocol.packet.Packet;
import lombok.Data;

import static juejin.netty.netty.protocol.Command.JOIN_GROUP_REQUEST;

/**
 * @author neptune
 * @create 2018 11 27 11:26 AM
 */
@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }
}
