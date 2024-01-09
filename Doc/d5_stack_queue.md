# 232.用栈实现队列

Queue 是 Collection 接口下的，她的一个实现类是ArrayDeque.

不推荐使用 Vector 实现的 Stack，因为为了保证线程安全使得 Stack 的效率很低，而且由于继承的 Vector 导致没有屏蔽一些栈不应该有的操作

- stack 下使用入栈出栈的Api是 push 和 pop

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
# 225.用队列实现栈

用两个队列实现栈，第一个队列用于按照栈的顺序存放队列，当来一个新数据 x 如需要入栈的时候，我们要把这个数据 x 放在队列的最前面

队列的实现方式：LinkedList 实现了 Deque 和 List 两个接口

Deque是双端队列，既可以当作栈，也可以当作队列，当作队列使用时：
- 入队尾用 `offer()` / `add()`
- 出队头用 `poll()`
- 与 add() push() pop() 的不同在与 
  - offer/poll不会抛出异常
  - push() 是针对栈的操作，如果在队列中使用的话，添加的元素是加载队头的
- 
```java
Deque<Integer> queue = new LinkedList<>();
```

## 实现方法一：
- queue1 把所有的数据先倒入 queue2，然后把 x 放入 queue1 里
- 然后再把 queue2 里的数据倒回 queue1，这样新加入的数据就在 queue1 队列头部
- 问题是这样原本的数据在两个队列中倒了两次（移动了两次）

```java
class MyStack {
    private Deque<Integer> queue1;
    private Deque<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<Integer>();
        queue2 = new LinkedList<Integer>();
    }

    public void push(int x) {
        while(!queue1.isEmpty()){
            queue2.offer(queue1.poll());
        }
        queue1.offer(x);
        while(!queue2.isEmpty()){
            queue1.offer(queue2.poll());
        }
    }

    public int pop() {
        return queue1.poll();
    }

    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}

```

## 实现方法二：
- x 放入 queue2 中，这样新加入的数据就在 queue2 队列头部
- 然后在把 queue1 中的数据导入 queue2 中，这样 queue2 的数据就是按照栈的顺序存放的
- 最后交换 queue1 和 queue2 指向的队列，这样 queue1就指向的是那个按照栈顺序存放的队列，而 queue2变成了空队列
- 这样数据只倒了一次

```java
class MyStack {
    private Deque<Integer> queue1;
    private Deque<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<Integer>();
        queue2 = new LinkedList<Integer>();
    }

    public void push(int x) {
        queue2.offer(x);
        while(!queue1.isEmpty()){
            queue2.offer(queue1.poll());
        }
        Deque<Integer> tempQ = queue1;
        queue1 = queue2;
        queue2 = tempQ;
    }

    public int pop() {
        return queue1.poll();
    }

    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}
```

# 20.有效的括号

题目的描述：

交错的括号不能出现，但是包含的括号可以出现，例如：[(]) 是 false，但是 [()] 是 true

解法：
- 当遇到左括号的时候入栈
- 当遇到右括号的时候，取出栈顶元素，判断能否与右括号匹配

注意：
- 简化代码的方法，遇到左括号，存对应的右括号
- 到时候遇到右括号，需要判断栈顶和右括号是否匹配的时候，直接判断是否相等就行，而不用三个分别判断

```java
class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); ++ i){
            char ch = s.charAt(i);
            if(ch == '(') stack.push(')');
            else if(ch == '{') stack.push('}');
            else if(ch == '[') stack.push(']');
            else if(stack.isEmpty() || stack.pop() != ch){
                return false;
            }
        }
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }
}
```

## 我的代码

```java
class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); ++ i){
            char ch = s.charAt(i);
            if(ch == ')' || ch == '}' || ch == ']'){
                if(stack.isEmpty()){
                    return false;
                }
                char top = stack.peek();
                if((top == '(' && ch == ')') 
                || (top == '{' && ch == '}')
                || (top == '[' && ch == ']')){
                    stack.pop();
                }else{
                    return false;
                }
            }else{
                stack.push(ch);
            }
        }
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }
}
```

