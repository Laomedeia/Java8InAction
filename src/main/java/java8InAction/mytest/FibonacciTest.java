package java8InAction.mytest;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author neptune
 * @create 2018 04 03 下午10:28
 */
public class FibonacciTest {

    public int printFib(int count) {
        if(count == 0) {
            return 0;
        }
        List<int[]> list = Stream.iterate(new int[]{1, 1}, p -> new int[]{p[1], p[0] + p[1]})
                .limit(count).collect(Collectors.toList());//.forEach(p->System.out.println(p[0]));
        return list.get(list.size() - 1)[0];
//        Stream.iterate(new BigInteger[]{ BigInteger.ONE, BigInteger.ONE },
//                p->new BigInteger[]{ p[1], p[0].add(p[1]) })
//                .limit(count).forEach(p->System.out.println(p[0]));
    }

    public static void main(String[] args) {
        FibonacciTest fibonacciTest = new FibonacciTest();
        int result = fibonacciTest.printFib(0);
        System.out.println(result);
    }
}
