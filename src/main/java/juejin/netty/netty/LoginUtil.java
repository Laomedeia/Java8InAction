package juejin.netty.netty;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * 用户登录相关util
 * @author neptune
 * @create 2018 11 22 3:19 PM
 */
public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.IS_LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.IS_LOGIN);
        return loginAttr.get() != null;
    }
}
