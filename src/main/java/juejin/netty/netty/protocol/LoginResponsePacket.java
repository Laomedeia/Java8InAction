package juejin.netty.netty.protocol;

import lombok.Data;

import static juejin.netty.netty.protocol.Command.LOGIN_RESPONSE;

/**
 * @author neptune
 * @create 2018 11 22 11:35 AM
 */
@Data
public class LoginResponsePacket extends Packet{

    private String userId;

    private String userName;

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
