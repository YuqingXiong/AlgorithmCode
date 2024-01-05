package d4_string.d2_reverse_string_ii;

public class Solution {
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
