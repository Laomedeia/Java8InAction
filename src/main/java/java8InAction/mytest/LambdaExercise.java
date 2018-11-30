package java8InAction.mytest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author neptune
 * @create 2018 03 09 下午12:45
 */
public class LambdaExercise {

    // 给定数组或列表简单求和
    void sumInt() {
        List<Integer> ss = Arrays.asList(1,2,3);
        int[] s = new int[] {1,2,3};
        System.out.println(IntStream.of(s).sum());
        System.out.println(IntStream.of(s).parallel().reduce(Integer::sum).getAsInt());
        Integer sum = ss.stream().reduce(0, (ss1, ss2) -> ss1 + ss2);
        System.out.println(sum);

    }

    public static void main(String[] args) {
        LambdaExercise lambdaExercise = new LambdaExercise();
        lambdaExercise.sumInt();
    }


}
