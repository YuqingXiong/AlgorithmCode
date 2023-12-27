package d1_array.d5_59_spiral_matrix_ii;

public class Solution2 {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int data = 0;
        int maxData = n*n;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        while(data < maxData){
//            System.out.println(top + ", " + right + ", " + bottom + ", " + left);
            for(int y = left; y <= right; ++ y){
                ans[top][y] = ++data;
            }
            ++top;
            for(int x = top; x <= bottom; ++ x){
                ans[x][right] = ++data;
            }
            --right;
            for(int y = right; y >= left; --y){
                ans[bottom][y] = ++data;
            }
            --bottom;
            for(int x = bottom; x >= top; --x){
                ans[x][left] = ++data;
            }
            ++left;
        }
        return ans;
    }
}
