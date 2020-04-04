package leetcode.a8_str2int_atoi;

/**
 * 字符串转换成整数：输入一个表示整数的字符串，把该字符串转换成整数并输出，例如输入字符串”345”，则输出整数345。
 * 在笔试面试中，atoi 即「字符串转换成整数」是一个经典问题了，此题无关算法，考察的更多是编码能力和细节考虑能力。因此自己就动手写了下，写完之后，打开 JDK 的源码想看看大牛是怎么写的，所谓「站在巨人的肩膀上」，果然还是有很多有意思的东西的。
 * 首先，实现的思路是扫描整个字符串，扫描到当前字符时，将之前的结果乘以10加上当前字符代表的数字。
 * 思路是很简单，但是有很多细节需要考虑，也是本题考查的重点。
 * 1. 开头可能会有 ‘+’ 和 ‘-‘，表示整数的正负。
 * 2. 字符串为 null 或是空（””）呢？
 * 3. 字符串中包含非数字的字符
 * 4. 只有一个 “+” 或 “-“ 字符呢？
 * 最后就是溢出问题。
 *
 * @author neptune
 * @create 2020 04 03 7:25 下午
 */
public class Solution {
    public int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int n = chars.length;
        int idx = 0;
        while (idx < n && chars[idx] == ' ') {
            // 去掉前导空格
            idx++;
        }
        if (idx == n) {
            //去掉前导空格以后到了末尾了
            return 0;
        }
        boolean negative = false;
        if (chars[idx] == '-') {
            //遇到负号
            negative = true;
            idx++;
        } else if (chars[idx] == '+') {
            // 遇到正号
            idx++;
        } else if (!Character.isDigit(chars[idx])) {
            // 其他符号
            return 0;
        }
        int ans = 0;
        while (idx < n && Character.isDigit(chars[idx])) {
            int digit = chars[idx] - '0';
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
                return negative? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 + digit;
            idx++;
        }
        return negative? -ans : ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.myAtoi("   ");
    }
}
