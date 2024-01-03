package d3_hash.d1_valid_anagram;

public class Solution2 {
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
