package d6_binary_tree.d6_symmetric_tree;

import d6_binary_tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public boolean isSymmetric(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while(!queue.isEmpty()){
            TreeNode l = queue.poll();
            TreeNode r = queue.poll();
            if(l == null && r == null) continue;
            if(l == null || r == null || l.val != r.val) return false;

            queue.offer(l.left);
            queue.offer(r.right);

            queue.offer(l.right);
            queue.offer(r.left);
        }
        return true;
    }
}
