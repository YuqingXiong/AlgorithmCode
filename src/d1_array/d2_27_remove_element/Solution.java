package d1_array.d2_27_remove_element;

class Solution {
    public int removeElement(int[] nums, int val) {
        int idx = 0;
        for (int num : nums) {
            if(num != val){
                nums[idx++] = num;
            }
        }
        return idx;
    }
}
