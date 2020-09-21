package concurrent.genOrderNo;

import org.apache.commons.lang3.RandomUtils;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 多线程并发条件下生产不重复的订单号测试
 *
 * 多个实例应用订单号如何区分开呢？以下为思考的大致方向：
 * 使用UUID(在第一次生成订单号时初始化一个)
 * 使用redis记录一个增长ID
 * 使用数据库表维护一个增长ID
 * 应用所在的网络IP
 * 应用所在的端口号
 * 使用第三方算法(雪花算法等等)
 * 使用进程ID(某种程度下是一个可行的方案)
 *
 * 在此我想了下，我们的应用是跑在docker里面，而且每个docker容器内的应用端口都一样，不过网路IP不会存在重复的问题，至于进程也有存在重复的可能，
 * 对于UUID的方式之前吃过亏，总之吧，redis或DB也算是一种比较好的方式，不过独立性较差。。。
 * 同时还有一个因素也很重要，就是所有涉及到订单号生成的应用都是在同一台宿主机(linux实体服务器)上， 所以就目前的系统架构我选用了IP的方式。
 *
 * 代码说明及几点建议：
 * generateOrderNo()方法内不需要加锁，因为AtomicInteger内使用的是CAS自旋转锁(保证可见性的同时也保证原子性,具体的请自行了解)
 * getLocalIpSuffix()方法内不需要对不为null的逻辑加同步锁(双向校验锁，整体是一种安全的单例模式)
 * 本人实现的方式并不是解决问题的唯一方式，具体解决问题需要视当前系统架构具体而论
 * 任何测试都是必要的，我同事在前几次尝试解决这个问题后都没有自测，不测试有损开发专业性！
 *
 * @create 2020 09 16 12:32 下午
 */
public class OrderGen2Test {

    /** 订单号生成 **/
    private static ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");
    private static final AtomicInteger SEQ = new AtomicInteger(1000);
    private static final DateTimeFormatter DF_FMT_PREFIX = DateTimeFormatter.ofPattern("yyMMddHHmmssSS");

    public static String generateOrderNo(){
        LocalDateTime dataTime = LocalDateTime.now(ZONE_ID);
        if(SEQ.intValue()>9990){
            SEQ.getAndSet(1000);
        }
        return  dataTime.format(DF_FMT_PREFIX)+ getLocalIpSuffix()+SEQ.getAndIncrement();
    }

    private volatile static String IP_SUFFIX = null;
    private static String getLocalIpSuffix (){
        if(null != IP_SUFFIX){
            return IP_SUFFIX;
        }
        try {
            synchronized (OrderGen2Test.class){
                if(null != IP_SUFFIX){
                    return IP_SUFFIX;
                }
                InetAddress addr = InetAddress.getLocalHost();
                //  172.17.0.4  172.17.0.199 ,
                String hostAddress = addr.getHostAddress();
                if (null != hostAddress && hostAddress.length() > 4) {
                    String ipSuffix = hostAddress.trim().split("\\.")[3];
                    if (ipSuffix.length() == 2) {
                        IP_SUFFIX = ipSuffix;
                        return IP_SUFFIX;
                    }
                    ipSuffix = "0" + ipSuffix;
                    IP_SUFFIX = ipSuffix.substring(ipSuffix.length() - 2);
                    return IP_SUFFIX;
                }
                IP_SUFFIX = RandomUtils.nextInt(10, 20) + "";
                return IP_SUFFIX;
            }
        }catch (Exception e){
            System.out.println("获取IP失败:"+e.getMessage());
            IP_SUFFIX =  RandomUtils.nextInt(10,20)+"";
            return IP_SUFFIX;
        }
    }


    // 模拟并发测试
    public static void main(String[] args) {
        List<String> orderNos = Collections.synchronizedList(new ArrayList<String>());
        IntStream.range(0,2000).parallel().forEach(i->{
            String orderNumber = generateOrderNo();
            System.out.println(orderNumber);
            orderNos.add(orderNumber);
        });

        List<String> filterOrderNos = orderNos.stream().distinct().collect(Collectors.toList());

        System.out.println("订单样例："+ orderNos.get(22));
        System.out.println("生成订单数："+orderNos.size());
        System.out.println("过滤重复后订单数："+filterOrderNos.size());
        System.out.println("重复订单数："+(orderNos.size()-filterOrderNos.size()));
    }
}

/**
 订单样例：20082115575546011022
 生成订单数：8000
 过滤重复后订单数：8000
 重复订单数：0
 **/
