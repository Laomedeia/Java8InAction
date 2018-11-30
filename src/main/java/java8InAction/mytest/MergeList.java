package java8InAction.mytest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author neptune
 * @create 2018 04 03 下午11:24
 */
public class MergeList {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 5, 8, 12));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(2, 4, 6, 10));
        list1.addAll(list2);
        list1.stream().sorted().forEach(n-> System.out.println(n));
    }
}
