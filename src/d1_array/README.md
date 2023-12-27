# 27. 移除元素
https://leetcode.cn/problems/remove-element/description/
## 方法一：
将后面所有不等于val的元素向前赋值移动

弊端：大量不等于val的元素会被重复赋值
```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int idx = 0;
        for (int num : nums) {
            if(num != val){
                nums[idx++] = num;
            }
        }
        return idx;
    }
}
```
## 方法二：双指针
左右两个指针分别向中间移动

如果左指针指向的元素是val，则将右指针指向的元素覆盖左边的元素，同时当前右指针指向的元素就不可用了，所以右指针向左移动一位

如果左指针指向的元素不是val，则保留这个元素，左指针向右移动

注意的是：最后边界条件的判断，最后左右指针必须重合指向同一个元素，才能保证长度计算正确

```java
public class Solution2 {
    public int removeElement(int[] nums, int val) {
        // while里是<，则这里 right 必须为 nums.length：
        // left和right才能指向同一个位置进行最后一个元素的判断
        int left = 0, right = nums.length;
        while(left < right){
            if(nums[left] == val){
                nums[left] = nums[--right];
            }else{
                ++left;
            }
        }
        return right;
    }


    public int removeElement2(int[] nums, int val) {
        // while里是<=，则这里 right 必须为 nums.length-1：
        // left和right才能指向同一个位置进行最后一个元素的判断
        int left = 0, right = nums.length-1;
        while(left <= right){
            if(nums[left] == val){
                nums[left] = nums[right--];
            }else{
                ++left;
            }
        }
        return right+1;
    }
}
```
## 我的解法：
不同在于，我的解法进一步减少了数据的复制次数，对于右指针指向的等于val的元素并不会复制给左指针，而是跳过，指导找到一个不等于val的元素
```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int begin = 0, end = nums.length-1;
        while(begin < end){
            while(begin < end && nums[begin] != val) ++begin;
            while(end >= 0 && nums[end] == val ) --end;
            if(begin>= end) break;
            int temp = nums[begin];
            nums[begin] = nums[end];
            nums[end] = temp;
        }
        if(end == 0 && nums[end] == val) {
            return 0;
        }
        return end+1;
    }
}
```

# 977. 有序数组的平方
https://leetcode.cn/problems/squares-of-a-sorted-array/description/

## 方法一：
一个包含正负数的非递减数组，等同于一个从正负数分割线开始的两个有序正负数组

对于两个有序数组的合并，可以利用类似归并排序的思想，为两个数组的最小位置各自分配一个指针

分别向左和右移动，选择当前较小的平方值存入新数组：

```java
public class Solution {
    public int[] sortedSquares(int[] nums) {
        // 类似归并排序，从中间到两边发散
        int[] arr =  new int[nums.length];
        int mid = -1;
        for(int i = 0; i < nums.length; ++ i){
            if(nums[i] >= 0){
                mid = i;
                break;
            }
        }
        int l = -1, r = -1;
        int idx = 0;
        if(mid != -1 && nums[mid] == 0){
            arr[idx] = 0;
            ++idx;
            l = mid - 1;
            r = mid + 1;
        }else if(mid != -1 && mid != 0){
            l = mid - 1;
            r = mid;
        }else if(mid == 0){
            // 全是正数
            r = 0;
        }
        else{
            // mid = -1, 全是负数
            l = nums.length - 1;
            r = nums.length;
        }

        while(r < nums.length && l >= 0 && idx < nums.length){
            int rsq = nums[r]* nums[r];
            int lsq = nums[l]* nums[l];
            if(lsq > rsq){
                arr[idx] = rsq;
                ++r;
            }else{
                arr[idx] = lsq;
                --l;
            }
            ++idx;
        }
        while(l >= 0){
            arr[idx++] =  nums[l] * nums[l];
            --l;
        }
        while(r < nums.length){
            arr[idx++] = nums[r] * nums[r];
            ++r;
        }
        return arr;
    }
}
```

## 方法二：

根据数组特性，这是一个凹下去的数组，类似一元二次函数。

只需要在左右边界最高点的地方分配两根指针，逐渐向中心移动。

每次移动前选择其中较大的一方，以倒序的方式存入新数组中：

```java
public class Solution2 {
    public int[] sortedSquares(int[] nums) {
        // 从两边到中间收拢
        int[] arr = new int[nums.length];
        int idx = nums.length - 1;
        int l = 0, r = nums.length - 1;
        while(l <= r){
            int lsq = nums[l] * nums[l];
            int rsq = nums[r] * nums[r];
            if(lsq > rsq){
                arr[idx] = lsq;
                ++l;
            }else{
                arr[idx] = rsq;
                --r;
            }
            --idx;
        }
        return arr;
    }
}
```

