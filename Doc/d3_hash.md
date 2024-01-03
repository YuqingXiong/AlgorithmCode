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