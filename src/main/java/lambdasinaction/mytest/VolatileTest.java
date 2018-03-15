package lambdasinaction.mytest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author neptune
 * @create 2018 03 12 下午10:58
 */
public class VolatileTest implements Runnable {

    //private static volatile int count = 0 ; //使用 volatile 修饰基本数据内存不能保证原子性
    private static AtomicInteger count = new AtomicInteger() ;  //AtomicInteger保证原子性操作
    @Override
    public void run() {
        for (int i=0;i<10000 ;i++){
            //count ++ ;
            count.incrementAndGet() ;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileInc = new VolatileTest() ;
        Thread t1 = new Thread(volatileInc,"t1") ;
        Thread t2 = new Thread(volatileInc,"t2") ;
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        for (int i=0;i<10000 ;i++){
            //count ++ ;
            count.incrementAndGet();
        }
        System.out.println("最终Count="+count);
    }
}
