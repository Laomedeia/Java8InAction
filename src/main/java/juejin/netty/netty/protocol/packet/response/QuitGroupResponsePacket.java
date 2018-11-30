package juejin.netty.netty.protocol.packet.response;

import juejin.netty.netty.protocol.packet.Packet;
import lombok.Data;

import static juejin.netty.netty.protocol.Command.QUIT_GROUP_REQUEST;
import static juejin.netty.netty.protocol.Command.QUIT_GROUP_RESPONSE;

/**
 * @author neptune
 * @create 2018 11 27 3:10 PM
 */
@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_RESPONSE;
    }
}
