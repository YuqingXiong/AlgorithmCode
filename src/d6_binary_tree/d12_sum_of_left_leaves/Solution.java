package d6_binary_tree.d12_sum_of_left_leaves;

import d6_binary_tree.TreeNode;

public class Solution {
    public int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return sum;
        if(root.left != null && root.left.left == null && root.left.right == null)
            sum = sum + root.left.val;
        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);
        return sum;
    }
}
