package d2_link.d2_design_linked_list;

public class MyLinkedList2 {
    ListNode head;
    int size;
    public MyLinkedList2(){
        size = 0;
        head = new ListNode(0);
    }

    public int get(int index) {
        if(index < 0 || index >= size) return -1;
        ListNode curNode = head;
        for(int count = 0; count <= index; ++count){
            curNode = curNode.next;
        }
        return curNode.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
       addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if(index < 0 || index > size) return;
        ListNode curNode = head;
        // 注意这里 count 没有 <= index，所以取的是index前一个节点
        for(int count = 0; count < index; ++ count){
            curNode = curNode.next;
        }
        ListNode addNode = new ListNode(val);
        ++size;
        addNode.next = curNode.next;
        curNode.next = addNode;

    }

    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size) return;
        ListNode curNode = head;
        for(int count = 0; count < index; ++ count){
            curNode = curNode.next;
        }
        curNode.next = curNode.next.next;
        --size;
    }
}
