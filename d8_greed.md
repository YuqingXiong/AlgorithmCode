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