package leetcode.a309_maxProfit_MID;

/**
 * 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * <p>
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * @author neptune
 * @create 2020 07 10 4:17 下午
 */
public class Solution {

    /**
     * 动态规划，
     * 参考 https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/yi-tu-miao-dong-jie-fa-by-zi-gei-zi-zu/
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;
        int dp[][] = new int[n][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);//对于持股状态，我们要么是之前持有的股份没有卖，要么就是冷冻期不持股，冷冻期过了新买的。
            dp[i][1] = dp[i - 1][0] + prices[i];//对于不持股且在冷冻期，我们肯定是持有股票并且卖了股票，所以有收益。
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);//对于不持股且不在冷冻期，说明我们在i-1天不持有股票，不持有股票有两种状态（在不在冷冻期）。
        }
        return Math.max(dp[n - 1][2], dp[n - 1][1]);//最后对不持股状态求最大，因为持股是没有意义的，只能是利润减少。
    }

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int dp0 = -prices[0], dp1 = 0, dp2 = 0;
        for (int i = 1; i < prices.length; i++) {
            int temp0 = Math.max(dp0, dp2 - prices[i]);
            int tmep1 = dp0 + prices[i];
            int temp2 = Math.max(dp1, dp2);
            dp0 = temp0;
            dp1 = tmep1;
            dp2 = temp2;
        }
        int maxProfit = Math.max(dp1, dp2);
        return maxProfit;
    }
}
