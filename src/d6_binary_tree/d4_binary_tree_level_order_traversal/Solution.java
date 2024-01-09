package d6_binary_tree.d4_binary_tree_level_order_traversal;

import d6_binary_tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        List<TreeNode> levelNode = new ArrayList<>();
        levelNode.add(root);
        while(!levelNode.isEmpty()){
            List<Integer> levelAns = new ArrayList<>();
            List<TreeNode> nextLevelNode = new ArrayList<>();
            for(TreeNode node : levelNode){
                levelAns.add(node.val);
                System.out.println(node.val);
                if(node.left != null)
                    nextLevelNode.add(node.left);
                if(node.right != null)
                    nextLevelNode.add(node.right);
            }
            levelNode = nextLevelNode;
            ans.add(levelAns);
        }
        return ans;
    }
}
