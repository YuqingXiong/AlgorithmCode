package d1_array.d4_minimum_size_subarray_sum;

public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        // 滑动窗口
        int l = 0, n = nums.length;
        int sum = 0;
        int res = n;

        for(int r = 0 ; r < n; ++ r){
            sum += nums[r];
            if(sum >= target){
                while(sum - nums[l] >= target) {
                    sum -= nums[l++];
                }
                res = Math.min(res, r - l + 1);
            }

        }
        if(sum < target) return 0;
        return res;
    }
}
