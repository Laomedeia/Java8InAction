package juejin.netty.netty.protocol.packet.request;

import juejin.netty.netty.protocol.packet.Packet;
import lombok.Data;

import static juejin.netty.netty.protocol.Command.MESSAGE_REQUEST;

/**
 * @author neptune
 * @create 2018 11 22 12:33 PM
 */
@Data
public class MessageRequestPacket extends Packet {
    private String message;
    public MessageRequestPacket(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
