package d5_stack_queue.d5_evaluate_reverse_polish_notation;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
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
