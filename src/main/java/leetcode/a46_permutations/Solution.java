package leetcode.a46_permutations;

import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 解决方案：回溯搜索算法
 * https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
 * @author neptune
 * @create 2020 04 25 8:55 下午
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;

        List<List<Integer>> res = new ArrayList<>(factorial(len));
        if (len == 0) {
            return res;
        }

        // 使用哈希表检测一个数字是否使用过
        Set<Integer> used = new HashSet<>(len);
        Deque<Integer> path = new ArrayDeque<>(len);

        dfs(nums, len, 0, path, used, res);
        return res;
    }

    /**
     * 在 res 变量初始化的时候，最好传入 len 的阶乘，让 ArrayList 在代码执行的过程中不发生扩容行为
     * @param n
     * @return
     */
    private int factorial(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    /**
     * 递归深度遍历
     * @param nums
     * @param len
     * @param depth 递归到第几层
     * @param path 已经选了哪些数
     * @param used hashset used
     * @param res
     */
    private void dfs(int[] nums, int len, int depth,
                     Deque<Integer> path, Set<Integer> used,
                     List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));  // path 最终会回到根节点，这里需要 copy 需要返回的 path 列表到结果里
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!used.contains(i)) {
                used.add(i);
                path.addLast(nums[i]);

                dfs(nums, len, depth + 1, path, used, res);

                //开始回溯到上一层
                path.removeLast();
                used.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.permute(new int[] {1,2,3}));
    }
}