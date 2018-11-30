package juejin.netty.netty.client.console;

import io.netty.channel.Channel;
import juejin.netty.netty.protocol.packet.request.ListGroupMembersRequestPacket;

import java.util.Scanner;

/**
 * @author neptune
 * @create 2018 11 27 3:33 PM
 */
public class ListGroupMembersConsoleCommand implements  ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();

        System.out.print("输入 groupId，获取群成员列表：");
        String groupId = scanner.next();

        listGroupMembersRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(listGroupMembersRequestPacket);
    }
}
