package d4_string.d5_Strstr;

public class Solution_KMP {
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
