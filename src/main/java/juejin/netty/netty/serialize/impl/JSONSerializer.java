package juejin.netty.netty.serialize.impl;

import com.alibaba.fastjson.JSON;
import juejin.netty.netty.serialize.Serializer;
import juejin.netty.netty.serialize.SerializerAlgorithm;

/**
 * @author neptune
 * @create 2018 11 21 6:33 PM
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
