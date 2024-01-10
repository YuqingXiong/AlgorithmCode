package d6_binary_tree.d9_count_complete_tree_nodes;

import d6_binary_tree.TreeNode;

public class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        int ans = 0;
        ans += countNodes(root.left);
        ans += countNodes(root.right);
        return ans + 1;
    }
}
