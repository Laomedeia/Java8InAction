package juejin.netty.netty.protocol.packet.response;

import juejin.netty.netty.protocol.packet.Packet;
import juejin.netty.netty.session.UserSession;
import lombok.Data;

import static juejin.netty.netty.protocol.Command.GROUP_MESSAGE_RESPONSE;

/**
 * @author neptune
 * @create 2018 11 28 11:59 AM
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    private String message;

    private String fromGroupId;

    private UserSession fromUser;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
