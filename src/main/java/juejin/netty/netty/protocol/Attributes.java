package juejin.netty.netty.protocol;

import io.netty.util.AttributeKey;

/**
 * @author neptune
 * @create 2018 11 22 3:17 PM
 */
public interface Attributes {

    AttributeKey<Boolean> IS_LOGIN = AttributeKey.newInstance("login");
}
