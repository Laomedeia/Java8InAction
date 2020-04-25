package leetcode.a55_jumpGame;

/**
 * @author neptune
 * @create 2020 04 17 9:27 下午
 */
public class Solution {

    public boolean canJump(int[] nums)
    {
        int k = 0;
        for (int i = 0; i < nums.length; i++)
        {
            if (i > k) return false;
            k = Math.max(k, i + nums[i]);
        }
        return true;
    }

//    public boolean canJump2(int[] nums){
//        for (int i=0;i<nums.length-1;i++){
//            if (nums[i]==0){
//                if (passZero(nums, i))
//                    continue;
//                else
//                    return false;
//            }
//        }
//        return true;
//    }
//    //判断是否能跳出当前0
//    public static boolean passZero(int[] nums,int index){
//        for (int i=index;i>=0;i--){
//            if (nums[i]>(index-i))
//                return true;
//        }
//        return false;
//    }

    public static void main(String[] args) {
         int[] nums1 = {2,3,1,1,4};
         int[] nums2 = {3,2,1,0,4};
         int[] nums3 = {2,3,5,1,4};
         int[] nums4 = {0};
        int[] nums5 = {1};
        int[] nums6 = {2,0};
        int[] nums7 = {2,5,0,0};
        int[] nums8 = {1,1,0,1};
        int[] nums9 = {0,1};
        int[] nums10 = {1,1,1,0};
        int[] nums11 = {1,1,2,2,0,1,1};
        int[] nums12 ={1,2,0,1};
        Solution solution = new Solution();
        System.out.println(solution.canJump(nums1));
        System.out.println(solution.canJump(nums2));
        System.out.println(solution.canJump(nums3));
        System.out.println(solution.canJump(nums4));
        System.out.println(solution.canJump(nums5));
        System.out.println(solution.canJump(nums6));
        System.out.println(solution.canJump(nums7));
        System.out.println(solution.canJump(nums8));
        System.out.println(solution.canJump(nums9));
        System.out.println(solution.canJump(nums10));
        System.out.println(solution.canJump(nums11));
        System.out.println(solution.canJump(nums12));
    }

}
