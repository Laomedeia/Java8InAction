package leetcode.a347_topKFrequent_MID;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 前 K 个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 *  示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *  
 */
public class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        // 统计每个数字出现的次数
        Map<Integer, Integer> counter = IntStream.of(nums).boxed().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        // 定义小根堆，根据数字频率自小到大排序
        Queue<Integer> pq = new PriorityQueue<>((v1, v2) -> counter.get(v1) - counter.get(v2));
        // 遍历数组，维护一个大小为 k 的小根堆：
        // 不足 k 个直接将当前数字加入到堆中；否则判断堆中的最小次数是否小于当前数字的出现次数，若是，则删掉堆中出现次数最少的一个数字，将当前数字加入堆中。
        counter.forEach((num, cnt) -> {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (counter.get(pq.peek()) < cnt) {
                pq.poll();
                pq.offer(num);
            }
        });
        // 构造返回结果
        int[] res = pq.stream().mapToInt(c->c.intValue()).toArray();
        return res;
    }

}
