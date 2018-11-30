package juejin.netty.netty.protocol.packet.response;

import juejin.netty.netty.protocol.packet.Packet;
import lombok.Data;

import java.util.List;

import static juejin.netty.netty.protocol.Command.LIST_GROUP_RESPONSE;

/**
 * @author neptune
 * @create 2018 11 27 3:48 PM
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<String> userNameList;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_RESPONSE;
    }
}
