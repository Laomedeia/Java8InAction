package leetcode.a42_calcRain;

import java.util.Stack;

import static java.lang.Integer.min;

/**
 * 接雨水
 *  python:
 *      def trap(self, height: List[int]) -> int:
 *         length=len(height)
 *         if length<3:return 0
 *         res,idx=0,0
 *         stack=[]
 *         while idx<length:
 *             while len(stack)>0 and height[idx]>height[stack[-1]]:
 *                 top=stack.pop()#index of the last element in the stack
 *                 if len(stack)==0:
 *                     break
 *                 h=min(height[stack[-1]],height[idx])-height[top]
 *                 dist=idx-stack[-1]-1
 *                 res+=(dist*h)
 *             stack.append(idx)
 *             idx+=1
 *         return res
 *
 * @author neptune
 * @create 2020 04 04 3:37 下午
 */
public class Solution {

    public int trap(int[] nums) {
        int length = nums.length;
        if(length < 3) {
            return 0;
        }
        int result = 0, idx = 0;
        Stack<Integer> stack = new Stack();
        while (idx < length) {
            while (stack.size() > 0 && nums[idx] > nums[stack.peek()]) {
                int top = stack.pop();
                if(stack.size() == 0) {
                    break;
                }
                int h = Math.min(nums[stack.peek()], nums[idx]) - nums[top];
                int distance = idx - stack.peek() - 1;
                result+=(distance * h);
            }
            stack.push(idx);
            idx+=1;
        }
        return result;
    }

    /**
     * 第二个解法
     * https://leetcode-cn.com/problems/trapping-rain-water/solution/dan-diao-zhan-jie-jue-jie-yu-shui-wen-ti-by-sweeti/
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if (height == null) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            while(!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int curIdx = stack.pop();
                // 如果栈顶元素一直相等，那么全都pop出去，只留第一个。
                while (!stack.isEmpty() && height[stack.peek()] == height[curIdx]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    int stackTop = stack.peek();
                    // stackTop此时指向的是此次接住的雨水的左边界的位置。右边界是当前的柱体，即i。
                    // Math.min(height[stackTop], height[i]) 是左右柱子高度的min，减去height[curIdx]就是雨水的高度。
                    // i - stackTop - 1 是雨水的宽度。
                    ans += (Math.min(height[stackTop], height[i]) - height[curIdx]) * (i - stackTop - 1);
                }
            }
            stack.add(i);
        }
        return ans;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
