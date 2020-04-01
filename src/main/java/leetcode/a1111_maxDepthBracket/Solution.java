package leetcode.a1111_maxDepthBracket;

import java.util.Arrays;

/**
 * @author neptune
 * @create 2020 04 01 8:57 下午
 */
public class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int[] ans = new int[seq.length()];
        int idx = 0;
        for (char c : seq.toCharArray()) {
            ans[idx++] = c == '(' ? idx & 1 : ((idx + 1) & 1);
        }
        return ans;
    }

    /**
     * 输入：seq = "(()())"
     * 输出：[0,1,1,1,1,0]
     *
     * 输入：seq = "()(())()"
     * 输出：[0,0,0,1,1,0,1,1]
     * 解释：本示例答案不唯一。
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        String seq = "()(())()";
        int[] ans = solution.maxDepthAfterSplit(seq);
        Arrays.stream(ans).forEach(c -> System.out.println(c));
    }
}