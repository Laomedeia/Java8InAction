package juejin.netty.netty.protocol.packet.request;

import juejin.netty.netty.protocol.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;

import static juejin.netty.netty.protocol.Command.GROUP_MESSAGE_REQUEST;

/**
 * @author neptune
 * @create 2018 11 28 11:55 AM
 */
@Data
@AllArgsConstructor
public class GroupMessageRequestPacket extends Packet {

    private String toGroupId;
    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }

}
