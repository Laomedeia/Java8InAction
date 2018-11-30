package juejin.netty.netty.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 控制台指令接口
 * @author neptune
 * @create 2018 11 26 5:26 PM
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);
}
