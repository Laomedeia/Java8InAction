package juejin.netty.netty.client.console;

import io.netty.channel.Channel;
import juejin.netty.netty.protocol.packet.request.GroupMessageRequestPacket;

import java.util.Scanner;

/**
 * @author neptune
 * @create 2018 11 28 3:08 PM
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个群组：");
        String toGroupId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));

    }
}