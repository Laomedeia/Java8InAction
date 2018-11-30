package juejin.netty.netty.client.console;

import io.netty.channel.Channel;
import juejin.netty.netty.protocol.packet.request.MessageToUserRequestPacket;

import java.util.Scanner;

/**
 * @author neptune
 * @create 2018 11 26 5:31 PM
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个用户：");
        String toUserId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new MessageToUserRequestPacket(toUserId, message));
    }
}
