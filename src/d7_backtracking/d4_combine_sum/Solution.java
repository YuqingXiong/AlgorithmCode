package d7_backtracking.d4_combine_sum;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<Integer> res = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(res, candidates, target, 0);
        return ans;
    }

    public void dfs(List<Integer> res, int[] candidates, int target, int beg){

        if(target < 0){
            return;
        }
        if(target == 0){
            ans.add(new ArrayList<>(res));
            return;
        }

        for(int i = beg; i < candidates.length; ++ i){
            res.add(candidates[i]);
            dfs(res, candidates, target - candidates[i], i);
            res.remove(res.size() - 1);
        }
    }
}
