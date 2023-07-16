# B
转化为区间重复个数统计
由于负数存在，下标可能访问出界，区间统一右移k

差分（前缀和）：用于区间加。

数组a[l,r]区间统一加c，可转化为：

for i~n: b[i] = a[i] - a[i - 1]

b[l] += c, b[r + 1] -= c

# C
数学题

1. map计算前缀和后缀众数

2. 数学证明：
i = 0,1,2,...

$$freq_l(x) * 2 > i + 1$$

$$freq_r(x) * 2 > n - (i + 1)$$

freq_l 和 freq_r 数组合并为原数组，则：

$$(freq_l(x) + freq_r(x)) * 2 > n$$

所以 x 如果是左右两个数组的支配元素则一定是原数组的支配元素。

从左到右枚举分割下标 $i$, 统计[0, i] 和[i + 1, n] 两个区间内的 x 数量，判断是否大于 len/2

# D
https://leetcode.cn/problems/length-of-the-longest-valid-substring/solution/ha-xi-biao-shuang-zhi-zhen-pythonjavacgo-bcez/

unordered_set + 双指针
```cpp
class Solution {
public:
    int longestValidSubstring(string word, vector<string> &forbidden) {
        unordered_set<string> fb{forbidden.begin(), forbidden.end()};
        int ans = 0, left = 0, n = word.length();
        for (int right = 0; right < n; right++) {
            for (int i = right; i >= left && i > right - 10; i--) {
                if (fb.count(word.substr(i, right - i + 1))) {
                    left = i + 1; // 当子串右端点 >= right 时，合法子串一定不能包含 word[i]
                    break;
                }
            }
            ans = max(ans, right - left + 1);
        }
        return ans;
    }
};
```

其他技巧：字典树，AC自动机（都是为了快速字符串匹配）

