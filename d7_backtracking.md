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

# 17.电话号码的字母组合

第一次遍历判断第一个位置的 beg 所对应的字符 idx，遍历所有字符的可能

第二次遍历判断第二个位置的 beg + 1 所对应的字符 idx，遍历所有字符的可能

一直到所有位置都被遍历完，也就是 digits 所有位置都被遍历完，那么 beg 就等于  digits.length() 了，此时记录答案

注意，digits 的第 beg 位置，对应的数字是 idx，该 idx 对应的字符才是要遍历的字符
- 字符串中提取对应位置的字符：digits.chatAt(beg)
- 字符 char 转为 int 类型：
  - 首先 char 转为 String ：String.valueOf(x)
  - String 转为 int : Integer.parseInt(xx)

```java
class Solution {
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
```

# 39.组合总和

不限制元素使用次数，使其达到目标数，的所有不同组合

- 由于可以重复选取，所以 i 没有+1
- 由于结果要求组合不同，所以不能选 i 之前的数，这里用for循环让 i 只能往后选择剩余的数

```java
class Solution {
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
            ans.add(new ArrayList(res));
            return;
        }

        for(int i = beg; i < candidates.length; ++ i){
            res.add(candidates[i]);
            dfs(res, candidates, target - candidates[i], i);
            res.remove(res.size() - 1);
        }
    }
}
```

## 40.组合总和II

元素有重复，选出总和达到目标数的不同组合

由于元素有重复，所以一个数只能连续的被使用，那么就需要对原数组排序

我这里使用了一个freq记录每个数的可使用次数，当这个数的使用次数被用完，或者以后都不打算使用这个数的时候，才可以用下一个数

```java
class Solution {
    List<int[]> freq = new ArrayList<>();
    List<Integer> res = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        int num = 1, size = candidates.length;
        for(int i = 0; i < size; ++ i){
            if(i < size -1 && candidates[i] == candidates[i+1]){
                ++num;
            }else{
                freq.add(new int[]{candidates[i], num});
                num = 1;
            }
        }
        dfs(target, 0);
        return ans;
    }

    public void dfs(int target, int beg){
        if(target < 0){
            return;
        }
        if(target == 0){
            ans.add(new ArrayList<>(res));
        }

        for(int i = beg; i < freq.size(); ++i){
            res.add(freq.get(i)[0]);
            --freq.get(i)[1];
            dfs(target-freq.get(i)[0], freq.get(i)[1] == 0 ? i+1 : i);
            res.remove(res.size() - 1);
            ++freq.get(i)[1];
        }
    }
}
```
## 更优雅

另一个方法是让当前搜索层级里不出现相同的元素，要想同一个值使用多次，则必须在dfs的下一个层级

```java
class Solution {
    List<Integer> res = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, target, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, int beg){
        if(target < 0){
            return;
        }
        if(target == 0){
            ans.add(new ArrayList<>(res));
        }

        for(int i = beg; i < candidates.length; ++i){
            if(i > beg && candidates[i] == candidates[i-1]){
                continue;
            }
            res.add(candidates[i]);
            dfs(candidates, target-candidates[i], i+1);
            res.remove(res.size() - 1);
        }
    }
}
```

