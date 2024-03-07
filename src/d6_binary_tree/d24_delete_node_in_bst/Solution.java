package d6_binary_tree.d24_delete_node_in_bst;

import d6_binary_tree.TreeNode;

public class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return root;
        if(key < root.val){
            root.left = deleteNode(root.left, key);
            return root;
        }else if(key > root.val){
            root.right = deleteNode(root.right, key);
            return root;
        }else if(key == root.val){
            if(root.left == null && root.right == null){
                return null;
            }else if(root.left == null){
                return root.right;
            }else if(root.right == null){
                return root.left;
            }else{
                TreeNode node = root.right;
                while(node.left != null){
                    node = node.left;
                }
                root.right = deleteNode(root.right, node.val);
                node.left = root.left;
                node.right = root.right;
                return node;
            }
        }
        return root;
    }
}
