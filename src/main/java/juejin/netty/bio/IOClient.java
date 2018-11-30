package juejin.netty.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author neptune
 * @create 2018 11 19 6:39 PM
 */
public class IOClient {
    public static void main(String[] args) {

        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        Thread.sleep(3000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();

    }
}
