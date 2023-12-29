package d2_link.d2_design_linked_list;

class MyLinkedList3 {
    private ListNode head;
    private ListNode tail;
    private int size;

    public MyLinkedList3() {
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public int get(int index) {
        if(index < 0 || index >= size) return -1;
        ListNode curNode = head;
        for(int count = 0; count <= index; ++ count){
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
        for(int count = 0; count <= index; ++ count){
            curNode = curNode.next;
        }
        ListNode prevNode = curNode.prev;
        ListNode addNode = new ListNode(val);
        addNode.prev = prevNode;
        addNode.next = curNode;
        prevNode.next = addNode;
        curNode.prev = addNode;
        ++size;
    }

    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size) return;
        ListNode curNode = head;
        for(int count = 0; count <= index; ++ count){
            curNode = curNode.next;
        }
        --size;
        curNode.prev.next = curNode.next;
        curNode.next.prev = curNode.prev;
    }
}