package d2_link.d1_remove_linked_list_elements;


class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;
        ListNode curNode = head;
        ListNode preNode = null;
        while(curNode != null){
            if(curNode.val == val){
                if(preNode == null){
                    head = curNode.next;
                }
                else{
                    preNode.next = curNode.next;
                    curNode = preNode;
                }
            }
            if(head != curNode.next) preNode = curNode;
            curNode = curNode.next;
        }
        return head;
    }
}
