package d6_binary_tree.d7_binary_tree_max_depth;

import d6_binary_tree.TreeNode;

public class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int lnum = maxDepth(root.left);
        int rnum = maxDepth(root.right);
        return Math.max(lnum, rnum) + 1;
    }
}
