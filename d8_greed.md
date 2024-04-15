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
# 122.买卖股票的最佳时机 II

一旦股票上涨就在前一天买入，后一天卖出

后面有更高的价格，可以在之前卖出的时候买入

```java
class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        for(int i = 1; i < prices.length; ++i){
            int diff = prices[i] - prices[i-1];
            res +=  diff > 0 ? diff : 0; 
        }
        return res;
    }
}
```

# 55.跳跃游戏

贪心：

当前位置可以到达的最远地方是 i + num[i], 在[i, i+num[i]]区间内又可以跳到更远的地方，所以不断更新最远的地方，判断是否达到最右边

```java
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length, maxR = 0;
        for(int i = 0; i < n; ++ i){
            if(i > maxR) return false;
            maxR = Math.max(maxR, i + nums[i]);
        }
        return true;
    }
}
```


我的思想：

查看数字 0 是否可被跳过，如果所有最右的0可以被跳过则可以到达最右端

```java
class Solution {
  public boolean canJump(int[] nums) {
    int n = nums.length;
    int zero_idx = -1;
    for(int i = n - 2; i >= 0; --i){
      if(zero_idx == -1 && nums[i] == 0){
        zero_idx = i;
      }else if(zero_idx != -1){
        if(nums[i] > zero_idx - i){
          zero_idx = -1;
        }
      }
    }
    if(zero_idx == -1) return true;
    return false;
  }
}
```

# 45.跳跃游戏 II

```
[2,3,1,1,4]
```

每次step可以在区间[i, end]中选一个地点进行跳跃，每跳跃一次从 i 到达新的地方 num[i]+i ，计算这次 step 能到达的最远地方 maxR

当这次step跳完，也就是 i 大于了 end，此时开启下一次跳跃，即 ++step，这次跳跃的边界 end 是上次跳跃计算得到的最远地方 maxR

- 在区间中跳跃得到最大值maxR，作为下次跳跃的区间边界

化简：
```java
class Solution {
  public int jump(int[] nums) {
    int step = 0;
    int n = nums.length;
    if(n == 1) return 0;
    int maxR = 0, end = 0;
    for(int i = 0; i < n; ++ i){
      if(i > end){
        end = maxR;
        ++step;
      }
      maxR = Math.max(maxR, i + nums[i]);
    }
    return step;
  }
}
```

```java
class Solution {
    public int jump(int[] nums) {
        int step = 0;
        int n = nums.length;
        if(n == 1) return 0;
        int maxR = 0, end = 0;
        for(int i = 0; i < n; ++ i){
            for(int j = i; j <= end; ++ j){
                maxR = Math.max(maxR, j + nums[j]);
            }
            i = end;
            end = maxR;
            ++step;
            if(maxR >= n - 1) break;
        }
        return step;
    }
}
```