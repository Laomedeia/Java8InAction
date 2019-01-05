package leetcode.a2_add_two_numbers;

/**
 * @author neptune
 * @create 2019 01 05 18:54
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode add(ListNode newNextNode) {
        next = newNextNode;
        return next;
    }
}
