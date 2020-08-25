package leetcode.a337_houseRober3_MID;

import java.util.HashMap;

/**
 * 打家劫舍 III
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 *
 * 示例 2:
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 *  解法：https://leetcode-cn.com/problems/house-robber-iii/solution/san-chong-fang-fa-jie-jue-shu-xing-dong-tai-gui-hu/
 * @create 2020 08 05 5:49 下午
 */
public class Solution {
        public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

//    /**
//     * 解法一 : 暴力解法 + 递归
//     * @param root
//     * @return
//     */
//    public int rob(TreeNode root) {
//        if (root == null) return 0;
//
//        int money = root.val;
//        if (root.left != null) {
//            money += (rob(root.left.left) + rob(root.left.right));
//        }
//
//        if (root.right != null) {
//            money += (rob(root.right.left) + rob(root.right.right));
//        }
//
//        return Math.max(money, rob(root.left) + rob(root.right));
//    }

    /**
     * 记忆化 - 解决重复子问题
     * 针对解法一种速度太慢的问题，经过分析其实现，我们发现爷爷在计算自己能偷多少钱的时候，同时计算了 4 个孙子能偷多少钱，也计算了 2 个儿子能偷多少钱。这样在儿子当爷爷时，就会产生重复计算一遍孙子节点。
     *
     * 于是乎我们发现了一个动态规划的关键优化点
     * 重复子问题
     *
     * 我们这一步针对重复子问题进行优化，我们在做斐波那契数列时，使用的优化方案是记忆化，但是之前的问题都是使用数组解决的，把每次计算的结果都存起来，下次如果再来计算，就从缓存中取，不再计算了，这样就保证每个数字只计算一次。
     * 由于二叉树不适合拿数组当缓存，我们这次使用哈希表来存储结果，TreeNode 当做 key，能偷的钱当做 value
     *
     * 解法一加上记忆化优化后代码如下：
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        HashMap<TreeNode, Integer> memo = new HashMap<>();
        return robInternal(root, memo);
    }

    public int robInternal(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);
        int money = root.val;

        if (root.left != null) {
            money += (robInternal(root.left.left, memo) + robInternal(root.left.right, memo));
        }
        if (root.right != null) {
            money += (robInternal(root.right.left, memo) + robInternal(root.right.right, memo));
        }
        int result = Math.max(money, robInternal(root.left, memo) + robInternal(root.right, memo));
        memo.put(root, result);
        return result;
    }

    /**
     * 记忆化：最优解
     */
//    public int rob(TreeNode root) {
//        int[] result = robInternal(root);
//        return Math.max(result[0], result[1]);
//    }
//
//    public int[] robInternal(TreeNode root) {
//        if (root == null) return new int[2];
//        int[] result = new int[2];
//
//        int[] left = robInternal(root.left);
//        int[] right = robInternal(root.right);
//
//        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
//        result[1] = left[0] + right[0] + root.val;
//
//        return result;
//    }

}
