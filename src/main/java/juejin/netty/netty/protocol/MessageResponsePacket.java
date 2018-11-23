package juejin.netty.netty.protocol;

import lombok.Data;

import static juejin.netty.netty.protocol.Command.MESSAGE_RESPONSE;

/**
 * @author neptune
 * @create 2018 11 22 12:33 PM
 */
@Data
public class MessageResponsePacket extends Packet {
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
