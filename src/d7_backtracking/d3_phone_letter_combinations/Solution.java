package d7_backtracking.d3_phone_letter_combinations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    List<String> table = Arrays.asList("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz");
    List<String> ans = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if(digits.equals("")) return ans;
        dfs(digits, new StringBuilder(), 0);
        return ans;
    }

    public void dfs(String digits, StringBuilder res, int beg){
        if(beg == digits.length()){
            ans.add(res.toString());
            return;
        }
        int idx = Integer.parseInt(String.valueOf(digits.charAt(beg)));
        for(int j = 0; j < table.get(idx).length(); ++j){
            res.append(table.get(idx).charAt(j));
            dfs(digits, res, beg + 1);
            res.delete(beg, beg + 1);
        }
    }
}
