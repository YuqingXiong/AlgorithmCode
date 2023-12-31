# 242.有效的字母异位词

## Hash 表
26个英文字母出现的次数记录在一个数组中，再根据s出现的次数在t中判断

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] table = new int[26];
        for(int i = 0; i < s.length(); ++ i){
            int ch = s.charAt(i) -'a';
            table[ch] += 1;
        }
        for(int i = 0; i < t.length(); ++ i){
            int ch = t.charAt(i) - 'a';
            table[ch] -= 1;
            if(table[ch] < 0) return false;
        }
        return true;
    }
}
```


### HashMap
getOrDefault() 方法获取指定 key 对应的 value，如果找不到 key 就返回默认值

```java
hashmap.getOrDefault(Object key, V defaultValue)
```

多态写法：

```java
 Map<Character, Integer> table = new HashMap<>();
```

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        Map<Character, Integer> table = new HashMap<>();
        for(int i = 0; i < s.length(); ++ i){
            char ch = s.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) + 1);
        }
        for(int i = 0; i < t.length(); ++ i){
            char ch = t.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) -1);
            // 没有ch则为-1，多了ch也会为负数
            if(table.get(ch) < 0){
                return false;
            }
        }
        return true;
    }
}
```


## 排序
对字符串按照字典序排序，如果排序后两个字符串相等则说明他们包含的字母和次数相同

String 转 char 数组：`toCharArray()`

char 数组排序：`Arrays.sort(char[])`
```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        Arrays.sort(charS);
        Arrays.sort(charT);
        return Arrays.equals(charS, charT);
    }
}
```



## 我的代码
```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); ++ i){
            char ch = s.charAt(i);
            if(map.containsKey(ch)){
                map.put(ch, map.get(ch) + 1);
            }else{
                map.put(ch, 1);
            }
        }
        for(int i = 0; i < t.length(); ++ i){
            char ch = t.charAt(i);
            if(map.containsKey(ch)){
                map.put(ch, map.get(ch) - 1);
            }else{
                return false;
            }
            if(map.get(ch) < 0){
                return false;
            }
        }
        return true;
    }
}
```

# 349.两个数组的交集

## 排序后双指针
两个排序后的数组找他们的公共元素可以用双指针的方式

分别有两个指针指向两个数组，如果这两个指针指向的元素相同，则都前进一步，且比较之前是否已保存过

如果不同，则指向较小的指针前进一步，因为数组是升序排序的，可能小数组的后面有较大的值能与当前另一个数组的较大值相同
```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> res = new ArrayList<>();
        int preSame = -1;
        for(int i=0,j=0; i<nums1.length && j< nums2.length;){
            if(nums1[i] == nums2[j]){
                if(nums1[i] != preSame) {
                    preSame = nums1[i];
                    res.add(preSame);
                }
                ++i;
                ++j;
            }else if(nums1[i] < nums2[j]){
                ++i;
            }else{
                ++j;
            }
        }
        int len = res.size();
        int[] ans = new int[len];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
```

## HashSet
```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }

        List<Integer> res = new ArrayList<>();
        for (Integer value : set1) {
            if(set2.contains(value)){
                res.add(value);
            }
        }
        int len = res.size();
        int[] ans = new int[len];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
```

## 我的代码
```java
class Solution {
     public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList();
        int[] table = new int[1000];
        for(int i = 0; i < nums1.length; ++ i){
            table[nums1[i]] = 1;
        }
        for(int i = 0; i < nums2.length; ++ i){
            if(table[nums2[i]] == 1){
                res.add(nums2[i]);
                table[nums2[i]] = 0;
            }
        }
        int len = res.size();
        int[] ans = new int[len];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
```

# 202.快乐数
观察一个三位的最大数 999，它的每一位平方求和后为 243，并没有越变越大，在经历过足够次数的循环后就会回到原先经历过的数

也就是说会经历一个循环数，需要判断这个循环是1引起的，还是因为不到1而有一个大循环

## HashSet 判断循环
```java
public class Solution {
    public boolean isHappy(int n) {
        Set<Integer> table = new HashSet<>();
        while(true){
            int ans = 0;
            while(n != 0){
                int num = n % 10;
                n = n / 10;
                ans = ans + (num * num);
            }
            if(ans == 1){
                return true;
            }
            if(table.contains(ans)){
                return false;
            }
            table.add(ans);
            n = ans;
        }
    }
}
```

## 快慢指针判断循环

```java
class Solution {
    public boolean isHappy(int n) {
        int fast = n, slow = n;
        while(true){
            fast = getNext(getNext(fast));
            slow = getNext(slow);
            if(fast == 1){
                return true;
            }else if(fast == slow){
                return false;
            }
        }
    }
    public static int getNext(int n){
        int ans = 0;
        while(n != 0){
            int num = n % 10;
            n /= 10;
            ans += num * num;
        }
        return ans;
    }
}
```

# 1.两数之和

从数组中选两个数使其之和等于目标值

先选出一个值，nums[i], 在判断 target-nums[i] 是否存在数组中

