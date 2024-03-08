package d7_backtracking.d1_combinations;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> res = new ArrayList<Integer>();
        dfs(res, 1, n, k);
        return ans;
    }

    public void dfs(List<Integer> res, int beg, int n, int k){
        if(k == 0){
            ans.add(new ArrayList<Integer>(res));
            return;
        }
        if(res.size() + n - beg + 1 < k) return;

        for(int i = beg; i <= n; ++ i){
            res.add(i);
            dfs(res, i + 1, n, k - 1);
            res.remove(res.size() - 1);
        }
    }
}
