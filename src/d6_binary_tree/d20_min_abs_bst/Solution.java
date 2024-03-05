package d6_binary_tree.d20_min_abs_bst;

import d6_binary_tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public int getMinimumDifference(TreeNode root) {

        Deque<TreeNode> stack = new ArrayDeque<>();
        int pre = -1, res = Integer.MAX_VALUE;
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pre != -1){
                res = Math.min(res, root.val - pre);
            }
            pre = root.val;
            root = root.right;
        }
        return res;
    }
}
