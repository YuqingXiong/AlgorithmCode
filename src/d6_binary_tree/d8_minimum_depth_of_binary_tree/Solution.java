package d6_binary_tree.d8_minimum_depth_of_binary_tree;

import d6_binary_tree.TreeNode;

public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int lmin = minDepth(root.left);
        int rmin = minDepth(root.right);
        if(lmin == 0 && rmin == 0) return 1;
        else if(lmin == 0 || rmin == 0) return Math.max(lmin, rmin)+1;
        return Math.min(lmin, rmin)+1;
    }
}
