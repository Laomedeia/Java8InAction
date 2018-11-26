package juejin.netty.netty.protocol;

import lombok.Data;
import lombok.NoArgsConstructor;

import static juejin.netty.netty.protocol.Command.MESSAGE_REQUEST;
import static juejin.netty.netty.protocol.Command.MESSAGE_TO_USER_REQUEST;

/**
 * 服务端转发用户信息 packet
 * @author neptune
 * @create 2018 11 26 10:52 AM
 */
@Data
public class MessageToUserRequestPacket extends Packet {

    private String toUserId;
    private String message;

    public MessageToUserRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_TO_USER_REQUEST;
    }
}
