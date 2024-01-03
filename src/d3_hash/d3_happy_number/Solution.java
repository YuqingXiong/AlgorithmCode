package d3_hash.d3_happy_number;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public boolean isHappy(int n) {
        Set<Integer> table = new HashSet<>();
        while(true){
            int ans = 0;
            while(n != 0){
                int num = n % 10;
                n = n / 10;
                ans = ans + (num * num);
            }
            if(ans == 1){
                return true;
            }
            if(table.contains(ans)){
                return false;
            }
            table.add(ans);
            n = ans;
        }
    }
}
