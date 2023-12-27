package d1_array.d5_59_spiral_matrix_ii;

public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int data = 1;
        int x = 0, y = 0;
//        int l = 0, r = n - 1, top = 0, bottom = n - 1;
        int[][] step = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dire = 0;
        while(data <= n*n){
            System.out.println(x + ", " + y);
            ans[x][y] = data;
            ++data;
            int stepx = x + step[dire][0];
            int stepy = y + step[dire][1];
            if(stepx < 0 || stepx >= n
                    || stepy < 0 || stepy >= n
                    || ans[stepx][stepy] != 0){
                dire = (dire + 1) % 4;
            }
            x += step[dire][0];
            y += step[dire][1];
        }
        return ans;
    }
}
