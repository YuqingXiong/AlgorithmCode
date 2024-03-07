package d6_binary_tree.d26_bst_to_greater_sum_tree;

import d6_binary_tree.TreeNode;

public class Solution {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root == null) return root;
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }
}