可以用hashMap存储除了当前了值的其他值和对应下标

注意这里要前判断是否包含，再加入table中，否则当前值可能会被选两次

```java
class Solution {
public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> table = new HashMap<>();
        for(int i = 0; i < nums.length; ++ i){
            int num = target - nums[i];
            if(table.containsKey(num)){
                return new int[]{i, table.get(num)};
            }
            table.put(nums[i], i);
        }
        return null;
    }
}
```

## 我的代码
```java
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> table = new HashMap<>();
        int[] ans = new int[2];
        for(int i = 0; i < nums.length; ++ i){
            if(table.containsKey(target - nums[i])){
                ans[0] = i;
                ans[1] = table.get(target-nums[i]);
                break;
            }
            table.put(nums[i], i);
        }
        return ans;
    }
}
```

# 454.四数相加 II
从A,B,C,D 四个数组中各取一个数，使得和为0

最暴力是循环四次，我们可以把数组 D 的值哈希，这样可以 O(1) 判断是否存在值，这样是循环三次

如果将它们分为两组，A,B一组，C,D一组，分别将它们的和哈希，这样只有循环两次

```java
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> table = new HashMap<>();
        for(int num1 : nums1){
            for(int num2 : nums2){
                table.put(num1 + num2, table.getOrDefault(num1 + num2, 0) + 1);
            }
        }
        int ans = 0;
        for(int num3 : nums3){
            for(int num4 : nums4){
                ans += table.getOrDefault(-(num3+num4), 0);
            }
        }
        return ans;
    }
}
```

# 383.赎金信

ransomNote 能否被 magazine 构成

统计 magazine 里每个字母的个数，遍历 ransomNote，被其中的字母消耗，如果小于0，说明无法构成

这里由于只有26个英文字母，可以用int数组代替Map，速度更快

```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> table = new HashMap<>();
        
        for(int i = 0; i < magazine.length(); ++ i){
            char ch = magazine.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) + 1);
        }

        for(int i = 0; i < ransomNote.length(); ++ i){
            char ch = ransomNote.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) - 1);
            if(table.get(ch) < 0){
                return false;
            }
        }
        return true;
    }
}
```

# 15.三数之和

## 排序后双指针

不重复的要求，使得我们必须保证这三个数的顺序是i<j<k的

同时，由于排序后的数组，当 i 固定时，我们可以 O(n) 得到这个数组中是否存在两个数 j,k之和满足目标值的

因为如果小于目标值，说明 j 要增大；
大于目标值，说明 k 要减小；

同时需要剔除相同的数

将多个数转为List集合：`List.of(Element e,...)`

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < nums.length; ++i){
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int j = i + 1, k = nums.length - 1;
            while(j < k){
                int sum = nums[i] + nums[j] + nums[k];
                if(sum < 0) ++j;
                else if(sum > 0) --k;
                else{
                    ans.add(List.of(nums[i], nums[j], nums[k]));
                    ++j;
                    --k;
                    while(nums[j] == nums[j-1] && j < k) ++j;
                    while(nums[k] == nums[k+1] && j < k) --k;
                }
            }
        }
        return ans;
    }
}
```

# 18.四数之和

与三数之和相同，遍历前n-2个数，后两个数用双指针

由于数组时升序的，所以有优化之处：
1. 当第i个数开始的最小四元组：i + (i+1) + (i+2) + (i+3)>target后，最小的都大，则后面的越来越大，就不可能出现等于target的四元组了
2. 当第i个数开始的最大四元组：i + (n-3) + (n-2) + (n-3)<target后，最大的都小，则这个i选得太小，后面的遍历不用看了，直接让i++,第一个数变大一点才有可能等于target

注意：
1. 要用 n-3,n-2提前限制下标，否则四个数相加会超出下标
2. 数据会超过 int，需要强转为 long
3. 注意判断 j 时，i 已经固定了，所以最大最小四元组的计算有一些改变
```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for(int i = 0; i < n - 3; ++ i){
            if(i > 0 && nums[i] == nums[i-1]) continue;
            if((long) nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target) break;
            if((long) nums[i] + nums[n-3] + nums[n-2] + nums[n-1] < target) continue;
            for(int j = i + 1; j < n - 2; ++ j){
                if(j > i + 1 && nums[j] == nums[j-1]) continue;
                if((long) nums[i] + nums[j] + nums[j+1] + nums[j+2] > target) break;
                if((long) nums[i] + nums[j] + nums[n-2] + nums[n-1] < target) continue;
                int k = j + 1, z = n - 1;
                while(k < z){
                    long sum = (long) nums[i] + nums[j] + nums[k] + nums[z];
                    if(sum < target) ++k;
                    else if(sum > target) --z;
                    else{
                        ans.add(List.of(nums[i], nums[j], nums[k], nums[z]));
                        ++k;
                        --z;
                        while(k < z && nums[k] == nums[k-1]) ++k;
                        while(k < z && nums[z] == nums[z+1]) --z;
                    }
                }
            }
        }
        return ans;
    }
}
```