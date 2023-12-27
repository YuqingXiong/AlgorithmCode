package d1_array.d3_977_squares_of_a_sorted_array;

public class Solution {
    public int[] sortedSquares(int[] nums) {
        // 类似归并排序，从中间到两边发散
        int[] arr =  new int[nums.length];
        int mid = -1;
        for(int i = 0; i < nums.length; ++ i){
            if(nums[i] >= 0){
                mid = i;
                break;
            }
        }
        int l = -1, r = -1;
        int idx = 0;
        if(mid != -1 && nums[mid] == 0){
            arr[idx] = 0;
            ++idx;
            l = mid - 1;
            r = mid + 1;
        }else if(mid != -1 && mid != 0){
            l = mid - 1;
            r = mid;
        }else if(mid == 0){
            // 全是正数
            r = 0;
        }
        else{
            // mid = -1, 全是负数
            l = nums.length - 1;
            r = nums.length;
        }

        while(r < nums.length && l >= 0 && idx < nums.length){
            int rsq = nums[r]* nums[r];
            int lsq = nums[l]* nums[l];
            if(lsq > rsq){
                arr[idx] = rsq;
                ++r;
            }else{
                arr[idx] = lsq;
                --l;
            }
            ++idx;
        }
        while(l >= 0){
            arr[idx++] =  nums[l] * nums[l];
            --l;
        }
        while(r < nums.length){
            arr[idx++] = nums[r] * nums[r];
            ++r;
        }
        return arr;
    }
}
