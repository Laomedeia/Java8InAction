package java8InAction.mytest;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author neptune
 * @create 2018 03 15 下午12:15
 */
public class ConcurrentMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("key1", 2);
        map.put("key2", 8);
        map.put("key3", 5);
        Optional<Integer> maxValue =
                Optional.of(map.reduceValues(1, Integer::max));
        maxValue.ifPresent(System.out::println);
        System.out.println(map.mappingCount());     //尽量用mappingCount而不是size来计数

    }
}
