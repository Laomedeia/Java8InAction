package leetcode.a151_reverseWordsInString;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * @author neptune
 * @create 2020 04 10 8:55 下午
 */
public class Solution {

    public String reverseWords(String s) {
        s = s.trim();
        if ("".equals(s)) return "";
        String[] array = s.split("\\s+");

//        List<String> listArray = new ArrayList<>();
//        for (int i = array.length - 1; i > -1 ; i--) {
//            listArray.add(array[i]);
//        }
        List<String> wordList = Arrays.asList(array);
        Collections.reverse(wordList);
        String output = String.join(" ", wordList);
//        String output = wordList.stream().collect(Collectors.joining(" "));
        System.out.println(output);
        return output;
    }


    /**
     * 利用双端队列求解
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') ++left;

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') --right;

        Deque<String> d = new ArrayDeque();
        StringBuilder word = new StringBuilder();

        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
                // 将单词 push 到队列的头部
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        d.offerFirst(word.toString());

        return String.join(" ", d);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.reverseWords("a good   example");
        solution.reverseWords("  hello world!  ");
        solution.reverseWords("the sky is blue");

    }
}
