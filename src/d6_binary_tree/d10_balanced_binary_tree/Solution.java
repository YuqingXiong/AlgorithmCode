package d6_binary_tree.d10_balanced_binary_tree;

import d6_binary_tree.TreeNode;

public class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        int lLevel = countLevel(root.left);
        int rLevel = countLevel(root.right);
        if(Math.abs(lLevel-rLevel) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int countLevel(TreeNode root){
        if(root == null) return 0;
        return Math.max(countLevel(root.left), countLevel(root.right)) + 1;
    }
}
