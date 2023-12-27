[数组的最大美丽值 - 力扣 (LeetCode) 竞赛](https://leetcode.cn/contest/weekly-contest-354/problems/maximum-beauty-of-an-array-after-applying-operation/)

```cpp
class Solution {
public:
    int maximumBeauty(vector<int>& nums, int k) {
        vector<int> res(1e6, 0);
        int max_num = 0;
        for(int i = 0; i < nums.size(); ++ i){
            int l = nums[i] - k, r = nums[i] + k;
            //cout << l << " " << r << endl;
            res[k + l] += 1;
            res[k + r + 1] -= 1;
            max_num = max(max_num, k+r+1);
        }
        int beauty = res[0];
        for(int i = 1; i <= max_num; ++ i){
            res[i] = res[i - 1] + res[i];
            if(res[i] > res[beauty])
                beauty = i;
        }
        return res[beauty];
    }
};
```

