package Linkedlist;

public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // k个一组翻转链表
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p0 = dummy;
        while (count >= k) {
            count -= k;
            ListNode pre = null, curr = p0.next;
            for (int i = 0; i < k; i++) {
                ListNode nxt = curr.next;
                curr.next = pre;
                pre = curr;
                curr = nxt;
            }
            ListNode nxt = p0.next;
            p0.next.next = curr;
            p0.next = pre;
            p0 = nxt;
        }
        return dummy.next;
    }
}
