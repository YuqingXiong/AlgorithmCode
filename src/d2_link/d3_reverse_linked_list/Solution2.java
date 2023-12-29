package d2_link.d3_reverse_linked_list;

public class Solution2 {
    public static ListNode reverseList(ListNode head) {
        if(head == null) return null;
        ListNode prevNode = null, curNode, nextNode;
        curNode = head;
        while(curNode != null){
            nextNode = curNode.next;
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = nextNode;
        }
        return prevNode;
    }
}