package d1_array.d3_977_squares_of_a_sorted_array;

public class Solution2 {
    public int[] sortedSquares(int[] nums) {
        // 从两边到中间收拢
        int[] arr = new int[nums.length];
        int idx = nums.length - 1;
        int l = 0, r = nums.length - 1;
        while(l <= r){
            int lsq = nums[l] * nums[l];
            int rsq = nums[r] * nums[r];
            if(lsq > rsq){
                arr[idx] = lsq;
                ++l;
            }else{
                arr[idx] = rsq;
                --r;
            }
            --idx;
        }
        return arr;
    }
}
