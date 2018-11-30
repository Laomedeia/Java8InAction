package workTest;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.collections4.map.PassiveExpiringMap;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 测试 expiringMap (能定时过期key的数据结构)
 * @author neptune
 * @create 2018 11 20 11:33 AM
 */
public class ExpiringMapTest {

    /**
     * 测试JodahExpiringMap
     */
    public void testJodahExpiringMap() {
        Map<String, String> map = ExpiringMap.builder()
                .maxSize(500)
                .expiration(5, TimeUnit.SECONDS)
                .expirationPolicy(ExpirationPolicy.CREATED)    // 过期策略。创建后设置过期 ExpirationPolicy.CREATED or 访问后设置过期 ExpirationPolicy.ACCESSED
                .build();
        // Expires after 30 seconds or as soon as a 124th element is added and this is the next one to expire based on the expiration policy
        map.put("connection", "yes from Jodah");
        new Thread(() -> {
            while(true) {
                try {
                    System.out.println(map.get("connection"));
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

        /**
         * 测试apache common passiveExpiringMap
         */
    public void testApacheCommonPassiveExpiringMap() {
        // 默认构造
//        PassiveExpiringMap passiveExpiringMap = new PassiveExpiringMap(5000);
        // 按策略构造
        PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy constantTimeToLiveExpirationPolicy = new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy(5000);
        Map<String, String> map = new HashedMap<>();
        map.put("mykey", "computer");
        PassiveExpiringMap passiveExpiringMap = new PassiveExpiringMap(constantTimeToLiveExpirationPolicy, map);
        passiveExpiringMap.put("sskey", "qooco");
        new Thread(() -> {
            while(true) {
                try {
                    System.out.println(passiveExpiringMap.get("sskey"));
                    System.out.println(passiveExpiringMap.get("mykey"));
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        ExpiringMapTest expiringMapTest = new ExpiringMapTest();
//        expiringMapTest.testApacheCommonPassiveExpiringMap();
        expiringMapTest.testJodahExpiringMap();
    }
}
