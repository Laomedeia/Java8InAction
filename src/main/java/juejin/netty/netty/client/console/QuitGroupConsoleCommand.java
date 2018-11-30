package juejin.netty.netty.client.console;

import io.netty.channel.Channel;
import juejin.netty.netty.protocol.packet.request.QuitGroupRequestPacket;

import java.util.Scanner;

/**
 * 加入群聊指令
 * @author neptune
 * @create 2018 11 27 11:24 AM
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {

    @Override public void exec(Scanner scanner, Channel channel) {
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();
        System.out.print("输入 groupId，退出群聊：");
        String groupId = scanner.next();
        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }

}
