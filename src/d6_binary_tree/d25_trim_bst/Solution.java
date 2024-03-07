package d6_binary_tree.d25_trim_bst;

import d6_binary_tree.TreeNode;

public class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null) return root;
        if(root.val < low){
            return trimBST(root.right, low, high);
        }else if(root.val > high){
            return trimBST(root.left, low, high);
        }else{
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }
}
