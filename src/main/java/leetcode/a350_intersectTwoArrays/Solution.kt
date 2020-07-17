package leetcode.a350_intersectTwoArrays

import java.util.*
import kotlin.math.min

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 *
 * 示例 2:
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *  
 *
 * 说明：
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 * @author neptune
 * @create 2020 07 13 10:39 下午
 */
class Solution {

    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
//        return nums2.intersect(nums1.asIterable()).toIntArray()
        nums1.sort()
        nums2.sort()
        val length1 = nums1.size
        val length2 = nums2.size
        val intersection = IntArray(min(length1, length2))
        var index1 = 0
        var index2 = 0
        var index = 0
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++
            } else if (nums1[index1] > nums2[index2]) {
                index2++
            } else {
                intersection[index] = nums1[index1]
                index1++
                index2++
                index++
            }
        }
        return Arrays.copyOfRange(intersection, 0, index)
    }
}

fun main(args: Array<String>) {
    val solution = Solution()
    val nums1:IntArray = arrayOf(1,2,2,1).toIntArray()
    val nums2:IntArray = arrayOf(2).toIntArray()
//    val nums1:IntArray = arrayOf(1,2,2,1).toIntArray()
//    val nums2:IntArray = arrayOf(2,2).toIntArray()
    solution.intersect(nums1,nums2).iterator().forEach { el -> println(el) }
}