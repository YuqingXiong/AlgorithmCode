package d5_stack_queue.d6_sliding_window_maximum;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
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
