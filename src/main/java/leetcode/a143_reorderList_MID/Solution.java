package leetcode.a143_reorderList_MID;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 143. 重排链表
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3
 *
 * @create 2020 10 20 5:38 下午
 */
public class Solution {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // 解法 1：双指针
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        ListNode slowp = head;
        ListNode fastp = head;

        // 当快指针到达链表末尾，慢指针恰好到达链表中点之前的节点
        while (fastp.next != null && fastp.next.next != null) {
            slowp = slowp.next;
            fastp = fastp.next.next;
        }

        // 拟造一个 “新链表头”，将原链表分为两个“等长”链表
        ListNode newHead = slowp.next;
        slowp.next = null;

        newHead = reverseList(newHead);

        // 将两半链表，交相穿插拼接
        while (newHead != null) {
            ListNode temp = newHead.next;
            newHead.next = head.next;
            head.next = newHead;
            head = newHead.next;
            newHead = temp;
        }
    }

    /**
     * 倒置 链表
     * @param head
     * @return
     */
    private ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode tail = head;
        head = head.next;
        tail.next = null;

        while (head != null) {
            ListNode temp = head.next;
            head.next = tail;
            tail = head;
            head = temp;
        }

        return tail;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = null;

        Solution solution = new Solution();
        solution.reorderList(listNode1);

    }



    // 解法 2：双向链表
//    public void reorderList(ListNode head) {
//        if (head == null) {
//            return;
//        }
//        Deque<ListNode> deque = new LinkedList<>();
//        ListNode next = head.next;
//        while (next != null) {
//            deque.add(next);
//            next = next.next;
//        }
//
//        while (!deque.isEmpty()) {
//            head.next = deque.pollLast();
//            head = head.next;
//
//            if (!deque.isEmpty()) {
//                head.next = deque.pollFirst();
//                head = head.next;
//            }
//        }
//        head.next = null;
//    }


}
