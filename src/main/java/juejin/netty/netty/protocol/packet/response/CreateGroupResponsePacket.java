package juejin.netty.netty.protocol.packet.response;

/**
 * @author neptune
 * @create 2018 11 26 5:56 PM
 */

import juejin.netty.netty.protocol.packet.Packet;
import lombok.Data;

import java.util.List;

import static juejin.netty.netty.protocol.Command.CREATE_GROUP_RESPONSE;

@Data
public class CreateGroupResponsePacket extends Packet {

    private boolean success;
    private String groupId;
    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
