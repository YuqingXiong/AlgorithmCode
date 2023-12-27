package d1_array.d2_27_remove_element;

public class Solution2 {
    public int removeElement(int[] nums, int val) {
        // while里是<，则这里 right 必须为 nums.length：
        // left和right才能指向同一个位置进行最后一个元素的判断
        int left = 0, right = nums.length;
        while(left < right){
            if(nums[left] == val){
                nums[left] = nums[--right];
            }else{
                ++left;
            }
        }
        return right;
    }


    public int removeElement2(int[] nums, int val) {
        // while里是<=，则这里 right 必须为 nums.length-1：
        // left和right才能指向同一个位置进行最后一个元素的判断
        int left = 0, right = nums.length-1;
        while(left <= right){
            if(nums[left] == val){
                nums[left] = nums[right--];
            }else{
                ++left;
            }
        }
        return right+1;
    }

}
