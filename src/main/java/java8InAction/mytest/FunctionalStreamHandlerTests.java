package java8InAction.mytest;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author neptune
 * @create 2018 02 26 下午3:39
 */
public class FunctionalStreamHandlerTests {

    void createNewFile() {
        File testfile = new File("/tmp/testfile");
//        try {
//            testfile.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(testfile.getName());
        System.out.println(testfile.exists());
        System.out.println(testfile.length());
    }

    public static void main(String[] args) {
        FunctionalStreamHandlerTests functionalStreamHandlerTests = new FunctionalStreamHandlerTests();
        functionalStreamHandlerTests.createNewFile();
        // fibonnaci with iterate（iterate无限流）
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1],t[0] + t[1]})
                .limit(10)
                .map(t -> t[0]).collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
