package leetcode.a56_mergeRange;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author neptune
 * @create 2020 04 16 11:26 上午
 */
public class Solution {
    public int[][] merge(int[][] input) {
//        Arrays.stream(input).sorted((v1, v2) -> v1[0] - v2[0]);
        int[][] res = new int[input.length][2];
        // 先按照区间起始位置排序
        Arrays.sort(input, Comparator.comparing(c->c[0]));
//        List list = Arrays.stream(input).sorted(Comparator.comparing(c -> c[0])).collect(Collectors.toList());
//        input = (int[][]) list.toArray(res);
        int idx = -1;
        for (int[] interval: input) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }

    public static void main(String[] args) {
        int[][] input = {{ 1,3},{8,10},{2,6},{15,18}};
//        int[][] input = {{ 1,4},{4,5} };
//        int[][] input = {{ 1,4},{1,4} };
//        int[][] input = {{1,5},{1,4} };
//        int[][] input = {{1,4},{1,5} };
//        int[][] input = {{1,4},{0,2} };
        Solution solution = new Solution();
        int[][] merge = solution.merge(input);
        System.out.println(merge);
        //solution.merge(input);
    }
}
