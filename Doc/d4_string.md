# 344.反转字符串

API: `StringBuffer` 内部是 append 实现字符串的改变，不会每次改变字符串都创建一个新对象
```java
StringBuffer(String.valueOf(s)).reverse().toString()
```

不调用API, 使用双指针，分别从两端向中间聚拢

```java
class Solution {
    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while(l < r){
            char chL = s[l];
            char chR = s[r];
            s[l] = chR;
            s[r] = chL;
            ++l;
            --r;
        }
    }
}
```

# 541.反转字符串 II

```java
class Solution {
    public String reverseStr(String s, int k) {
        char[] str = s.toCharArray();
        int n = str.length;
        int l = 0;
        while(l < n){
            int r = (l-1) + k;
            if(r >= n) r = n - 1;
            int nextl = (l-1)+2*k+1;
            while(l < r){
                char ch = str[l];
                str[l] = str[r];
                str[r] = ch;
                ++l;
                --r;
            }
            l = nextl;
        }
        return new String(str);
    }
}
```

# 122.路径加密/替换空格

API:

```java
path.replaceAll("\\.", " ");
```

```java
class Solution {
    public String pathEncryption(String path) {
        char[] p = path.toCharArray();
        for(int i = 0; i < p.length; ++ i){
            if(p[i] == '.'){
                p[i] = ' ';
            }
        }
        return new String(p);
    }
}
```
# 182.动态口令/左旋字符串

## substring(begin, len)
```java
class Solution {
    public String dynamicPassword(String password, int target) {
        int n = password.length();
        return password.substring(target, n) + password.substring(0, target);
    }
}
```
## StringBuilder
```java
class Solution {
    public String dynamicPassword(String password, int target) {
        StringBuilder res = new StringBuilder();
        for(int i = target; i < password.length() + target; ++i){
            res.append(password.charAt(i % password.length()));
        }
        return res.toString();
    }
}
```

## 源字符串上操作

# 28.实现strStr
找第一个匹配子串的下标

## KMP

求Next数组：

1. Next 数组长度为 n + 1，用于获取 next[n]，也就是整个字符串相同前后缀的长度
2. 刚开始 Next[0] = -1, j = -1，便于观察当前是否已经退回到下标 0 了
3. 可以求所有匹配位置，在 while 循环里判断 j 是否到n，然后让 j = next[j]，继续匹配

```java
class Solution {
    public int strStr(String haystack, String needle) {
        int n = needle.length(), m = haystack.length();
        int[] next = new int[n+1];

        next[0] = -1;
        int i = 0, j = -1;
        while(i < n){
            if(j == -1 || needle.charAt(j) == needle.charAt(i)){
                ++j;
                ++i;
                next[i] = j;
            }else{
                j = next[j];
            }
        }

        i = 0;
        j = 0;
        while(i < m && j < n){
            if(j == -1 || haystack.charAt(i) == needle.charAt(j)){
                ++i;
                ++j;
            }else{
                j = next[j];
            }
        }
        if(j == n){
            return i - n;
        }
        return -1;
    }
}
```

## 暴力
```java
public class Solution {
    public int strStr(String haystack, String needle) {
        for(int i = 0; i < haystack.length(); ++i){
            String compare = haystack.substring(i, Math.min(i+needle.length(), haystack.length()));
            if(compare.equals(needle)){
                return i;
            }
        }
        return -1;
    }
}
```
