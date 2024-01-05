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