# 209. 长度最小的子数组
https://leetcode.cn/problems/minimum-size-subarray-sum/description/

## 方法一：
设置左右两个指针，起点都为0

右指针不断的前进，计算两个指针之间的元素和

当元素和的大小大于等于目标值时，判断能否在保证元素和大于目标值的同时，移动左指针前进

计算当前这种左右区间的长度，更新最小区间长度的答案

注意边界：如果所有元素的和都小于目标值，则区间不存在，返回0

```java
public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        // 滑动窗口
        int l = 0, n = nums.length;
        int sum = 0;
        int res = n;

        for(int r = 0 ; r < n; ++ r){
            sum += nums[r];
            if(sum >= target){
                while(sum - nums[l] >= target) {
                    sum -= nums[l++];
                }
                res = Math.min(res, r - l + 1);
            }
        }
        if(sum < target) return 0;
        return res;
    }
}
```

## 方法二：前缀和+二分查找

我们的目标是找到一个最小长度区间内元素和大于等于target

一个简单的想法是枚举区间的左右端点，这样复杂度是 $O(n^2)$

但是如果我们只枚举左端点，右端点使用二分查找的方式，复杂度为 $O(nlog(n))$

由于前缀和是不断递增的，满足二分查找的条件，所以我们只需要找到一个索引最小的右端点，使得左右端点之间的元素和大于等于target

```java
public class Solution2 {
    public int minSubArrayLen(int target, int[] nums) {
        // 前缀和&二分查找：nlog(n)
//        System.out.println(Arrays.toString(nums));
        int n = nums.length;
        int[] sums = new int[n+1];
        sums[0] = 0;
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i-1];
        }
        int res = n;
        for (int i = 1; i <= n; i++) {
            int minr = binarySearch(sums, i, n, target);
            if(minr != -1){
                res = Math.min(res, minr - i + 1);
            }
//            System.out.println(i + ", " + minr);
        }
        return sums[n] < target ? 0 : res;
    }

    // lowerBound: mid偏左，< 判断里左指针+1
    public int binarySearch(int[] sums, int i, int n, int target){
        int l = i, r = n;
        while(l < r){
            int mid = (l + r) >> 1;
            if(sums[mid] - sums[i - 1] < target){
                l = mid + 1;
            }else{
                r = mid;
            }
        }
        return sums[l] - sums[i - 1] < target ? -1 : l;
    }
}
```
# 59. 螺旋矩阵 II

## 方法一：
每次只走一步

判断下一步是否为临界值，如果是，按照预定的的四个方向的顺序进行转变

一个巧妙的点：通过判断下一步是否有值来确定是否是边界，就可以不用记录已走过的边界框

```java
public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int data = 1;
        int x = 0, y = 0;
//        int l = 0, r = n - 1, top = 0, bottom = n - 1;
        int[][] step = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dire = 0;
        while(data <= n*n){
            System.out.println(x + ", " + y);
            ans[x][y] = data;
            ++data;
            int stepx = x + step[dire][0];
            int stepy = y + step[dire][1];
            if(stepx < 0 || stepx >= n
                    || stepy < 0 || stepy >= n
                    || ans[stepx][stepy] != 0){
                dire = (dire + 1) % 4;
            }
            x += step[dire][0];
            y += step[dire][1];
        }
        return ans;
    }
}
```

## 方法二：
每次都将一个方向的所有步给走完，更新边界值，接着才走向下一个方向

注意：这里必须是 data < maxData, 如果写成 data <= maxData 将选入死循环

因为 data 等于 maxData 后， left, right, top, bottom之间的关系将不再进入四个方向的循环，data的值也不会增加
```java
public class Solution2 {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int data = 0;
        int maxData = n*n;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        while(data < maxData){
//            System.out.println(top + ", " + right + ", " + bottom + ", " + left);
            for(int y = left; y <= right; ++ y){
                ans[top][y] = ++data;
            }
            ++top;
            for(int x = top; x <= bottom; ++ x){
                ans[x][right] = ++data;
            }
            --right;
            for(int y = right; y >= left; --y){
                ans[bottom][y] = ++data;
            }
            --bottom;
            for(int x = bottom; x >= top; --x){
                ans[x][left] = ++data;
            }
            ++left;
        }
        return ans;
    }
}
```