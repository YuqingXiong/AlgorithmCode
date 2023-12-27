package d2_link.d1_remove_linked_list_elements;

public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode preHead = new ListNode(0, head);
        ListNode curNode = preHead;
        while(curNode.next != null){
            if(curNode.next.val == val){
                curNode.next = curNode.next.next;
            }else{
                curNode = curNode.next;
            }
        }
        return preHead.next;
    }
}
