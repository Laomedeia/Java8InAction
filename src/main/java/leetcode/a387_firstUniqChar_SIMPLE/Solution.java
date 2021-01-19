package leetcode.a387_firstUniqChar_SIMPLE;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 387. 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 示例：
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 *
 * @create 2020 12 23 5:58 下午
 */
public class Solution {

//    public int firstUniqChar(String s) {
//        if(s == null || "".equals(s.trim())) {
//            return -1;
//        }
//        Set<Integer> items = new HashSet<>();
////        s.chars().filter(i -> Collections.frequency(s, i) >1)
////                .collect(Collectors.toSet())
//        Set<Integer> myset = s.chars().boxed().filter(n -> !items.add(n)).collect(Collectors.toSet());
//        String result = s;
//        for (Integer el : myset) {
//            char duplicatedChar = (char)el.intValue();
//            result = result.replaceAll(duplicatedChar+"","");
//        }
//        System.out.println(result);
//        if(result.trim().length() == 0) {
//            return -1;
//        }
//        System.out.println(result.charAt(0) - 'a');
//        return s.indexOf(result.charAt(0));
//    }

    /**
     * 一个从前查找，一个从后查找，如果下标相等，说明只出现了一次
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++)
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i)))
                return i;
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.firstUniqChar("loveleetcode"));
    }

}
