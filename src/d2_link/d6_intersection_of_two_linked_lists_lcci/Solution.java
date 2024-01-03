package d2_link.d6_intersection_of_two_linked_lists_lcci;
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode tempB = headB, tempA = headA;
        while(headA != null && headB != null){
            headA = headA.next;
            headB = headB.next;
        }
        if(headA == null) headA = tempB;
        if(headB == null) headB = tempA;
        while(headA != null && headB != null){
            headA = headA.next;
            headB = headB.next;
        }
        if(headA == null) headA = tempB;
        if(headB == null) headB = tempA;
        while(headA != null && headB != null){
            if(headA == headB) return headA;
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
}
