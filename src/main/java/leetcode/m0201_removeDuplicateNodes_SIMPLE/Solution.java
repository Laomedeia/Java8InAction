package leetcode.m0201_removeDuplicateNodes_SIMPLE;


import java.util.*;

/**
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 *
 * 示例1:
 *  输入：[1, 2, 3, 3, 2, 1]
 *  输出：[1, 2, 3]
 * 示例2:
 *
 *  输入：[1, 1, 1, 1, 2]
 *  输出：[1, 2]
 * 提示：
 *
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 *
 * @author neptune
 * @create 2020 06 26 10:29 上午
 */
public class Solution {

     public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
     }

    public ListNode removeDuplicateNodes(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode nextNode = head.next;
        if(nextNode == null) {
            return head;
        }
        Set<Integer> valSet = new HashSet<>(16);
        valSet.add(head.val);
        ListNode parentNode = head;
        while (nextNode != null) {
            if(valSet.contains(nextNode.val)) {
                parentNode.next = nextNode.next;
            } else {
                parentNode = nextNode;
                valSet.add(nextNode.val);
            }
            nextNode = nextNode.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(3);
        ListNode listNode5 = new ListNode(2);
        ListNode listNode6 = new ListNode(1);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        ListNode resultNode = solution.removeDuplicateNodes(listNode);
        System.out.println(resultNode);
    }
    
    
}
