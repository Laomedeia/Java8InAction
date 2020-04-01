package leetcode.a912_sortArray;


import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @author neptune
 * @create 2020 03 31 3:49 下午
 */
public class Solution {

    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        System.out.println(nums);
        return nums;
    }

    // 快速排序
    public void quickSort(int[] nums, int lPos, int rPos) {
        if(lPos >= rPos) {
            return;
        }
        int left = lPos, right = rPos;
        int pivot = nums[left];  // 参考值
        while (left < right) {
            // 1. 右边指针开始工作，向左移动
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            // 发现右边值小于了左边值，调换位置
            if(left < right) {
                nums[left] = nums[right];
            }
            // 2. 左边指针开始工作，向右移动
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            // 发现左边值大于了右边值，调换位置
            if(left < right) {
                nums[right] = nums[left];
            }
            // 3. 变更后的left 大于了 right（或下标重合）, 则设置 pivot 值到指定位置
            if(left >= right) {
                nums[left] = pivot;
            }
        }
        // 递归
        quickSort(nums, lPos, right - 1);
        quickSort(nums, right + 1, rPos);
    }

    public int[] sortArray1(int[] nums) {


        return nums;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = new int[]{-50000,-1,-2,-2,-3,1,2,32,23,22221,50000};
        Arrays.stream(solution.sortArray(array)).forEach(x-> System.out.println(x));
    }
}
