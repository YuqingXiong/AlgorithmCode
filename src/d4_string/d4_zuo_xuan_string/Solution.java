package d4_string.d4_zuo_xuan_string;

public class Solution {
    public String dynamicPassword(String password, int target) {
        StringBuilder res = new StringBuilder();
        for(int i = target; i < password.length() + target; ++i){
            res.append(password.charAt(i % password.length()));
        }
        return res.toString();
    }
}
