package leetcode.a409_longestPalindrome;

import java.util.HashMap;
import java.util.Map;

/**
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

}
