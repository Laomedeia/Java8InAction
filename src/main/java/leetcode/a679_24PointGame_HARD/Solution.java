package leetcode.a679_24PointGame_HARD;

import java.util.ArrayList;
import java.util.List;

/**
 * 24 点游戏
 *
 * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
 * 示例 1:
 * 输入: [4, 1, 8, 7]
 * 输出: True
 * 解释: (8-4) * (7-1) = 24
 * 示例 2:
 *
 * 输入: [1, 2, 1, 2]
 * 输出: False
 * 注意:
 *
 * 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
 * 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。
 * 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12
 *
 * @author neptune
 * @create 2020 08 22 11:03 下午
 */
public class Solution {

    static final int TARGET = 24;
    static final double EPSILON = 1e-6;
    static final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

    // 1. 回溯法
    // https://leetcode-cn.com/problems/24-game/solution/24-dian-you-xi-by-leetcode-solution/
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<Double>();
        for (int num : nums) {
            list.add((double) num);
        }
        return solve(list);
    }

    public boolean solve(List<Double> list) {
        if (list.size() == 0) {
            return false;
        }
        if (list.size() == 1) {
            return Math.abs(list.get(0) - TARGET) < EPSILON;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    List<Double> list2 = new ArrayList<Double>();
                    for (int k = 0; k < size; k++) {
                        if (k != i && k != j) {
                            list2.add(list.get(k));
                        }
                    }
                    for (int k = 0; k < 4; k++) {
                        if (k < 2 && i > j) {
                            continue;
                        }
                        if (k == ADD) {
                            list2.add(list.get(i) + list.get(j));
                        } else if (k == MULTIPLY) {
                            list2.add(list.get(i) * list.get(j));
                        } else if (k == SUBTRACT) {
                            list2.add(list.get(i) - list.get(j));
                        } else if (k == DIVIDE) {
                            if (Math.abs(list.get(j)) < EPSILON) {
                                continue;
                            } else {
                                list2.add(list.get(i) / list.get(j));
                            }
                        }
                        if (solve(list2)) {
                            return true;
                        }
                        list2.remove(list2.size() - 1);
                    }
                }
            }
        }
        return false;
    }

//    2. 全排列解法：
//    public boolean judgePoint24(int[] nums) {
//        return backTrack(nums, 0);
//    }
//
//    // 第一步：求出所有排列，一一验证
//    public boolean backTrack(int[] nums, int index) {
//        if (index == 4) {
//            return judge(nums[0], nums[1], nums[2], nums[3]);
//        }
//        for (int i = index; i < 4; i++) {
//            swap(nums, index, i);
//            if (backTrack(nums, index + 1)) return true;
//            swap(nums, index, i);
//        }
//        return false;
//    }
//
//    public void swap(int[] nums, int i, int j) {
//        int tmp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = tmp;
//    }
//
//    // 第二步：由于已经全排列，a、b、c、d 都是等价的，利用四种运算符选出三个数继续
//    public boolean judge(double a, double b, double c, double d) {
//        return judge(a + b, c, d) ||
//                judge(a - b, c, d) ||
//                judge(a * b, c, d) ||
//                judge(a / b, c, d);
//    }
//
//    // 第三步：a 是有两个数组成的，b、c 只表示一个数，等价
//    public boolean judge(double a, double b, double c) {
//        // 情况一：a 和 b(c) 组合，a 和 b(c) 不等价，- 号和 / 号需要考虑两种情况
//        return judge(a + b, c) ||
//                judge(a - b, c) ||
//                judge(a * b, c) ||
//                judge(a / b, c) ||
//                judge(b - a, c) ||
//                judge(b / a, c) ||
//                // 情况二：b 和 c 组合
//                judge(a, b - c) ||
//                judge(a, b + c) ||
//                judge(a, b * c) ||
//                judge(a, b / c);
//    }
//
//    // 第四步：a 和 b 不等价
//    public boolean judge(double a, double b) {
//        return Math.abs(a + b - 24) < 0.001 ||
//                Math.abs(a - b - 24) < 0.001 ||
//                Math.abs(a * b - 24) < 0.001 ||
//                Math.abs(a / b - 24) < 0.001 ||
//                Math.abs(b - a - 24) < 0.001 ||
//                Math.abs(b / a - 24) < 0.001;
//    }

}
