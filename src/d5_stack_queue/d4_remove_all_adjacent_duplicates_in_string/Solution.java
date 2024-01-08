package d5_stack_queue.d4_remove_all_adjacent_duplicates_in_string;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
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
