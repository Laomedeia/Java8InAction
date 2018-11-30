package juejin.netty.netty.protocol.packet.response;

import juejin.netty.netty.protocol.packet.Packet;
import lombok.Data;

import java.util.List;

import static juejin.netty.netty.protocol.Command.JOIN_GROUP_RESPONSE;

/**
 * @author neptune
 * @create 2018 11 27 11:36 AM
 */
@Data
public class JoinGroupResponsePacket extends Packet {

    private boolean success;
    private String reason;
    private String groupId;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPONSE;
    }
}
