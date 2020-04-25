package leetcode.a11_calWaterVolume;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * @author neptune
 * @create 2020 04 18 10:49 上午
 */
public class Solution {

    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            res = height[i] < height[j] ? Math.max(res, (j - i) * height[i++]) :  Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(solution.maxArea(array));
    }
}
