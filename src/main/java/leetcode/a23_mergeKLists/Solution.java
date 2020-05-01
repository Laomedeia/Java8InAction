package leetcode.a23_mergeKLists;

import leetcode.a2_add_two_numbers.ListNode;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * @author neptune
 * @create 2020 04 26 9:55 下午
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> pq = new PriorityQueue<>((v1, v2) -> v1.val - v2.val);
        for (ListNode node: lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = minNode;
            if (minNode.next != null) {
                // 此时 offer 单个节点值又会按 value 值正常排序
                pq.offer(minNode.next);
            }
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode[] listNodes = new ListNode[3];
        listNodes[0] = new ListNode(1);
        listNodes[0].next = new ListNode(4);
        listNodes[0].next.next = new ListNode(5);

        listNodes[1] = new ListNode(1);
        listNodes[1].next = new ListNode(3);
        listNodes[1].next.next = new ListNode(4);

        listNodes[2] = new ListNode(2);
        listNodes[2].next = new ListNode(6);

        System.out.println(solution.mergeKLists(listNodes));
    }
}
