package d5_stack_queue.d1_implement_queue_using_stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyQueue {

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
