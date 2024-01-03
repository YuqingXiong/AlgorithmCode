package d2_link.intersection_of_two_linked_lists_lcci;

public class Solution2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode curNodeA = headA;
        ListNode curNodeB = headB;
        while(curNodeA != curNodeB){
            curNodeA = curNodeA == null ? headB : curNodeA.next;
            curNodeB = curNodeB == null ? headA : curNodeB.next;
        }
        return curNodeA;
    }
}
