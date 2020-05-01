package leetcode.a21_mergeTwoSortedLists;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import leetcode.a2_add_two_numbers.ListNode;

import java.util.Collections;

/**
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @author neptune
 * @create 2020 05 01 9:08 下午
 */
public class Solution {

    /**
     * 其实递归就是程序内部维护了一个栈。这个题就是每次都把最小值压入栈，最后出栈的时候，将所有数连在一起就可以了。
     * 说白了，就是用一个栈维护了顺序。最后的连接，当然是小的连小的，所以l1 小，就连到 l1,l2 小就连到 l2，
     * 最后先返回的，就是最小的头结点。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }

        if(l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }


}
