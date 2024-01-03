package d3_hash.d4_two_sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> table = new HashMap<>();
        int[] ans = new int[2];
        for(int i = 0; i < nums.length; ++ i){
            if(table.containsKey(target - nums[i])){
                ans[0] = i;
                ans[1] = table.get(target-nums[i]);
                break;
            }
            table.put(nums[i], i);
        }
        return ans;
    }
}
