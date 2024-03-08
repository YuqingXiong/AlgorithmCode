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

# 216.组合总和III

```java
class Solution {
    
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
```

随想录代码：
```java
class Solution {
	List<List<Integer>> result = new ArrayList<>();
	LinkedList<Integer> path = new LinkedList<>();

	public List<List<Integer>> combinationSum3(int k, int n) {
		backTracking(n, k, 1, 0);
		return result;
	}

	private void backTracking(int targetSum, int k, int startIndex, int sum) {
		// 减枝
		if (sum > targetSum) {
			return;
		}

		if (path.size() == k) {
			if (sum == targetSum) result.add(new ArrayList<>(path));
			return;
		}

		// 减枝 9 - (k - path.size()) + 1
		for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
			path.add(i);
			sum += i;
			backTracking(targetSum, k, i + 1, sum);
			//回溯
			path.removeLast();
			//回溯
			sum -= i;
		}
	}
}
```