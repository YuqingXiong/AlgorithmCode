package d5_stack_queue.d7_top_k_frequent_elements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
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
