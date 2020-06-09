package leetcode.a69_sqrtX

/**
 * 求给定一个数的平方根
 */
class Solution {
    fun mySqrt(x: Int): Int {
        var left: Long = 0
        var right:Long = Integer.MAX_VALUE.toLong()
        while (left < right) {
            // 这种取中位数的方法又快又好，是我刚学会的，原因在下面这篇文章的评论区
            // 注意：这里得用无符号右移
//            val mid = (left + right + 1) >>> 1;
            val mid = (left + right + 1) ushr  1
            val square = mid * mid;
            if (square > x) {
                right = mid - 1
            } else {
                left = mid
            }
        }
        return left.toInt()
    }
}

fun main(args: Array<String>) {
    val solution = Solution()
    println(solution.mySqrt(49))
}