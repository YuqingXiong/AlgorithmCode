package d6_binary_tree.d1_binary_tree_preorder_traversal;

import d6_binary_tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<Integer> ans = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) return ans;
        ans.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return ans;
    }
}
