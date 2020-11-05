package leetcode.a234_isPalindromeList_SIMPLE;

/**
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 *
 * @create 2020 10 23 10:57 下午
 */

import java.util.Stack;

class Solution {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        if (head.next == null) {
            return true;
        }

        // 思路一：使用栈
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        while (head != null) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return stack.isEmpty();
    }

//    public boolean isPalindrome(ListNode head) {
//        if (head == null) {
//            return true;
//        }
//        if (head.next == null) {
//            return true;
//        }
//        if (head.next.next == null) {
//            return head.val == head.next.val;
//        }
//        // 方法二：快慢指针
//        ListNode p1 = head; // slow
//        ListNode p2 = head; // fast
//        while (p2.next != null && p2.next.next != null) {
//            p2 = p2.next.next;
//            p1 = p1.next;
//        }
//        // p3 = pre
//        // p1 = cur
//        // p2 = next
//        ListNode p3 = null;
//        while (p1 != null) {
//            p2 = p1.next;
//            p1.next = p3;
//            p3 = p1;
//            p1 = p2;
//        }
//        p1 = head;
//        p2 = p3;
//        p3 = null;
//        while (p1 != null) {
//            if (p1.val != p2.val) {
//                return false;
//            }
//            p1 = p1.next;
//            p2 = p2.next;
//        }
//        return true;
//    }

}
