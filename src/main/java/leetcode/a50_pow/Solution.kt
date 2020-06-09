package leetcode.a50_pow

import kotlin.math.pow

class Solution {
    fun myPow(x: Double, n: Int): Double {
//        return x.pow(n)
        return Math.pow(x, n.toDouble())
    }
}

fun main(args: Array<String>) {
    var solution = Solution()
    println(solution.myPow(2.0,10))
}