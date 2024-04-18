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

# 134.加油站

贪心：

用 加油量 - 耗油量 得到当前站点是 正 还是 负

起始点一定是 正（=0） 的，因为需要去往下一个站点

假设一个正的位置为起始点，如果后面累计损耗后为负数，说明这个起始点无法完成环绕一圈。此时应该选择后面的 正 数为新的起始点

当 i 回到记录的起始点时，说明完成了环绕一周

```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = cost.length;
        for(int i = 0; i < n; ++i){
            cost[i] = gas[i] - cost[i];
        }
        int ans = -1, sum = 0;
        for(int i = 0; i < n*2; ++i){
            if(i%n == ans){
                break;
            }
            if(ans == -1 && cost[i%n] >= 0){
                ans = i;
            }
            sum += cost[i%n];
            if(sum < 0){
                ans = -1;
                sum = 0;
            }
        }
        return ans>n?-1:ans;
    }
}
```

更优雅的：

差值累加大于 0 则一定存在起始点满足要求。只需要在 sum 小于 0 的时候更新答案就行：

```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = cost.length;
        int totalSum = 0;
        for(int i = 0; i < n; ++i){
            cost[i] = gas[i] - cost[i];
            totalSum += cost[i];
        }
        if(totalSum < 0) return -1;
        int ans = 0, sum = 0;
        for(int i = 0; i < n; ++i){
            sum += cost[i];
            if(sum < 0){
                ans = i+1;
                sum = 0;
            }
        }
        return ans;
    }
}
```

# 135.分发糖果

先从左往右遍历一遍，确定比左边孩子大的分到更多的糖果

再从右往左遍历一遍，确定比右边孩子大的分到更多的糖果（同时要满足比左边孩子大的需求，所以需要取左右分得糖果的最大值）

```java
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] ans = new int[n];
        int res = 0;
        ans[0] = 1;
        for(int i = 1; i < n; ++ i){
            if(ratings[i] > ratings[i-1]){
                ans[i] = ans[i-1] + 1;
            }else{
                ans[i] = 1;
            }
        }
        for(int i = n-2; i >= 0; --i){
            if(ratings[i] > ratings[i+1]){
                ans[i] = Math.max(ans[i+1] + 1, ans[i]);
            }
        }
        for(int i = 0; i < n; ++ i){
            res += ans[i];
        }
        return res;
    }
}
```
# 860.柠檬水找零
分类讨论

找零钱，记录现在的5元面值和10元面值的数量，贪心的优先找10元面值的

```java
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int num5 = 0, num10 = 0;
        for(int i = 0; i < bills.length; ++i){
            if(bills[i] == 5) {
                ++num5;
            }else if(bills[i] == 10){
                --num5;
                ++num10;
            }else{
                if(num10 > 0){
                    --num10;
                    --num5;
                }else{
                    num5 -= 3;
                }
            }
            if(num5 < 0 || num10 < 0){
                break;
            }
        }
        return (num5 < 0 || num10 < 0) ? false : true;
    }
}
```
# 406.根据身高重建队列

[h,k]记录身高h和前面比h大的个数k

1. 按照身高降序排序后，比[h,k]高的个的人一定在它前面，但是前面可能有大于k个比他高的人；（所以等前面的人确定好了位置后，我们再根据下标k插入）
2. 按照排完序的顺序，把 k 当作下标再次插入时，保证了前面刚好有k个人比他高

```java
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if(b[0] == a[0]) return a[1]-b[1];
            return b[0]-a[0];
        });

        List<int[]> ans = new LinkedList<>();
        for(int i = 0; i < people.length; ++i){
            ans.add(people[i][1], people[i]);
        }
        return ans.toArray(new int[people.length][]);
    }
}
```

# 425.用最少数量的箭引爆气球

```java
class Solution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b)->{
            if(a[1] > b[1]) return 1;
            if(a[1] < b[1]) return -1;
            return 0;
        });
        int rlim = points[0][1], ans = 1;
        for(int i = 1; i < points.length; ++ i){
            if(points[i][0] > rlim){
                rlim = points[i][1];
                ++ans;
            }
        }
        return ans;
    }
}
```

# 1005.K 次取反后最大化的数组和

1. 首先反转负数
2. k有剩余，说明现在都是正数
   3. k为偶数，则保持现状，所有数为正已经最大了
   4. k为奇数，则选择一个最小的正数，反转为负数

```java
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        int minAbs = 100, ans = 0;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; ++ i){
            if(nums[i] < 0 && k > 0){
                --k;
                nums[i] = -nums[i];
            }
            if(minAbs > Math.abs(nums[i])){
                minAbs = Math.abs(nums[i]);
            }
            ans += nums[i];
        }
        if(k > 0){
            if(k%2 == 1){
                ans -= 2*minAbs;
            }
        }
        return ans;
    }
}
```