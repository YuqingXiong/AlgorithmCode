package d6_binary_tree.d13_find_bottom_left_tree_value;

import d6_binary_tree.TreeNode;

public class Solution {
    int curDepth = 1;
    int maxDepth = 0;
    int curVal = 0;
    public int findBottomLeftValue(TreeNode root) {
        if(root == null) return curVal;
        if(root.left == null && root.right == null){
            if(maxDepth < curDepth){
                maxDepth = curDepth;
                curVal = root.val;
                return curVal;
            }
        }
        curDepth += 1;
        findBottomLeftValue(root.left);
        findBottomLeftValue(root.right);
        curDepth -= 1;
        return curVal;
    }
}
