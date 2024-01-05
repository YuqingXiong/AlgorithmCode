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