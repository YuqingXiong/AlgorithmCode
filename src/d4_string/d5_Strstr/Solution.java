package d4_string.d5_Strstr;

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
