package d6_binary_tree.d18_search_in_binary_search_tree;

import d6_binary_tree.TreeNode;

public class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null){
            return null;
        }
        if(root.val == val){
            return root;
        }
        if(root.val > val){
            return searchBST(root.left, val);
        }
        return searchBST(root.right, val);
    }
}