package d2_link.d2_design_linked_list;

public class MyLinkedList {
    ListNode head;
    int size;

    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
    }

    public int get(int index) {
        if(index < 0 || index >= size) return -1;
        int count = 0;
        ListNode tempHead = head.next;
        while(tempHead != null){
            if(count == index){
                return tempHead.val;
            }
            ++count;
            tempHead = tempHead.next;
        }
        return -1;
    }

    public void addAtHead(int val) {
        ListNode addNode = new ListNode(val);
        addNode.next = head.next;
        head.next = addNode;
        ++size;
    }

    public void addAtTail(int val) {
        ListNode addNode = new ListNode(val);
        ListNode tempHead = head;
        while(tempHead.next != null) tempHead = tempHead.next;
        tempHead.next = addNode;
        ++size;
    }

    public void addAtIndex(int index, int val) {
        if(index < 0 || index > size) return;
        ListNode addNode = new ListNode(val);
        ListNode indexNode = head.next;
        ListNode preNode = head;
        int count = 0;
        while(indexNode != null){
            if(index == count){
                break;
            }
            preNode = indexNode;
            indexNode = indexNode.next;
            ++count;
        }
        ++size;
        if(index != count){
            indexNode.next = addNode;
            return;
        }
        preNode.next = addNode;
        addNode.next = indexNode;
    }

    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size) return;
        ListNode indexNode = head.next;
        ListNode preNode = head;
        int count = 0;
        while(indexNode.next != null){
            if(index == count){
                break;
            }
            preNode = indexNode;
            indexNode = indexNode.next;
            ++count;
        }
        preNode.next = indexNode.next;
        --size;
    }
}