package juejin.netty.netty.protocol;

import lombok.Data;

import static juejin.netty.netty.protocol.Command.MESSAGE_FROM_USER_RESPONSE;
import static juejin.netty.netty.protocol.Command.MESSAGE_RESPONSE;

/**
 * @author neptune
 * @create 2018 11 26 11:03 AM
 */
@Data
public class MessageFromUserResponsePacket extends Packet {

    private String fromUserId;
    private String fromUserName;
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_FROM_USER_RESPONSE;
    }
}
