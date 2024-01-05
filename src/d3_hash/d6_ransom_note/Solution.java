package d3_hash.d6_ransom_note;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> table = new HashMap<>();

        for(int i = 0; i < magazine.length(); ++ i){
            char ch = magazine.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) + 1);
        }

        for(int i = 0; i < ransomNote.length(); ++ i){
            char ch = ransomNote.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) - 1);
            if(table.get(ch) < 0){
                return false;
            }
        }
        return true;
    }
}
