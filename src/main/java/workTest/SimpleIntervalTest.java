package workTest;

/**
 * 利用按位与的方式（效率更高）实现每隔固定时间进行某项任务
 * @author neptune
 * @create 2018 11 30 4:25 PM
 */
public class SimpleIntervalTest {

    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        for(;;) {
            count += 1;
            // 每隔64次count, sleep 5 秒
            if((count & 0x3f) == 0) {
                Thread.sleep(5000);
            }
            System.out.println(count);
        }
    }
}
