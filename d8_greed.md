# 455.分发饼干

排序后贪心

双指针实现

注意边界

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int n = g.length, m = s.length;
        int i = 0, j = 0, ans = 0;
        while(i < n && j < m){
            while(j < m && g[i] > s[j]){
                ++j;
            }
            if(j < m && g[i] <= s[j]){
                ++i;
                ++j;
                ++ans;
            }else{
                break;
            }
        }
        return ans;
    }
}
```

# 376.摆动序列

- 贪心：求最大数量的波峰和波谷数量
  - 先平后升
  - 先平后降
  - 先降后升
  - 先升后降
- 动态规划

- 假设第一个是平的，所以preDiff为0
- 假设右边有一个峰值，所以res为1
- 避免平坡的影响，有峰值变化的时候才更新preDiff
```java
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int preDiff = 0;
        int curDiff = 0;
        int res = 1;
        for(int i = 0; i < nums.length - 1; ++ i){
            curDiff = nums[i + 1] - nums[i];
            if((preDiff >= 0 && curDiff < 0) || (preDiff <= 0 && curDiff > 0)){
                preDiff = curDiff;
                ++res;
            }
        }
        return res;
    }
}
```


# 53.最大子序和

连续子序列的和最大：
- sum加上当前值 num[i] 比 num[i] 大，那么加上是比不加上好的
- sum加上当前值 num[i] 比 num[i] 小，那么说明sum是个负数，不如重新从num[i]开始
- 此过程不断更新 res 取最大值

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int res = nums[0];
        for(int i = 0; i < nums.length; ++i){
            sum += nums[i];
            if(sum < nums[i]){
                sum = nums[i];
            }
            if(sum > res){
                res = sum;
            }
        }
        return res;
    }
}
```