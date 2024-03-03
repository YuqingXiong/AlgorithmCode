package d6_binary_tree.d15_build_from_inorder_postorder;

import d6_binary_tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<Integer, Integer> hash;
    int idx;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        hash = new HashMap<>();
        for(int i = 0; i < inorder.length; ++ i){
            hash.put(inorder[i], i);
        }
        idx = inorder.length-1;
        return build(inorder, postorder, 0, inorder.length-1);
    }

    public TreeNode build(int[] inorder, int[] postorder, int l, int r){
        if(l > r){
            return null;
        }
        // if(l == r){
        //     idx--;
        //     return new TreeNode(inorder[l]);
        // }
        TreeNode root = new TreeNode(postorder[idx]);
        int mid = hash.get(postorder[idx--]);

        root.right = build(inorder, postorder, mid + 1, r);
        root.left = build(inorder, postorder, l, mid-1);
        return root;
    }
}
