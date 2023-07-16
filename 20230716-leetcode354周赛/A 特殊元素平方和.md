[特殊元素平方和 - 力扣 (LeetCode) 竞赛](https://leetcode.cn/contest/weekly-contest-354/problems/sum-of-squares-of-special-elements/)

```cpp
class Solution {
public:
    int sumOfSquares(vector<int>& nums) {
        int res = 0, n = nums.size();
        for(int i = 0;  i < n; ++ i){
            if(n % (i+1) == 0)
                res += (nums[i]*nums[i]);
        }
        return res;
    }
};
```

