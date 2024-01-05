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

