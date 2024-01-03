package d2_link.d5_remove_nth_node_from_end_of_list;
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}
public class Solution {
    private int back = 0;
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return head;

        head.next = removeNthFromEnd(head.next, n);
        ++back;

        if(n == back) return head.next;
        return head;
    }
}
