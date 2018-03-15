package lambdasinaction.mytest;

import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author neptune
 * @create 2018 03 13 下午5:30
 */
public class FutureTest {
    static Double doSomeLongComputation() {
        Double res = new Random().nextDouble() * 100 + 1;
//        Random random = new Random();
//        IntStream intstream = random.ints(1, 10);
//        Double res = random.nextDouble();
//        intstream.forEach(i -> System.out.println(i));
        System.out.println("do a long time work....."+res);
        return res;
    }

    static void doSomethingElse() {
        System.out.println("do something else work...");
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() {
                return FutureTest.doSomeLongComputation();
            }});

        FutureTest.doSomethingElse();

        try {
            Double result = future.get(3, TimeUnit.SECONDS);

        } catch (ExecutionException ee) { // 计算抛出一个异常
        } catch (InterruptedException ie) { // 当前线程在等待过程中被中断
        } catch (TimeoutException te) { // 在Future对象完成之前超过已过期
        }

    }


}
