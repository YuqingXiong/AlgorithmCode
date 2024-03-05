package d6_binary_tree.d19_validate_binary_search_tree;

import d6_binary_tree.TreeNode;

public class Solution {
    public boolean isValidBST(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean check(TreeNode root, long left, long right){
        if(root == null) return true;
        if(root.val <= left || root.val >= right) return false;

        return check(root.left, left, root.val) && check(root.right, root.val, right);
    }
}
