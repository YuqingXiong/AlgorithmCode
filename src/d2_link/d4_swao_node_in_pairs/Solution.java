package d2_link.d4_swao_node_in_pairs;
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
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode prevNode = null, swap1Node = head, swap2Node = head.next, nextNode;
        while(swap1Node != null && swap1Node.next != null){
            swap2Node = swap1Node.next;

            if(prevNode == null) head = swap2Node;
            else prevNode.next = swap2Node;

            swap1Node.next = swap2Node.next;
            swap2Node.next = swap1Node;

            prevNode = swap1Node;
            swap1Node = swap1Node.next;
            // System.out.println(swap1Node.val);
        }
        return head;
    }
}
