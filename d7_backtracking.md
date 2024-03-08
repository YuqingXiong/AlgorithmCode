# 77.组合

第一个位置选择有 n 种，接下来每个位置只能在前面选择数字的后面选，所以有了 beg 参数，才能保持不重复

剪枝：res.size + (n - beg + 1) < k , 已有答案的长度 + 剩余所有未选择的个数 都小于最终答案长度了 就没有必要尝试下去

```java
class Solution {
    
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> res = new ArrayList<Integer>();
        dfs(res, 1, n, k);
        return ans;
    }

    public void dfs(List<Integer> res, int beg, int n, int k){
        if(k == 0){
            // 这里一定要 new 一个新的 ArrayList 啊，否则最后加进去的 res 都是 null
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
```

