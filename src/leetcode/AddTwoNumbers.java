package leetcode;

/**
 * @description: 两数之和
 * 给出两个非空的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字0之外，这两个数都不会以0开头。
 * <p>
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * @author: zhukai
 * @date: 2019/7/16 10:36
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        AddTwoNumbers a = new AddTwoNumbers();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode result = a.addTwoNumbers(l1, l2);
        a.output(result);
    }

    /**
     * 两个链表相加
     * 时间复杂度:O(max(m, n)),m和n分别表示l1和l2的长度,上面的算法最多重复max(m,n)次
     * 空间复杂度:O(max(m, n)),新链表的最大长度为max(m, n)+1
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1;
        ListNode q = l2;
        ListNode dummyHead = new ListNode(0); // 哑节点，为了简化代码
        ListNode curr = dummyHead;
        int carry = 0; // 进位
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        // 最高位相加进位是否大于0
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    /**
     * 输出链表
     *
     * @param head 链表头节点
     */
    public void output(ListNode head) {
        if (head != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(head.val);
            while (head.next != null) {
                head = head.next;
                sb.append("->");
                sb.append(head.val);
            }
            System.out.println(sb.toString());
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
