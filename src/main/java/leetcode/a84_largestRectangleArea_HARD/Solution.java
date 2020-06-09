package leetcode.a84_largestRectangleArea_HARD;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * 柱状图中最大的矩形
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 *
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 *
 * @author neptune
 * @create 2020 05 30 5:15 下午
 */
public class Solution {

    // 解法 1： 单调栈
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }

        int area = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peekLast()] > heights[i]){
                int height = heights[stack.removeLast()];

                while (!stack.isEmpty() &&  heights[stack.peekLast()] == height){
                    stack.removeLast();
                }

                int width;
                if (stack.isEmpty()){
                    width = i;
                } else {
                    width = i - stack.peekLast() - 1;
                }

                area = Math.max(area , width * height);
            }
            stack.addLast(i);
        }

        while (!stack.isEmpty()){
            int height = heights[stack.removeLast()];

            while (!stack.isEmpty() &&  heights[stack.peekLast()] == height){
                stack.removeLast();
            }

            int width;
            if (stack.isEmpty()){
                width = len;
            } else {
                width = len - stack.peekLast() - 1;
            }

            area = Math.max(area , width * height);
        }
        return area;
    }

    // 解法 2：单调栈 + 常数优化
    public int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Stack<Integer> mono_stack = new Stack<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                right[mono_stack.peek()] = i;
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}
