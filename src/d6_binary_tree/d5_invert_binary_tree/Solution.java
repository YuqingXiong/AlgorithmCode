package d6_binary_tree.d5_invert_binary_tree;

import d6_binary_tree.TreeNode;

public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;

        TreeNode right = invertTree(root.left);
        TreeNode left = invertTree(root.right);
        root.right = right;
        root.left = left;
        return root;
    }
}
