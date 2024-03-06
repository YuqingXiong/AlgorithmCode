package d6_binary_tree.d23_insert_binary_search_tree;

import d6_binary_tree.TreeNode;

public class Solution {
    boolean add = false;
    public TreeNode insertIntoBST(TreeNode root, int val) {
        return dfs(root, val);
    }
    public TreeNode dfs(TreeNode root, int val){
        if(root == null) return new TreeNode(val);
        if(val < root.val){
            root.left = dfs(root.left, val);
        }else{
            root.right = dfs(root.right, val);
        }
        return root;
    }
}
