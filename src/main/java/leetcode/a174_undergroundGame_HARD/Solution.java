package leetcode.a174_undergroundGame_HARD;

import java.util.Arrays;

/**
 * 地下城游戏
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，
 * 他必须穿过地下城并通过对抗恶魔来拯救公主。
 *
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；
 * 其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 *
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 *
 * -2 (K)	-3	3
 * -5	-10	1
 * 10	30	-5 (P)
 *
 * 说明:
 * 骑士的健康点数没有上限。
 * 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 *
 * @author neptune
 * @create 2020 07 12 10:06 下午
 */
public class Solution {

    /**
     * dp 解法
     * @param dungeon
     * @return
     */
//    public int calculateMinimumHP(int[][] dungeon) {
//        int n = dungeon.length, m = dungeon[0].length;
//        int[][] dp = new int[n + 1][m + 1];
//        for (int i = 0; i <= n; ++i) {
//            Arrays.fill(dp[i], Integer.MAX_VALUE);
//        }
//        dp[n][m - 1] = dp[n - 1][m] = 1;
//        for (int i = n - 1; i >= 0; --i) {
//            for (int j = m - 1; j >= 0; --j) {
//                int minn = Math.min(dp[i + 1][j], dp[i][j + 1]);
//                dp[i][j] = Math.max(minn - dungeon[i][j], 1);
//            }
//        }
//        return dp[0][0];
//    }


    int[][] memo; // 定义记忆化数组
    public int calculateMinimumHP(int[][] dungeon) {
        memo = new int[dungeon.length][dungeon[0].length];
        return dfs(dungeon, dungeon.length, dungeon[0].length, 0, 0);
    }

    /**
     * DFS + 记忆化（0ms，100%，搜索时会有大量重复计算的分支，加上记忆化即可解决）
     * @param dungeon
     * @param m
     * @param n
     * @param i
     * @param j
     * @return
     */
    private int dfs(int[][] dungeon, int m, int n, int i, int j) {
        // 到达终点，递归终止。
        if (i == m - 1 && j == n - 1) {
            return Math.max(1 - dungeon[i][j], 1);
        }
        // 如果memo数组中有值，直接取出并返回，不进行后续的搜索。
        if (memo[i][j] > 0) {
            return memo[i][j];
        }
        // 同解法一，向右搜+向下搜
        int minRes = 0;
        if (i == m - 1) {   // 最后一行，只能向右搜索。
            minRes =  Math.max(dfs(dungeon, m, n, i, j + 1) - dungeon[i][j], 1);
        } else if (j == n - 1) {    // 最后一行，只能向右搜索。
            minRes = Math.max(dfs(dungeon, m, n, i + 1, j) - dungeon[i][j], 1);
        } else {
         // 向下搜索 + 向右搜索，得到(i, j)点的后续路径所要求的最低血量 Math.min(dfs(i + 1, j), dfs(i, j + 1))，
        // 又因为(i, j)点本身提供血量dungeon[i][j], 因此从(i, j)开始所需的最低血量为 Math.min(dfs(i + 1, j), dfs(i, j + 1)) - dungeon[i][j]
        // 因为骑士的血量不能小于1，因此要和1取个max。
            minRes = Math.max(Math.min(dfs(dungeon, m, n, i + 1, j), dfs(dungeon, m, n, i, j + 1)) - dungeon[i][j], 1);
        }
        // 将结果存入memo数组
        return memo[i][j] = minRes;
    }


    /**
     * DFS 未优化，但是超时
     * @param dungeon
     * @param m
     * @param n
     * @param i
     * @param j
     * @return
     */
//    private int dfs(int[][] dungeon, int m, int n, int i, int j) {
//        // 到达终点，递归终止。
//        if (i == m - 1 && j == n - 1) {
//            return Math.max(1 - dungeon[i][j], 1);
//        }
//        // 最后一行，只能向右搜索。
//        if (i == m - 1) {
//            return Math.max(dfs(dungeon, m, n, i, j + 1) - dungeon[i][j], 1);
//        }
//        // 最后一列，只能向下搜索。
//        if (j == n - 1) {
//            return Math.max(dfs(dungeon, m, n, i + 1, j) - dungeon[i][j], 1);
//        }
//        // 向下搜索 + 向右搜索，得到(i, j)点的后续路径所要求的最低血量 Math.min(dfs(i + 1, j), dfs(i, j + 1))，
//        // 又因为(i, j)点本身提供血量dungeon[i][j], 因此从(i, j)开始所需的最低血量为 Math.min(dfs(i + 1, j), dfs(i, j + 1)) - dungeon[i][j]
//        // 因为骑士的血量不能小于1，因此要和1取个max。
//        return Math.max(Math.min(dfs(dungeon, m, n, i + 1, j), dfs(dungeon, m, n, i, j + 1)) - dungeon[i][j], 1);
//    }
}
