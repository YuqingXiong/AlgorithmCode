package d3_hash.d3_happy_number;

public class Solution2 {
    public boolean isHappy(int n) {
        int fast = n, slow = n;
        while(true){
            fast = getNext(getNext(fast));
            slow = getNext(slow);
            if(fast == 1){
                return true;
            }else if(fast == slow){
                return false;
            }
        }
    }
    public static int getNext(int n){
        int ans = 0;
        while(n != 0){
            int num = n % 10;
            n /= 10;
            ans += num * num;
        }
        return ans;
    }
}
