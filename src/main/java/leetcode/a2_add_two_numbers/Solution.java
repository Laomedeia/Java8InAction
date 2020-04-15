package leetcode.a2_add_two_numbers;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * @author neptune
 * @create 2019 01 05 18:53
 */
public class Solution {

    // 官方解法 (初等数学)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0; // 进位
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;   // 相加是否有进位，有赋值给 carry
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

//    第一次解法(完全按照题意先逆向再相加再转换字符串)
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        Deque<Integer> val_1 = new ArrayDeque();
//        Deque<Integer> val_2 = new ArrayDeque();
//        val_1.push(l1.val);
//        val_2.push(l2.val);
//        while (l1.next != null) {
//            val_1.push(l1.next.val);
//            l1 = l1.next;
//        }
//        while (l2.next != null) {
//            val_2.push(l2.next.val);
//            l2 = l2.next;
//        }
//        // 反转计算
//        BigInteger x = reverseListAndOutputNum(val_1);
//        BigInteger y = reverseListAndOutputNum(val_2);
//        BigInteger z = x.add(y);
//
//        // 输出 ListNode
//        return outputListNode(z);
//    }
//
//    public BigInteger reverseListAndOutputNum(Deque<Integer> val) {
//        StringBuilder sb = new StringBuilder();
//        val.stream().mapToInt(data -> data).forEach(i -> sb.append(i));
//        return new BigInteger(sb.toString());
//    }
//
//    public ListNode outputListNode(BigInteger result) {
//        ListNode listNode = null;
//        ListNode parentNode = null;
//        String Str = new StringBuilder(String.valueOf(result)).reverse().toString();
//        for (int i = 0; i < Str.length(); i++) {
//            ListNode tempNode = new ListNode(Integer.parseInt(String.valueOf(Str.charAt(i))));
//            if(i == 0) {
//                listNode = tempNode;
//            } else {
//                if(parentNode != null) {
////                    parentNode = parentNode.add(tempNode);
//                    parentNode.next = tempNode;
//                    parentNode = parentNode.next;
//                } else {
////                    parentNode = listNode.add(tempNode);
//                    listNode.next = tempNode;
//                    parentNode = listNode.next;
//                }
//            }
//        }
//        return listNode;
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listNode1 = new ListNode(2);
        ListNode listNode12 = new ListNode(4);
        ListNode listNode13 = new ListNode(3);
        listNode12.next = listNode13;
        listNode1.next = listNode12;

        ListNode listNode2 = new ListNode(5);
        ListNode listNode22 = new ListNode(6);
        ListNode listNode23 = new ListNode(4);
        listNode22.next = listNode23;
        listNode2.next = listNode22;

        ListNode listResult = solution.addTwoNumbers(listNode1, listNode2);
        System.out.println(listResult.val + "->" + listResult.next.val + "->" + listResult.next.next.val);
    }
}
