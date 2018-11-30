package juejin.netty.netty.protocol.packet.request;

import juejin.netty.netty.protocol.packet.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static juejin.netty.netty.protocol.Command.CREATE_GROUP_REQUEST;

/**
 * 创建群组消息数据包
 * @author neptune
 * @create 2018 11 26 5:37 PM
 */
@Data
@NoArgsConstructor
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
