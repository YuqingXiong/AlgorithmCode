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
# 206.反转链表
https://leetcode.cn/problems/reverse-linked-list/

用三个节点分别保存前一个节点，当前节点，后一个节点

反转后的连接关系：
**nextNode = curNode.next**

curNode.next = prevNode

prevNode = curNode

curNode = nextNode

提前存储 nextNode 可以简化代码

因为遍历到最后一个元素时 curNode 会指向 null，而 nextNode 就无法获取了，如果写在最后，就会报错

另一个角度：

通过curNode获取nextNode更好，可以用curNode是否为空判断是否有nextNode

通过nextNode获取nextNode还需要额外判断nextNode是否为空

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null) return null;
        ListNode prevNode = null, curNode, nextNode;
        curNode = head;
        while(curNode != null){
            nextNode = curNode.next;
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = nextNode;
        }
        return prevNode;
    }
}
```


## 我的代码
```java
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null) return null;
        ListNode prevNode = null, curNode, nextNode;
        curNode = head;
        nextNode = curNode.next;
        while(curNode != null){
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = nextNode;
            if(nextNode == null) break;
            nextNode = nextNode.next;
        }
        return prevNode;
    }
}
```

# 24.两两交换链表中的节点
## 递归实现
一条链表，从头节点开始，以两个节点为单位的遍历，当遍历到链表末端，没有节点或者只有一个节点的时候返回当前节点

此时返回的节点returnNode是前两个节点的下一个节点，设前两个节点是 swap1Node，swap2Node

**它们的关系是：**

swap1Node，swap2Node = swap1Node.next，returnNode = swap2Node.next

swap1Node->swap2Node->returnNode

**当交换swap1Node和swap2Node时：**

swap1Node.next = returnNode

swap2Node.next = swap1Node  

此时swap2Node 被换到前面去了，所以作为了新的头节点，将作为前两个的下一个节点返回
```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode swap1Node = head;
        ListNode swap2Node = swap1Node.next;
        ListNode returnNode = swapPairs(swap2Node.next);
        swap1Node.next = returnNode;
        swap2Node.next = swap1Node;
        return swap2Node;
    }
}
```


## 我的代码（迭代）
prevNode->swap1Node->swap2Node->nextNode

交换swap1Node和swap2Node的逻辑：

prevNode.next = swap2Node;

swap1Node.next = swap2Node.next;

swap2Node.next = swap1Node;

prevNode不好处理，因为第一个节点没有prevNode，这里需要特殊判断，或者增加一个哑节点

```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode prevNode = null, swap1Node = head, swap2Node = head.next, nextNode;
        while(swap1Node != null && swap1Node.next != null){
            swap2Node = swap1Node.next;
            
            if(prevNode == null) head = swap2Node;
            else prevNode.next = swap2Node;

            swap1Node.next = swap2Node.next;
            swap2Node.next = swap1Node;

            prevNode = swap1Node;
            swap1Node = swap1Node.next;
            // System.out.println(swap1Node.val);
        }
        return head;
    }
}
```

# 19.删除链表的倒数第N个节点

https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/

解法的前提是只遍历一遍链表

## 栈

把所有元素都添加进栈里面，然后弹出 n 个元素，最后栈顶的元素就是需要删除节点的前驱节点

添加哑元节点 dummy -> head

可以避免判断删除头节点的情况

```java
class Solution {
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
```

## 快慢指针

两个指针一个在前一个在后，前面的指针先走 n 步，后面的指针再和前面的指针同步，当到达末尾时后面的指针就执行了删除节点

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head); // 添加一个哑元节点
        ListNode first = head;
        for(int i = 0; i < n; ++ i){
            first = first.next;
        }

        ListNode second = dummy;
        while(first != null){
            first = first.next;
            second = second.next;
        }
        
        second.next = second.next.next;
        return dummy.next;
    }
}
```

## 我的代码

利用递归实现的栈：
```java
class Solution {
    private int back = 0;
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return head;
        
        head.next = removeNthFromEnd(head.next, n);
        ++back;
        
        if(n == back) return head.next;
        return head;
    }
}
```