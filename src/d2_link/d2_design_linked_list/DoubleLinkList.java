package d2_link.d2_design_linked_list;

public class DoubleLinkList {
    private ListNode head;
    private ListNode tail;
    private int size;

    public DoubleLinkList() {
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public int get(int index) {
        if(index < 0 || index >= size) return -1;
        ListNode curNode;
        if(index + 1 < size - index){ // 在前半部分
            curNode = head;
            for(int i = 0; i <= index; ++i){
                curNode = curNode.next;
            }
        }else{ // 从后半部分遍历
            curNode = tail;
            for(int i = 0; i < size - index; ++i){
                curNode = curNode.prev;
            }
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
        ListNode curNode, prevNode;
        if(index + 1 < size - index){
            curNode = head;
            for(int count = 0; count <= index; ++ count){
                curNode = curNode.next;
            }
            prevNode = curNode.prev;
        }else{
            curNode = tail;
            for(int i = 0; i < size - index; ++i){
                curNode = curNode.prev;
            }
            prevNode = curNode.prev;
        }
        ListNode addNode = new ListNode(val);
        addNode.prev = prevNode;
        addNode.next = curNode;
        prevNode.next = addNode;
        curNode.prev = addNode;
        ++size;
    }

    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size) return;
        ListNode curNode;
        if(index + 1 < size - index){
            curNode = head;
            for(int i = 0; i <= index; ++i){
                curNode = curNode.next;
            }
        }else{
            curNode = tail;
            for(int i = 0; i < size - index; ++i){
                curNode = curNode.prev;
            }
        }
        --size;
        curNode.prev.next = curNode.next;
        curNode.next.prev = curNode.prev;
    }
}
