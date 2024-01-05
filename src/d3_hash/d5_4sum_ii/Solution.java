package d3_hash.d5_4sum_ii;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> table = new HashMap<>();
        for(int num1 : nums1){
            for(int num2 : nums2){
                table.put(num1 + num2, table.getOrDefault(num1 + num2, 0) + 1);
            }
        }
        int ans = 0;
        for(int num3 : nums3){
            for(int num4 : nums4){
                ans += table.getOrDefault(-(num3+num4), 0);
            }
        }
        return ans;
    }
}
