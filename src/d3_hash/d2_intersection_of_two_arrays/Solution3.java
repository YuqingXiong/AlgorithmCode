package d3_hash.d2_intersection_of_two_arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution3 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> res = new ArrayList<>();
        int preSame = -1;
        for(int i=0,j=0; i<nums1.length && j< nums2.length;){
            if(nums1[i] == nums2[j]){
                if(nums1[i] != preSame) {
                    preSame = nums1[i];
                    res.add(preSame);
                }
                ++i;
                ++j;
            }else if(nums1[i] < nums2[j]){
                ++i;
            }else{
                ++j;
            }
        }
        int len = res.size();
        int[] ans = new int[len];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
