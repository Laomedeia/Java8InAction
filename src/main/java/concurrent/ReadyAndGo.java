package concurrent;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * 线程起跑线。参考 https://javadoop.com/post/phaser-tutorial
 * 启动 10 个线程执行任务，由于启动时间有先后，我们希望等到所有的线程都启动成功以后再开始执行，让每个线程在同一个起跑线上开始执行业务操作
 * @author neptune
 * @create 2019 03 19 17:56
 */
public class ReadyAndGo {

    // 1. 使用 CountDownLatch 测试
    public static void countDownLatchTest(ExecutorService executorService) {
        // 设置 count 为 1
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    // 2. 每个线程都等在栅栏这里，等待放开栅栏，不会因为有些线程先启动就先跑路了
                    latch.await();
                    // doWork();
                    long threadId = Thread.currentThread().getId();
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Thread # " + threadId + ", name: "+threadName+" is doing this work");
                } catch (Exception ignore) {
                }
            });
        }

        // 放开栅栏
        latch.countDown();
    }

    // 2. 使用 cyclicBarrierTest 测试
    public static void cyclicBarrierTest(ExecutorService executorService) {
        // 构造函数中指定了 10 个 parties
        CyclicBarrier barrier = new CyclicBarrier(10);

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    // 每个线程"报告"自己到了，
                    // 当第10个线程到的时候，也就是所有的线程都到齐了，一起通过
                    barrier.await();
                    long threadId = Thread.currentThread().getId();
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Thread # " + threadId + ", name: "+threadName+" is doing this work");

                } catch (InterruptedException | BrokenBarrierException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    // 3. 使用 phaserTest 测试
    public static void phaserTest(ExecutorService executorService) {
        Phaser phaser = new Phaser();
        // 注册一个 party
        phaser.register();
        for (int i = 0; i < 10; i++) {

            phaser.register();

            executorService.submit(() -> {
                // 每个线程到这里进行阻塞，等待所有线程到达栅栏
                phaser.arriveAndAwaitAdvance();
                long threadId = Thread.currentThread().getId();
                String threadName = Thread.currentThread().getName();
                System.out.println("Thread # " + threadId + ", name: "+threadName+" is doing this work");
            });
        }
        phaser.arriveAndAwaitAdvance();
    }


        public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10, new BasicThreadFactory.Builder().namingPattern("ready-go-threadpool-%d").daemon(true).build());
//        ReadyAndGo.countDownLatchTest(executorService);
//        ReadyAndGo.cyclicBarrierTest(executorService);
        ReadyAndGo.phaserTest(executorService);
    }
}
