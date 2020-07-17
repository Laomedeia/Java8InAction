package leetcode.a120_triangleMinimumTotal_MID;

import java.util.List;

/**
 * 三角形最小路径和
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * @author neptune
 * @create 2020 07 14 11:02 下午
 */
public class Solution {

    /**
     * dp 解法
     * 定义二维 dp 数组，将解法二中「自顶向下的递归」改为「自底向上的递推」。
     *
     * 1、状态定义：
     * dp[i][j] 表示从点 (i,j) 到底边的最小路径和。
     *
     * 2、状态转移：
     * dp[i][j] = min(dp[i+1][j], dp[i+1][j+1]) + triangle[i][j]
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // dp[i][j] 表示从点 (i, j) 到底边的最小路径和。
        int[][] dp = new int[n + 1][n + 1];
        // 从三角形的最后一行开始递推。
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}
