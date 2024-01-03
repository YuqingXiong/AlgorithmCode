package d2_link.d7_linked_list_cycle_ii;

public class Solution2 {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while(true){
            if(fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) break;
        }
        while(head != slow){
            head = head.next;
            slow = slow.next;
        }
        return slow;
    }
}
