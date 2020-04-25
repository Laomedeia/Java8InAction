package leetcode.match;

/**
 * 桌上有 n 堆力扣币，每堆的数量保存在数组 coins 中。我们每次可以选择任意一堆，拿走其中的一枚或者两枚，求拿完所有力扣币的最少次数。
 * 输入：[4,2,1]
 * 输出：4
 * 解释：第一堆力扣币最少需要拿 2 次，第二堆最少需要拿 1 次，第三堆最少需要拿 1 次，总共 4 次即可拿完。
 *
 * @author neptune
 * @create 2020 04 18 2:58 下午
 */
public class Solution {
    public int minCount(int[] coins) {
        int sum = 0;
        for (int i = 0; i < coins.length; i++) {
            int el = coins[i];
            if(el%2 == 0) {
                sum += (el/2);
            } else {
                sum += (el/2) + 1;
            }
        }
        return sum;
        // 最后至少需要拿 1 次

        // 前面最少次数

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = {2,3,10};
        System.out.println(solution.minCount(array));
    }
}
