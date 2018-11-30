package juejin.netty.netty.protocol.packet.request;

import juejin.netty.netty.protocol.packet.Packet;
import lombok.Data;

import static juejin.netty.netty.protocol.Command.QUIT_GROUP_REQUEST;

/**
 * @author neptune
 * @create 2018 11 27 3:10 PM
 */
@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_REQUEST;
    }
}
