package java8InAction.mytest;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author neptune
 * @create 2018 05 08 上午9:42
 */
public class TreeMapTest {

    public static void main(String[] args) {
        Map<String, String> map = new TreeMap<>();
        map.put("北京","北京");
        map.put("四川","四川");
        map.put("上海","上海");
        map.put("湖南","湖南");
        map.put("广东","广东");

        map.keySet().forEach(k-> System.out.println(k));
    }

}
