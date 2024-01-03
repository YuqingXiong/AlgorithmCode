package d2_link.d7_linked_list_cycle_ii;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode fast = head.next.next, slow = head.next;
        while(fast != slow){
            if(fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
        }
        System.out.println(slow.val);
        while(head != slow){
            head = head.next;
            slow = slow.next;
        }
        return slow;
    }
}
