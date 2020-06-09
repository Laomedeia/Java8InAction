package leetcode.a680_validPalindrome;

/**
 * 验证回文字符串 Ⅱ
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * 示例 1:
 *
 * 输入: "aba"
 * 输出: True
 * 示例 2:
 *
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 * 注意:
 *
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 *
 * @author neptune
 * @create 2020 05 19 9:44 下午
 */
public class Solution {

    private boolean flag = false;

    public boolean validPalindrome(String s) {
        if (s.isEmpty()) return true;
        int len = s.length();
        if (len == 1) return true;
        int left = 0, right = len - 1;
        while (left < right){
            if (s.charAt(left) != s.charAt(right)){
                if (flag) return false;
                flag = true;

                return validPalindrome(s.substring(left+1, right+1)) || validPalindrome(s.substring(left, right));
            }

            left++;
            right--;
        }

        return true;
    }

}
