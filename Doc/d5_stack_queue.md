# 232.用栈实现队列

Queue 是 Collection 接口下的，她的一个实现类是ArrayDeque.

不推荐使用 Vector 实现的 Stack，因为为了保证线程安全使得 Stack 的效率很低，而且由于继承的 Vector 导致没有屏蔽一些栈不应该有的操作

-----

两个栈实现队列：
- 一个栈用于放数据
- 一个栈用于读数据，这时读出的数据是按照队列的顺序的，
- 所以每次读的时候就先判断读数据的栈是否为空，先从读数据的栈中拿数据，如果为空，则把存数据的栈倒入读数据的栈
- 每次读完不需要再把数据倒回去，因为是优先从读数据的栈中取数据的，后加入的数据不会和之前的数据混乱，因为之前的数据读完了才能轮到后面的数据

```java
class MyQueue {

    private Deque<Integer> inStack;
    private Deque<Integer> outStack;

    public MyQueue() {
        inStack = new ArrayDeque<Integer>();
        outStack = new ArrayDeque<Integer>();
    }
    
    public void push(int x) {
        inStack.push(x);
    }

    private void inToOut(){
        while(!inStack.isEmpty()){
            outStack.push(inStack.pop());
        }
    }
    
    public int pop() {
        if(outStack.isEmpty()){
            inToOut();
        }
        return outStack.pop();
    }
    
    public int peek() {
        if(outStack.isEmpty()){
            inToOut();
        }
        return outStack.peek();
    }
    
    public boolean empty() {
        if(outStack.isEmpty() && inStack.isEmpty()){
            return true;
        }
        return false;
    }
}
```