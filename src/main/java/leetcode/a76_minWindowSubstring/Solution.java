package leetcode.a76_minWindowSubstring;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 最小覆盖子串
 *
 *  给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 *
 * 示例：
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * @author neptune
 * @create 2020 05 23 10:16 下午
 */
public class Solution {
    Map<Character, Integer> ori = new HashMap<Character, Integer>();
    Map<Character, Integer> cnt = new HashMap<Character, Integer>();

    /**
     * 滑动窗口解法 1
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public boolean check() {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

    /**
     * 滑动窗口解法 2
     * @param s
     * @param t
     * @return
     */
    public String minWindow2(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) return "";
        // 定义一个数字，用来记录字符串 t 中出现字符的频率，也就是窗口内需要匹配的字符和相应的频率
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        int left = 0, right = 0;
        int match = 0;  // 匹配字符的个数
        int minLen = s.length() + 1;   // 最大的子串的长度
        // 子串的起始位置 子串结束的位置(如果不存在这样的子串的话，start，end 都是 0，s.substring 截取就是 “”
        int start = 0, end = 0;
        while (right < s.length()){
            char charRight = s.charAt(right); // 右边界的那个字符
            map[charRight]--;   // 可以理解为需要匹配的字符 charRight 减少了一个
            // 如果字符 charRight 在 t 中存在，那么经过这一次操作，只要个数大于等于 0，说明匹配了一个
            // 若字符 charRight 不在 t 中，那么 map[charRight] < 0, 不进行任何操作
            if (map[charRight] >= 0) match++;
            right++;  // 右边界右移，这样下面就变成了 [)，方便计算窗口大小

            // 只要窗口内匹配的字符达到了要求，右边界固定，左边界收缩
            while (match == t.length()){
                int size = right - left;
                if (size < minLen){
                    minLen = size;
                    start = left;
                    end = right;
                }
                char charLeft = s.charAt(left);  // 左边的那个字符
                map[charLeft]++;  // 左边的字符要移出窗口
                // 不在 t 中出现的字符，移出窗口，最终能够达到的最大值 map[charLeft] = 0
                // 如果恰好移出了需要匹配的一个字符，那么这里 map[charLeft] > 0, 也就是还要匹配字符 charLeft，此时 match--
                if (map[charLeft] > 0) match--;
                left++;  // 左边界收缩
            }
        }
        return s.substring(start, end);
    }


}
