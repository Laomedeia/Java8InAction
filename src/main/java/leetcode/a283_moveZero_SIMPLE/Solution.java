package leetcode.a283_moveZero_SIMPLE;

/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * @create 2020 11 19 11:47 下午
 */
public class Solution {

    public void moveZeroes(int[] nums) {
        int i,j=0;
        for(i=0;i<nums.length;i++){
            if(nums[i]!=0){
                nums[j++]=nums[i];//把当前数组当成一个新的数组，把非零元素插入到其中
            }
        }
        for(i=j;i<nums.length;i++){//j指向要插入的点的坐标（还未插入），所以第一轮循环结束后，j之后的元素都应置为0
            nums[i]=0;
        }
    }
}
