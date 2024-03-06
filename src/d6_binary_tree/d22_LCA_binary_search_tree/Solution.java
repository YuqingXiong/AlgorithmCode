package d6_binary_tree.d22_LCA_binary_search_tree;

import d6_binary_tree.TreeNode;

public class Solution {
    TreeNode ans = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ans = null;
        dfs(root, p, q);
        return ans;
    }

    public void dfs(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return;
        if(root.val > p.val && root.val > q.val) dfs(root.left, p, q);
        else if(root.val < p.val && root.val < q.val) dfs(root.right, p, q);
        else ans = root;
    }
}