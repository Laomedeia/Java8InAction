package leetcode.a628_maximumProduct_SIMPLE;

import java.util.Arrays;

/**
 * 628. 三个数的最大乘积
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: 6
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意:
 *
 * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 * @create 2021 01 20 6:28 下午
 */
public class Solution {

    /**
     *
     * 将nums排序
     * 1. 如果全为正数或全为负数，最大三个数的乘积为a
     * 2. 如果存在两个以上负数，两个最小负数与最大正数的乘积b
     * 3. 三数乘积的最大值肯定为：max(a,b)
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 1] * nums[n - 2] * nums[n - 3]);
    }
}