# 1047.删除字符串中的所有相邻重复项

括号匹配的另一种描述形式

可以用StringBuilder简化代码，字符串本身提供删除指定索引位置的API:

```java
class Solution {
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (sb.length() != 0 && sb.charAt(sb.length() - 1) == c) {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
```

```java
class Solution {
    public String removeDuplicates(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); ++ i){
            char ch = s.charAt(i);
            if(stack.isEmpty() || stack.peek() != ch){
                stack.push(ch);
            }else{
                stack.pop();
            }
        }
        char[] ans = new char[stack.size()];
        int idx = stack.size() - 1;
        while(!stack.isEmpty()){
            ans[idx--] = stack.pop();
        }
        return new String(ans);
    }
}
```

# 150.逆波兰表达式求值

- 遇到运算符则从栈顶取两个数字出来，第一个数字是运算符右边的，第二个才是左边的，然后根据运算符的类型，把计算结果存回栈中，便于后续的计算
- 如果遇到数字，就存入栈中，等待后续被取出

```java
class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < tokens.length; ++ i){
            String token = tokens[i];
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
                int r = stack.pop();
                int l = stack.pop();
                if(token.equals("+")) stack.push(l+r);
                else if(token.equals("-")) stack.push(l-r);
                else if(token.equals("*")) stack.push(l*r);
                else stack.push(l/r);
            }else{
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
```

# 239.滑动窗口最大值

动态维护一个保持下标序列的递减队列

- 窗口要退出一个元素：
  - 这个元素可能早已出队
  - 也可能正是当前队列的最大值（队首），如果是队首就需要出队了
- 当新加入一个元素时，
  - 如果这个元素比队尾的值大，则说明这个元素的“价值”更高。
  - 因为这个元素不仅更偏右边，而且更大，那么之前那些存在队列中比这个元素小的值就都可以从队尾出队了

注意：
- poll 是从队首出队
- pollLast 从队尾出队
- offer 是从队尾入队，等同于 offerLast

```java
class Solution {
  public int[] maxSlidingWindow(int[] nums, int k) {
    Deque<Integer> queue = new LinkedList<>();
    int[] ans = new int[nums.length - k + 1];
    int idx = 0;
    for(int i = 0; i < k; ++ i){
      while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]){
        queue.pollLast();
      }
      queue.offerLast(i);
    }

    ans[idx++] = nums[queue.peekFirst()];
    for(int i = k; i < nums.length; ++ i){
      if(queue.peekFirst() == i - k) queue.poll();
      while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]){
        queue.pollLast();
      }
      queue.offerLast(i);
      ans[idx++] = nums[queue.peekFirst()];
    }
    return ans;
  }
}
```

# 347.前 K 个高频元素

求前 K 个最大 的元素：
- 用优先队列维护一个size为k的**最小堆**，这样最小的元素保持在堆顶
- 当有新元素来的时候
  - 如果新元素大于堆顶的最小元素，说明这个堆顶的元素要被淘汰掉，新元素加入堆中
  - 如果新元素小于堆顶元素，说明新元素连最小的元素都打不过，自然堆中的元素是当前最大的K个，堆保持不变

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> table = new HashMap<>();
        for(int i = 0; i < nums.length; ++ i){
            table.put(nums[i], table.getOrDefault(nums[i], 0) + 1);
        }
        int[] ans = new int[k];
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer a, Integer b)->{
            return table.get(a) - table.get(b); 
        });

        for(int key : table.keySet()){
            if(pq.size() < k){
                pq.add(key);
            }else if(table.get(pq.peek()) < table.get(key)){
                pq.poll();
                pq.add(key);
            }
        }
        int idx = 0;
        while(!pq.isEmpty()){
            ans[idx++] = pq.poll();
        }
        return ans;
    }
}
```
