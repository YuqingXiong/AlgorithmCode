# 203. 移除链表元素
https://leetcode.cn/problems/remove-linked-list-elements/description/

## 方法一：迭代
迭代遍历链表

注意：这里的head是指向第一个节点的（首元节点），并没有一个虚拟的头节点，所以这让删除节点元素变得有些麻烦

我们创建一个新的头节点的头节点preHead，preHead指向真正的节点 head，这样便于删除元素

删除方式：

例如删除 nextNode 节点： curNode -> nextNode -> nextNextNode

则：curNode.next = curNode.next.next; // 跳过了中间的nextNode节点，达到删除目的

这样原本的head节点变成了preHead.next，便于删除头节点

这里的循环判断条件是 curNode.next != null

```java
public class Solution {
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
```

## 方式二：递归

一直向下搜索节点，直到节点为 null，则返回；所以对于最后一个节点他的next指向一定为null

对于不是最后一个节点的，它的next等于下面一个return 返回的值，而返回的值由next指向的节点的值决定

如果next指向节点的值是val则说明，这个指向的节点需要跳过，所以返回的是next的next，如果不是val，则返回next指向的本身


```java
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return null;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
}
```

## 我的代码：
```java
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
```
# 707.设计链表
https://leetcode.cn/problems/design-linked-list/description/
## 单链表实现
单链表的创建需要一个头节点，它不存储值但指向第一个元素，便于后续的增删改查操作的实现

注意：
- 复用 addAtIndex 函数实现添加头尾节点的功能
```java
class ListNode{
    int val;
    ListNode next;
    public ListNode(int val){this.val = val;this.next = null;}
}

public class MyLinkedList {
    ListNode head;
    int size;
    public MyLinkedList(){
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
```
## 双链表实现
双链表的实现，需要头，尾节点
注意：
- 双链表则可以将头或尾节点作为起始点进行查找
- 将链表分为两半，判断当前需要查找的节点距离哪一端更近，可以遍历更少的节点

```java
class ListNode{
    int val;
    ListNode next;
    ListNode prev;
    public ListNode(int val){this.val = val;this.next = null;this.prev = null;}
}

class MyLinkedList {
    private ListNode head;
    private ListNode tail;
    private int size;

    public MyLinkedList() {
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
```

## 我的实现
没有注意双链表的特性，依然用单方向的遍历实现
```java
class ListNode{
    int val;
    ListNode next;
    ListNode prev;
    public ListNode(int val){this.val = val;this.next = null;this.prev = null;}
}

class MyLinkedList {
    private ListNode head;
    private ListNode tail;
    private int size;

    public MyLinkedList() {
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
```