package d4_string.d6_repeated_substring_pattern;

public class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] next = new int[n+1];

        next[0] = -1;
        int i = 0, j = -1;
        while(i < n){
            if(j == -1 || s.charAt(j) == s.charAt(i)){
                ++j;
                ++i;
                next[i] = j;
            }else{
                j = next[j];
            }
        }

        if(next[n] != 0 && n != 1 && n % (n - next[n]) == 0){
            return true;
        }
        return false;
    }
}
