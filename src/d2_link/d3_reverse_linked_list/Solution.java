package d2_link.d3_reverse_linked_list;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public static ListNode reverseList(ListNode head) {
        if(head == null) return null;
        ListNode prevNode = null, curNode, nextNode;
        curNode = head;
        nextNode = curNode.next;
        while(curNode != null){
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = nextNode;
            if(nextNode == null) break;
            nextNode = nextNode.next;
        }
        return prevNode;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode curNode = head;
        int[] vals = {2,3,4,5};
        for (int val : vals) {
            ListNode addNode = new ListNode(val);
            curNode.next = addNode;
            curNode = addNode;
        }
        curNode = head;
        while(curNode !=null){
            System.out.println(curNode.val);
            curNode = curNode.next;
        }
        head = reverseList(head);
        System.out.println("-----------");
        curNode = head;
        while(curNode !=null){
            System.out.println(curNode.val);
            curNode = curNode.next;
        }
    }
}
