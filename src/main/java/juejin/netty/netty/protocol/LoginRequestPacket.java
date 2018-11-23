package juejin.netty.netty.protocol;

import lombok.Data;

import static juejin.netty.netty.protocol.Command.LOGIN_REQUEST;

/**
 * @author neptune
 * @create 2018 11 21 6:29 PM
 */
@Data
public class LoginRequestPacket extends Packet{

    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
