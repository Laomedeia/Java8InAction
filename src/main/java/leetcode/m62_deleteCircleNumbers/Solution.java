package leetcode.m62_deleteCircleNumbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 约瑟夫环解
 * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * 限制 1 <= n <= 10^5，1 <= m <= 10^6
 * @author neptune
 * @create 2020 03 30 9:41 上午
 */
public class Solution {


    public int lastRemaining1(int n, int m) {
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }

    /**
     * 约瑟夫环数学解法解
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining2(int n, int m) {
        int ans = 0;
        // 最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
       System.out.println( solution.lastRemaining1(5,3));
//        System.out.println(2%5);
    }

}
