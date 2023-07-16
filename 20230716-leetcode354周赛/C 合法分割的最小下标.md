[合法分割的最小下标 - 力扣 (LeetCode) 竞赛](https://leetcode.cn/contest/weekly-contest-354/problems/minimum-index-of-a-valid-split/)

```cpp
class Solution {
public:
    int minimumIndex(vector<int>& nums) {
        vector<int> l_freq(nums.size());
        vector<int> r_freq(nums.size());

        map<int, int> record;
        int x = nums[0], max_freq = 1;
        for(int i = 0; i < nums.size(); ++ i){
            if(record.find(nums[i]) == record.end()){
                record[nums[i]] = 1;
            }else{
                record[nums[i]] += 1;
                if(record[nums[i]] > max_freq){
                    max_freq = record[nums[i]];
                    x = nums[i];
                }
            }

            if(max_freq * 2 > (i + 1))
                l_freq[i] = x;
            else
                l_freq[i] = -1;
        }

        map<int, int> record_y;
        x = nums[nums.size() - 1], max_freq = 1;
        for(int i = nums.size() - 1; i > 0; -- i){
            if(record_y.find(nums[i]) == record_y.end()){
                record_y[nums[i]] = 1;
            }else{
                record_y[nums[i]] += 1;
                if(record_y[nums[i]] > max_freq){
                    max_freq = record_y[nums[i]];
                    x = nums[i];
                }
            }

            if(max_freq * 2 > (nums.size() - i))
                r_freq[i] = x;
            else
                r_freq[i] = -1;
        }

        for(int i = 0; i < nums.size()-1; ++ i){
            if(l_freq[i] != -1 && r_freq[i+1] != -1 && l_freq[i] == r_freq[i+1])
                return i;
        }
        return -1;
    }
};
```

