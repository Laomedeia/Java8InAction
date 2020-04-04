package leetcode.a409_longestPalindrome;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 最长回文串
 * @author neptune
 * @create 2020 03 19 11:19 上午
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(7 & 1);
        Solution solution = new Solution();
//        int result = solution.longestPalindrome("abccccdd");
        int result = solution.longestPalindrome("ccc");
        System.out.println(result);
    }

    // 自己的解法
    public int longestPalindrome(String s) {
        int maxLength = 0;
        boolean foundMiddle = false;
        if(s.length() == 1) {
            return 1;
        }
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap();
        for (char c : chars) {
            if(map.get(c) == null) {
                map.put(c, 1);
            } else {
                int num = map.get(c).intValue() + 1;
                map.put(c, num);
            }
        }
        for (Map.Entry<Character, Integer> characterIntegerEntry : map.entrySet()) {
            int value = characterIntegerEntry.getValue();
            if(value == 1 && !foundMiddle) {
                maxLength += 1;
                foundMiddle = true;
            }
            if(value % 2 == 0) {
                maxLength += value;
            } else {
                if(foundMiddle) {
                    maxLength += (value - 1);
                } else {
                    maxLength += value;
                    foundMiddle = true;
                }
            }
        }

        return maxLength;
    }

    // 解法1
    // https://leetcode-cn.com/problems/longest-palindrome/solution/javade-2chong-shi-xian-fang-fa-by-sweetiee/
    public int longestPalindrome1(String s) {
        int[] cnt = new int[58];
        for (char c : s.toCharArray()) {
            cnt[c - 'A'] += 1;
        }

        int ans = 0;
        for (int x: cnt) {
            // 字符出现的次数最多用偶数次。
            ans += x - (x & 1);
        }
        // 如果最终的长度小于原字符串的长度，说明里面某个字符出现了奇数次，那么那个字符可以放在回文串的中间，所以额外再加一。
        return ans < s.length() ? ans + 1 : ans;
    }

    // 解法2
    // https://leetcode-cn.com/problems/longest-palindrome/solution/javade-2chong-shi-xian-fang-fa-by-sweetiee/
    public int longestPalindrome2(String s) {
        Map<Integer, Integer> count = s.chars().boxed()
                .collect(Collectors.toMap(k -> k, v -> 1, Integer::sum));

        int ans = count.values().stream().mapToInt(i -> i - (i & 1)).sum();
        return ans < s.length() ? ans + 1 : ans;
    }

}
