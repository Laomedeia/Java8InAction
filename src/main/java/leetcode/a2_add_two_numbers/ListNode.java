package leetcode.a2_add_two_numbers;

/**
 * @author neptune
 * @create 2019 01 05 18:54
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    ListNode add(ListNode newNextNode) {
        next = newNextNode;
        return next;
    }

}
