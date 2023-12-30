package d2_link.d4_swao_node_in_pairs;

public class Solution_recursion {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode swap1Node = head;
        ListNode swap2Node = swap1Node.next;
        ListNode returnNode = swapPairs(swap2Node.next);
        swap1Node.next = returnNode;
        swap2Node.next = swap1Node;
        return swap2Node;
    }
}
