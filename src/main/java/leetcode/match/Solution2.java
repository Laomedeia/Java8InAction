package leetcode.match;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author neptune
 * @create 2020 04 18 3:21 下午
 */
public class Solution2 {

    public int numWays(int n, int[][] relation, int k) {
        Arrays.sort(relation, (v1, v2) -> v2[1] - v1[1]);
        int finalValue = n - 1;
        int target = finalValue;
        int plan = 0;
        int counter = 0;
        int intervalIndex = 0;
        int start = 0;
        boolean jumpout = false;
        while (true) {
            if(jumpout) {
                break;
            }
            for (int i = start; i <= relation.length; i++) {
                if(i == relation.length) {
                    jumpout = true;
                    break;
                }
                if (relation[i][1] == target) {
                    counter++;
                    if(target == finalValue) {
                        intervalIndex = i;
                    }
                    int prevNum = relation[i][0];
                    target = prevNum;
                    if (target == 0 && counter == k) {
                        plan += 1;
                        target = finalValue;
                        start = intervalIndex + 1;
                        counter = 0;
                        break;
                    } else {
                        start = 0;
                    }
                    break;
                }
            }
        }
        return plan;
    }

//    public int numWays2(int n, int[][] relation, int k) {
//        int finalValue = n - 1;
//        int target = finalValue;
//        int plan = 0;
//        int counter = 0;
//        int intervalIndex = 0;
//        int start = 0;
//
//        for (int i = start; i <= relation.length; i++) {
//            if(i == relation.length) {
//                break;
//            }
//            if (relation[i][1] == target) {
//                counter++;
//                if(target == finalValue) {
//                    intervalIndex = i;
//                }
//                int prevNum = relation[i][0];
//                target = prevNum;
//                if (target == 0 && counter == k) {
//                    plan += 1;
//                    target = finalValue;
//                    start = intervalIndex + 1;
//                    counter = 0;
//                    break;
//                } else {
//                    start = 0;
//                }
//                break;
//            }
//            for (int j = 0; j < relation[0].length; j++) {
//
//            }
//        }
//
//        return plan;
//    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        int[][] array = new int[][] {{0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}};
//        int[][] array = new int[][] {{0,2},{2,1}};
        int n = 5;
        int k = 3;
        System.out.println(solution2.numWays(n, array, k));
    }

}
