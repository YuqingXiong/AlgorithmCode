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