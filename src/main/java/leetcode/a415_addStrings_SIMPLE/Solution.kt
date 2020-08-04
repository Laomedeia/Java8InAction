package leetcode.a415_addStrings_SIMPLE

/**
 * 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

提示：
num1 和num2 的长度都小于 5100
num1 和num2 都只包含数字 0-9
num1 和num2 都不包含任何前导零
你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式

 */
class Solution {
    fun addStrings(num1: String, num2: String): String {
        var res = StringBuilder("")
        var i = num1.length - 1
        var j = num2.length - 1
        var carry = 0
        while(i >= 0 || j >= 0){
            var n1 = if(i >= 0) num1[i] - '0' else 0
            var n2 = if(j >= 0) num2[j] - '0' else 0
            var tmp = n1 + n2 + carry
            carry = tmp / 10
            res.append(tmp % 10)
            i--
            j--
        }
        if(carry == 1) res.append(1);
        return res.reverse().toString();
    }
}


fun main(args: Array<String>) {
    println('a'.toInt())
    println('0'.toInt())
    println('a' - '0')
}