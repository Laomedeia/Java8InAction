package leetcode.a16_threeSumClosest_MID;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.ListUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 *
 * 提示：
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 *
 * @author neptune
 * @create 2020 06 24 3:14 下午
 */
public class Solution {

    public int threeSumClosest(int[] nums, int target) {
//        int[] copyTarget = new int[nums.length + 1];
//        System.arraycopy(nums, 0, copyTarget, 0, nums.length);
//        copyTarget[copyTarget.length - 1] = target;
//        Arrays.sort(copyTarget);
        Arrays.sort(nums);//先排序
        int minGap = Integer.MAX_VALUE; //间隔值，保存的是最小间隔值(最小差值)
        int res = Integer.MAX_VALUE;    //返回的数据
        for (int i = 0; i < nums.length - 2; i++) {
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                int tempMinGap = target - sum;
                if (Math.abs(tempMinGap) < Math.abs(minGap)) {
                    minGap = tempMinGap;
                    res = sum;
                }
                if (tempMinGap == 0)
                    return target;
                else if (tempMinGap > 0)
                    lo++;
                else
                    hi--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-1, 2, 1, -4};
        solution.threeSumClosest(nums, 1); // -4,-1,1,1,2
    }
}
