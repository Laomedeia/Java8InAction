package concurrent;

import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * corePoolSize vs. maxPoolSize demo
 * https://mp.weixin.qq.com/s/Soa0DADjqchqP9rHvYWyHw
 *
 * Spring中的 ThreadPoolTaskExecutor 是一个 JavaBean ，提供围绕java.util.concurrent.ThreadPoolExecutor 的抽象实例，并作为Spring 中org.springframework.core.task.TaskExecutor 暴露出来.
 * 此外，它可以通过corePoolSize、maxPoolSize、queueCapacity、allowCoreThreadTimeOut和keepAliveSeconds的属性进行高度配置。在本教程中，我们将查看corePoolSize和maxPoolSize属性。
 *
 * corePoolSize
 * corePoolSize 是在不超时情况下，保持活跃的最少线程数 。它是ThreadPoolTaskExecutor的一个可配置项。但是, ThreadPoolTaskExecutor* 抽象将该值的设置委托给底层的java.util.concurrent.ThreadPoolExecutor。
 * 为验证这一点，如果我们将allowCoreThreadTimeOut设置为true，那么所有线程都可能超时，等于将corePoolSize的值设置为零。
 *
 * maxPoolSize
 * 相反，maxPoolSize定义了可以创建的最大线程数。类似地，ThreadPoolTaskExecutor的maxPoolSize属性也将其值委托给底层的java.util.concurrent.ThreadPoolExecutor。
 * 为验证这点, maxPoolSize依赖于queueCapacity，因为ThreadPoolTaskExecutor只会在其队列中的项目数超过queueCapacity*时创建一个新线程。
 *
 *
 * 区别在哪?
 * 当我们向ThreadPoolTaskExecutor提交新任务时，如果正在运行的线程少于corePoolSize线程，即使池中有空闲线程，或者如果正在运行的线程少于maxPoolSize且由queueCapacity定义的队列已满，它也会创建一个新线程。
 *
 * @author neptune
 * @create 2020 08 24 11:54 上午
 */
public class SpringThreadPoolTaskExecutorDemo {

    /**
     *
     * @param taskExecutor
     * @param countDownLatch
     * @param numThreads 要准备并发执行的任务数
     */
    public void startThreads(ThreadPoolTaskExecutor taskExecutor, CountDownLatch countDownLatch,
                             int numThreads) {
        for (int i = 0; i < numThreads; i++) {
            taskExecutor.execute(() -> {
                try {
                    Thread.sleep(100L * ThreadLocalRandom.current().nextLong(1, 10));
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }


    @Test
    public void whenUsingDefaults_thenSingleThread() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.afterPropertiesSet();

        CountDownLatch countDownLatch = new CountDownLatch(10);
        this.startThreads(taskExecutor, countDownLatch, 10);

        while (countDownLatch.getCount() > 0) {
            assertEquals(1, taskExecutor.getPoolSize());
        }
    }


    @Test
    public void whenCorePoolSizeFive_thenFiveThreads() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.afterPropertiesSet();

        CountDownLatch countDownLatch = new CountDownLatch(10);
        this.startThreads(taskExecutor, countDownLatch, 10);

        while (countDownLatch.getCount() > 0) {
            assertEquals(5, taskExecutor.getPoolSize());
        }
    }


    @Test
    public void whenCorePoolSizeFiveAndMaxPoolSizeTen_thenFiveThreads() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
//        taskExecutor.setQueueCapacity(0);  // 如果不指定 QueueCapacity ，默认为无限制 QueueCapacity
        taskExecutor.afterPropertiesSet();

        CountDownLatch countDownLatch = new CountDownLatch(11);
        this.startThreads(taskExecutor, countDownLatch, 11);

        while (countDownLatch.getCount() > 0) {
            assertEquals(5, taskExecutor.getPoolSize());
        }
    }

    @Test
    public void whenCorePoolSizeFiveAndMaxPoolSizeTenAndQueueCapacityTen_thenTenThreads() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(10);
        taskExecutor.afterPropertiesSet();

        CountDownLatch countDownLatch = new CountDownLatch(20);
        this.startThreads(taskExecutor, countDownLatch, 20);

        while (countDownLatch.getCount() > 0) {
            assertEquals(10, taskExecutor.getPoolSize());
        }
    }
}
