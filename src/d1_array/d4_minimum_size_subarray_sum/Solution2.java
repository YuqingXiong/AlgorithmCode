package d1_array.d4_minimum_size_subarray_sum;

import java.util.Arrays;

import static java.lang.Math.min;

public class Solution2 {
    public int minSubArrayLen(int target, int[] nums) {
        // 前缀和&二分查找：nlog(n)
//        System.out.println(Arrays.toString(nums));
        int n = nums.length;
        int[] sums = new int[n+1];
        sums[0] = 0;
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i-1];
        }
        int res = n;
        for (int i = 1; i <= n; i++) {
            int minr = binarySearch(sums, i, n, target);
            if(minr != -1){
                res = Math.min(res, minr - i + 1);
            }
//            System.out.println(i + ", " + minr);
        }
        return sums[n] < target ? 0 : res;
    }

    // lowerBound: mid偏左，< 判断里左指针+1
    public int binarySearch(int[] sums, int i, int n, int target){
        int l = i, r = n;
        while(l < r){
            int mid = (l + r) >> 1;
            if(sums[mid] - sums[i - 1] < target){
                l = mid + 1;
            }else{
                r = mid;
            }
        }
        return sums[l] - sums[i - 1] < target ? -1 : l;
    }
}
