package java8InAction.mytest;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import javafx.scene.control.Button;
import java8InAction.chap3.Lambdas;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * FunctionalInterface 测试
 * @author neptune
 * @create 2018 02 24 上午11:48
 */
public class FunctionalInterfaceTests {

    void fileFilter() {
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
    }

    void threadRun() {
        Thread t = new Thread(() -> System.out.println("Hello world"));
    }

    void runnableRun() {
        Runnable r = () -> {
            System.out.println("hello world");
        };
        r.run();
    }

    void threadPoolRun() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.shutdown();
    }

    void buttonEvent() {
        Button button = new Button("Send");
        button.setOnAction(event -> {});
    }

    void sortNumber() {
        List<Integer> list = Arrays.asList(5, 7, 10, 1, 20);
        //list.sort((num1, num2) -> num1.compareTo(num2));
        list.sort(Comparator.naturalOrder());
        list.sort(Comparator.reverseOrder());
        //list.sort(Comparator.reverseOrder().thenComparing());   //thenComparing 复合比较器
        System.out.println(list);
    }

    /**
     * 现在，Java 8的Lambda和匿名类可以做类似于闭包的事情: 它们可以作为参数传递给方法，并且可以访问其作用域之外的变量。
     * 但有一个限制:它们不 能修改定义Lambda的方法的局部变量的内容。这些变量必须是隐式最终的。可以认为Lambda 是对值封闭，而不是对变量封闭。
     * 如前所述，这种限制存在的原因在于局部变量保存在栈上，并且隐式表示它们仅限于其所在线程。如果允许捕获可改变的局部变量，
     * 就会引发造成线程 不安全的新的可能性，而这是我们不想看到的(实例变量可以，因为它们保存在堆中，而堆 是在线程之间共享的)。
     */
    void variableInLambda() {
        final int portNumber = 1337;  // must be final
        Runnable r = () -> System.out.println(portNumber);
        r.run();
    }

    /**
     * 函数引用
     */
    void functionRef() {
        List<String> str = Arrays.asList("a","c","A","B");
        str.sort(String::compareToIgnoreCase);
        //str.sort((s1,s2) -> s1.compareToIgnoreCase(s2));
        System.out.println(str);

        // ------- 构造器函数引用 ---
        Supplier<Lambdas.Apple> c1 = () -> new Lambdas.Apple(1000, "yellow");
        Lambdas.Apple a1 = c1.get();

        // CustomFunctionInterface<Integer, String, Lambdas.Apple> c2 = Lambdas.Apple::new;
        BiFunction<Integer, String, Lambdas.Apple> c2 = Lambdas.Apple::new;
        Lambdas.Apple a2 = c2.apply(1000, "green");

        Function<Integer, Lambdas.Apple> c3 = Lambdas.Apple::new;
        Lambdas.Apple a3 = c3.apply(1000);
    }

    /**
     * 复合函数引用.注意andThen和compose的区别
     */
    void compositeFunctionRef() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        //Function<Integer, Integer> h = f.andThen(g);
        Function<Integer, Integer> h = f.compose(g);

        int result = h.apply(1);
        System.out.println(result);
    }

    void sysoutRef() {
        List<String> list = Arrays.asList("a","b","d","c");
        //流只能消费一次,同一个stream再次foreach会报错
        list.stream().forEach(System.out::println);
    }


    public static void main(String[] args) {
        FunctionalInterfaceTests myTests = new FunctionalInterfaceTests();
        //myTests.threadPoolRun();
       // myTests.sortNumber();
        //myTests.functionRef();
        // myTests.compositeFunctionRef();
        myTests.sysoutRef();
    }
}
