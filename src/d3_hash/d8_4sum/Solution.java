package d3_hash.d8_4sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for(int i = 0; i < n; ++ i){
            if(i > 0 && nums[i] == nums[i-1]) continue;
            for(int j = i + 1; j < n; ++ j){
                if(j > i + 1 && nums[j] == nums[j-1]) continue;
                int k = j + 1, z = n - 1;
                while(k < z){
                    long sum = (long) nums[i] + nums[j] + nums[k] + nums[z];
                    if(sum < target) ++k;
                    else if(sum > target) --z;
                    else{
                        ans.add(List.of(nums[i], nums[j], nums[k], nums[z]));
                        ++k;
                        --z;
                        while(k < z && nums[k] == nums[k-1]) ++k;
                        while(k < z && nums[z] == nums[z+1]) --z;
                    }
                }
            }
        }
        return ans;
    }
}
