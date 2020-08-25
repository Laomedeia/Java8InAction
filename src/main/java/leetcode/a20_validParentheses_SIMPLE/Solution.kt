package leetcode.a20_validParentheses_SIMPLE

import java.util.*


/**
有效的括号

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
有效字符串需满足：
左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:
输入: "()"
输出: true

示例 2:
输入: "()[]{}"
输出: true

示例 3:
输入: "(]"
输出: false

示例 4:
输入: "([)]"
输出: false

示例 5:
输入: "{[]}"
输出: true

 */
class Solution {
    fun isValid(s: String): Boolean {
        if (s.isEmpty()) return true
        val stack = Stack<Char>()
        for (c in s.toCharArray()) {
            if (c == '(')
                stack.push(')')
            else if (c == '{')
                stack.push('}')
            else if (c == '[')
                stack.push(']')
            else if (stack.empty() || c != stack.pop())
                return false
        }
        return stack.empty()
    }
}