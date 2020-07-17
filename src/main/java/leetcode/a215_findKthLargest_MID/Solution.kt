package leetcode.a215_findKthLargest_MID

import java.util.*

/**
 * 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * @author neptune
 * @create 2020 06 29 4:58 下午
 */
class Solution {

    /**
     * 可以使用最小堆来解决，一个个遍历原数组的值，添加到堆中，如果堆中元素的个数小于等于k的时候，
     * 我们就往堆中添加，添加之后如果堆中元素个数大于k的时候，我们就把最顶端的元素给移除掉，因为是最小堆，所以移除的就是堆中最小的值
     */
    fun findKthLargest(nums: IntArray, k: Int): Int {
        var queue: PriorityQueue<Int>  = PriorityQueue()
        for (vara in nums) {
            queue.add(vara);
            if (queue.size > k)
                queue.poll();
        }
        return queue.peek();
    }

}

fun main(args: Array<String>) {
    val solution = Solution()
    val arr = intArrayOf(3,2,1,5,6,4)
    println(solution.findKthLargest(arr, 2))
}