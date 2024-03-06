package d6_binary_tree.d21_lowest_common_ancestor;

import d6_binary_tree.TreeNode;

public class Solution {
    TreeNode ans;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ans = null;
        dfs(root, p, q);
        return ans;
    }

    public boolean dfs(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return false;

        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);

        if((lson && rson) || ((root.val == p.val || root.val == q.val)&&(lson || rson))){
            ans = root;
            return true;
        }

        return root.val == p.val || root.val == q.val || lson || rson;
    }
}
