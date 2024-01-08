package d5_stack_queue.d3_valid_parentheses;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
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
