package juejin.netty.netty;

import java.util.UUID;

/**
 * @author neptune
 * @create 2018 11 26 6:20 PM
 */
public class IDUtil {
    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
