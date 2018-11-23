package juejin.netty.netty.serialize;

import juejin.netty.netty.serialize.impl.JSONSerializer;

/**
 * @author neptune
 * @create 2018 11 21 6:31 PM
 */
public interface Serializer {

    /**
     * json 序列化
     */

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
