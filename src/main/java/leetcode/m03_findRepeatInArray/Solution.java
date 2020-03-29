package leetcode.m03_findRepeatInArray;

import java.util.HashMap;
import java.util.Map;

/**
 * 找出数组中重复的数字。
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * @author neptune
 * @create 2020 03 28 10:41 上午
 */
public class Solution {

    public int findRepeatNumber(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        Map<Integer, Boolean> iMap = new HashMap();
        for (int i = 0 ; i < nums.length ; i++) {
            if(iMap.containsKey(nums[i])) {
                return nums[i];
            } else {
                iMap.put(nums[i], true);
            }
        }
        return 0;
    }

    // 很有效率的解法。两个数组，第二个数组用值当成索引, 每次 + 1，大于 1 则肯定重复（ 类似 hash 碰撞）
    public int findRepeatNumber2(int[] nums) {
        int[] arr = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            arr[nums[i]]++;
            if(arr[nums[i]] > 1)
                return nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] test = { 2, 3, 1, 0, 2, 5, 3 };
//        System.out.println(solution.findRepeatNumber(test));
        System.out.println(solution.findRepeatNumber2(test));

    }
}
