package juejin.netty.netty.client.console;

import io.netty.channel.Channel;
import juejin.netty.netty.protocol.packet.request.LogoutRequestPacket;

import java.util.Scanner;

/**
 * @author neptune
 * @create 2018 11 26 5:31 PM
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
