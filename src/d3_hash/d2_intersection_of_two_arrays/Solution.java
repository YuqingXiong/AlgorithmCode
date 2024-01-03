package d3_hash.d2_intersection_of_two_arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList();
        int[] table = new int[1000];
        for(int i = 0; i < nums1.length; ++ i){
            table[nums1[i]] = 1;
        }
        for(int i = 0; i < nums2.length; ++ i){
            if(table[nums2[i]] == 1){
                res.add(nums2[i]);
                table[nums2[i]] = 0;
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
