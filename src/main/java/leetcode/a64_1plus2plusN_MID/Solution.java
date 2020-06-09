package leetcode.a64_1plus2plusN_MID;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 * 示例 1：
 * 输入: n = 3
 * 输出: 6
 *
 * 示例 2：
 * 输入: n = 9
 * 输出: 45
 *
 * @author neptune
 * @create 2020 06 02 11:42 下午
 */
public class Solution {

    public int sumNums(int n) {
        if(n == 1) return 1;
        n += sumNums(n - 1);
        return n;
    }
}
