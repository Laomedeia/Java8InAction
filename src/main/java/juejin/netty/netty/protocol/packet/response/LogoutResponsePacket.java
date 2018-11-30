package juejin.netty.netty.protocol.packet.response;

import juejin.netty.netty.protocol.packet.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static juejin.netty.netty.protocol.Command.LOGOUT_RESPONSE;

/**
 * @author neptune
 * @create 2018 11 26 6:22 PM
 */
@Data
@NoArgsConstructor
public class LogoutResponsePacket extends Packet {

    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}
