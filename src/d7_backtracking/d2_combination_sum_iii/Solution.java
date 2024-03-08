package d7_backtracking.d2_combination_sum_iii;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<Integer> res = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(1, k, n);
        return ans;
    }

    public void dfs(int beg, int k, int n){
        if(k < 0 || n < 0){
            return;
        }

        // 剩下最大的 k 个数相加都小于 n || 最小的 k 个数相加都大于 n : 也就没有必要继续遍历了
        if(k*(19-k)/2 < n || k*(2*beg+k-1)/2 > n){
            return;
        }

        if(k == 0 && n == 0){
            ans.add(new ArrayList(res));
            return;
        }

        for(int i = beg; i <= 9; ++ i){
            res.add(i);
            dfs(i + 1, k - 1, n - i);
            res.remove(res.size() - 1);
        }
    }
}
