package d2_link.remove_nth_node_from_end_of_list;

import java.util.Deque;
import java.util.LinkedList;

public class StackSolution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode dummy = new ListNode(0, head); // 添加一个哑元节点
        ListNode curNode = dummy;
        while(curNode != null){
            stack.push(curNode);
            curNode = curNode.next;
        }

        for(int i = 0; i < n; ++ i){
            stack.pop();
        }

        ListNode prevNode = stack.peek();
        prevNode.next = prevNode.next.next;
        return dummy.next;
    }
}
