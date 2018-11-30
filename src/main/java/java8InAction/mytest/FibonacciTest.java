package java8InAction.mytest;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * @author neptune
 * @create 2018 04 03 下午10:28
 */
public class FibonacciTest {

    public void printFib(int count) {
//        Stream.iterate(new long[]{ 1, 1 }, p->new long[]{ p[1], p[0]+p[1] })
//                .limit(count).forEach(p->System.out.println(p[0]));
        Stream.iterate(new BigInteger[]{ BigInteger.ONE, BigInteger.ONE },
                p->new BigInteger[]{ p[1], p[0].add(p[1]) })
                .limit(92).forEach(p->System.out.println(p[0]));
    }

    public static void main(String[] args) {
        FibonacciTest fibonacciTest = new FibonacciTest();
        fibonacciTest.printFib(100);

    }
}
